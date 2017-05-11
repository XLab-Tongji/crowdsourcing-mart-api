package com.crazy.service.impl;

import com.crazy.entity.Account;
import com.crazy.entity.DevEnrollInfo;
import com.crazy.entity.Requirement;
import com.crazy.entity.UserInfoDetail;
import com.crazy.repository.AccountRepository;
import com.crazy.repository.DevEnrollInfoRepository;
import com.crazy.repository.RequirementRepository;
import com.crazy.repository.UserInfoDetailRepository;
import com.crazy.security.JwtTokenUtil;
import com.crazy.service.AccountService;
import com.crazy.service.RequirementDetail;
import com.crazy.service.SimpleAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户服务实现类
 * Created by SHIKUN on 2016/10/29.
 */
@Service
public class AccountServiceImpl implements AccountService {
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private AccountRepository userRepository;

    @Autowired
    private UserInfoDetailRepository userInfoDetailRepository;

    @Autowired
    private DevEnrollInfoRepository devEnrollInfoRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RequirementRepository requirementRepository;


    @Value("")
    private String tokenHead;

    @Autowired
    public AccountServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil,
            AccountRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }

    @Override
    public Account register(Account userToAdd) {
        final String username = userToAdd.getUsername();
        if (userRepository.findByUsername(username) != null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
        userToAdd.setRole_id(1L);
        UserInfoDetail userInfoDetail = new UserInfoDetail();
        userToAdd.setInfo_id(userInfoDetail.getId());
        return userRepository.save(userToAdd);

    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public RequirementDetail GetRequirementDetail(Long requirementId) {
        List<SimpleAccount> developers = new ArrayList<>();
        List<DevEnrollInfo> devEnrollInfo = new ArrayList<>();
        devEnrollInfo = devEnrollInfoRepository.findByProjectId(requirementId);
        for (int i = 0; i < devEnrollInfo.size(); i++) {
            String userName = devEnrollInfo.get(i).getUsername();
            Account account = accountRepository.findByUsername(userName);
            SimpleAccount simpleAccount = new SimpleAccount();
            simpleAccount.setEmail(account.getEmail());
            simpleAccount.setName(account.getName());
            simpleAccount.setPhoneNumber(account.getMobile());
            developers.add(simpleAccount);
        }
        Requirement requirement = requirementRepository.findOne(requirementId);
        RequirementDetail requirementDetail = new RequirementDetail();
        requirementDetail.setRequirementName(requirement.getRequirement_name());
        requirementDetail.setRequirementType(requirement.getRequirement_type());
        requirementDetail.setStart_time(requirement.getStart_time());
        requirementDetail.setEnd_time(requirement.getEnd_time());
        requirementDetail.setNeed_manager(requirement.getNeed_manager());
        requirementDetail.setRequirement_detail(requirement.getRequirement_detail());
        requirementDetail.setFile(requirement.getFile());
        requirementDetail.setDevelopers(developers);
        return requirementDetail;
    }


    //
//    @Autowired
//    private AccountRepository accountRepository;
//    @Autowired
//    private AccountLoginRepository accountLoginRepository;
//
//
//    @Autowired
//    private Encryption encryption;
//
//    @Autowired
//    private ConvertJson convertJson;
//
//    @Autowired
//    private DateUtil dateUtil;
//
//    @Autowired
//    private GitlabAccountService gitlabAccountService;
//
//    @Override
//    public ResJsonTemplate addAccount(Account account) {
//        try {
//            Account savedAccount = new Account(account.getUsername(), account.getName(), account.getIcon(),
//                    encryption.doEncryption(account.getPassword()),
//                    account.getMobile(), account.getEmail(),
//                    account.getExt_params(), 1);
//            return new ResJsonTemplate("200", accountRepository.save(savedAccount));
//            /*
//            return new ResJsonTemplate("200",accountMapper.addAcount(account.getUsername(), account.getName(), account.getIcon(),
//                    encryption.doEncryption(account.getPassword()),
//                    account.getMobile(), account.getEmail(),
//                    account.getExt_params()
//            ));
//            */
//            /*
//            return new ResJsonTemplate("200",accountMapper.addAcount(account.getUsername(), account.getName(), account.getIcon(),
//                    encryption.doEncryption(account.getPassword()),
//                    account.getMobile(), account.getEmail(),
//                    convertJson.Map2Json(account.getExt_params())
//            ));
//            */
//
//            /*if (!gitlabAccountService.GitlabAddAccount(account)) {
//                return new ResJsonTemplate("500", "创建gitlab用户失败");
//            }
//
//            int ret = accountMapper.addAcount(account.getUsername(), account.getName(), account.getIcon(),
//                    encryption.doEncryption(account.getPassword()),
//                    account.getMobile(), account.getEmail(),
//                    convertJson.Map2Json(account.getExt_params()));
//            System.out.println(ret);
//            System.out.println(gitlabAccountService.getGitlabId());
//
//            martGitConnectionMapper.addConnection(ret, gitlabAccountService.getGitlabId());
//
//            return new ResJsonTemplate("200", ret);*/
//
//        } catch (Exception ex) {
//            System.out.println(ex);
//
//            return new ResJsonTemplate("500", "创建用户失败");
//
//        }
//    }
//
//
//    @Override
//    public ResJsonTemplate checkAccount(Account account, String useragent, HttpServletRequest request) {
//
//        String result = null;
//        String token = null;
//        String tmep = account.getUsername();
//        Account selectResult = accountRepository.findByUsername(account.getUsername());
//        //     Map<String, String> selectResult = accountMapper.getCheckInfo(account.getUsername());
//
//        if (selectResult == null) {
//            result = "没有此用户";
//        } else if (!encryption.checkPassword(account.getPassword(), selectResult.getPassword())) {
//            result = "密码错误";
//        } else {
//            token = encryption.createToken();
//            /*
//            accountMapper.addLoginLog(request.getRemoteAddr(), token, dateUtil.Str2Date(dateUtil.getNowTime()),
//                    dateUtil.Str2Date(dateUtil.setExpire(30)), accountMapper.getUserId(account.getUsername()),
//                    useragent, account.getUsername());
//            */
//            accountLoginRepository.save(new AccountLogin(request.getRemoteAddr(), token, dateUtil.Str2Date(dateUtil.getNowTime()),
//                    dateUtil.Str2Date(dateUtil.setExpire(30)), selectResult.getAccount_id(),
//                    useragent, selectResult.getUsername()));
//
//            result = token;
//        }
//
//        if (token != null) {
//            Map tokencons = new HashMap();
//
//            tokencons.put("tokens", token);
//            tokencons.put("username", account.getUsername());
//
//            List<Map> tokenresult = new LinkedList<>();
//
//            tokenresult.add(tokencons);
//
//            return new ResJsonTemplate("200", tokenresult);
//        } else {
//            return new ResJsonTemplate("200", result);
//        }
//
//    }
}
