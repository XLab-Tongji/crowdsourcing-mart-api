package com.crazy.controller;

import com.crazy.entity.Account;
import com.crazy.entity.DevEnrollInfo;
import com.crazy.entity.Requirement;
import com.crazy.entity.UserInfoDetail;
import com.crazy.repository.AccountRepository;
import com.crazy.repository.ProjectRepository;
import com.crazy.repository.RequirementRepository;
import com.crazy.repository.UserInfoDetailRepository;
import com.crazy.security.JwtAuthenticationRequest;
import com.crazy.security.JwtTokenUtil;
import com.crazy.service.*;
import com.crazy.util.ResJsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;


/**
 * Account Controller
 * Created by SHIKUN on 2016/9/30.
 */

@RestController
@RequestMapping(value = "/api")
public class AccountController {
    @Value("Authorization")
    private String tokenHeader;

    @Autowired
    private AccountService accountService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private RequirementService requirementService;
    @Autowired
    private UserInfoDetailService userInfoDetailService;
    @Autowired
    private ProjectExperienceService projectExperienceService;
    @Autowired
    UserInfoDetailRepository userInfoDetailRepository;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RequirementRepository requirementRepository;
    @Autowired
    private ProjectRepository projectRepository;


    //获取token
    @RequestMapping(value = "/session", method = RequestMethod.POST)
    public ResJsonTemplate createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        //数据库查找是否存在该用户
        Account account = accountRepository.findByUsername(authenticationRequest.getUsername());
        if (account == null) {
            return new ResJsonTemplate("404", "用户不存在");
        }
        //验证账号密码是否正确
        final java.lang.String token = accountService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());


        UserInfoDetail userInfoDetail = userInfoDetailRepository.findById(account.getInfo_id());
        AccountInfo d = new AccountInfo();
        d.setToken(token);
        d.setUserInfoDetail(userInfoDetail);

        return new ResJsonTemplate("200", d);


    }
    //新增实名认证
    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/user/verification", method = RequestMethod.POST)
    public ResJsonTemplate UserInfoVerifacation(
            HttpServletRequest request, @RequestBody UserInfoDetail userInfoDetail) throws AuthenticationException {
        Account account = getAccount(request);
        return userInfoDetailService.addUserInfoDetail(account,userInfoDetail);

    }
    //新增项目经历
    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/user/projectExperience", method = RequestMethod.POST)
    public ResJsonTemplate developerSkill(
            HttpServletRequest request, @RequestParam(value = "certificate", required = false) MultipartFile file,
            @RequestParam(value = "project_name") String project_name,
            @RequestParam(value = "project_region") String project_region,
            @RequestParam(value = "project_address") String project_address,
            @RequestParam(value = "project_text") String project_text
    ) throws AuthenticationException, IOException {
        Account account = getAccount(request);
        return projectExperienceService.addExperience(account,
                file,
                project_name,
                project_region,
                project_address,
                project_text);
    }
    //获取项目经历
    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/user/projectExperience", method = RequestMethod.GET)
    public ResJsonTemplate getDeveloperSkill(HttpServletRequest request)throws AuthenticationException, IOException
    {
        Account account = getAccount(request);
        return projectExperienceService.getExperience(account);
    }
    //新增需求
   // @Secured(value = { "user" })
    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/requirement", method = RequestMethod.POST)
    public ResJsonTemplate createRequirement(
            HttpServletRequest request,
            @RequestParam(value = "requirement_name") String requirement_name,
            @RequestParam(value = "requirement_type") String requirement_type,
            @RequestParam(value = "need_manager") int need_manager,
            @RequestParam(value = "start_time") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start_time,
            @RequestParam(value = "end_time") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end_time,
            @RequestParam(value = "requirement_detail") String requirement_detail,
            @RequestParam(value = "file", required = false) MultipartFile file) throws AuthenticationException, IOException {
        Account account = getAccount(request);
        return  requirementService.addRequirement(account,requirement_name,
                requirement_type,
                need_manager,
                start_time,
                end_time,
                requirement_detail,
                file);
    }

    //获取需求
    @PreAuthorize("hasRole('user')")
    @RequestMapping(value= "/requirement",method=RequestMethod.GET)
    public ResJsonTemplate getRequirement(HttpServletRequest request)
    {
        Account account = getAccount(request);
        return  requirementService.getReuirement(account);
    }

    //删除需求
    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/requirement/{id}", method = RequestMethod.DELETE)
    public ResJsonTemplate DeteleRequirement(HttpServletRequest request, @PathVariable Long id) {
        if (!requirementRepository.exists(id)) {
            return new ResJsonTemplate("400", "删除失败");
        }
        Requirement target = requirementRepository.findById(id);
        requirementRepository.delete(target);
    //    requirementRepository.deleteById(id);
        return new ResJsonTemplate("200", "删除成功");
    }

    //更新需求
    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/updateRequirement/{id}", method = RequestMethod.POST)
    public ResJsonTemplate UpdateRequirement(HttpServletRequest request,
                                             @RequestParam(value = "requirement_name") String requirement_name,
                                             @RequestParam(value = "requirement_type") String requirement_type,
                                             @RequestParam(value = "need_manager") int need_manager,
                                             @RequestParam(value = "start_time") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start_time,
                                             @RequestParam(value = "end_time") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end_time,
                                             @RequestParam(value = "requirement_detail") String requirement_detail,
                                             @RequestParam(value = "file", required = false) MultipartFile file,
                                             @PathVariable Long id) throws IOException {


        Account account = getAccount(request);
        return  requirementService.updateRequirement(account,id,requirement_name,
                requirement_type,
                need_manager,
                start_time,
                end_time,
                requirement_detail,
                file);
    }

    //获取详细需求
    @RequestMapping(value = "/requirement/{id}", method = RequestMethod.GET)
    public ResJsonTemplate GetRequirementDetail(HttpServletRequest request, @PathVariable Long id) {
        String token = request.getHeader("Authorization");
        Account account = getAccount(request);
        if (token == null) {
            new ResJsonTemplate("200", accountService.GetRequirementDetail(account.getUsername(),id,0));
        }

        if(account.getAccount_id()!=requirementService.getRequirement(id).getCreatorId())
        {
            return new ResJsonTemplate("200", accountService.GetRequirementDetail(account.getUsername(),id,1));
        }

        return new ResJsonTemplate("200", accountService.GetRequirementDetail(account.getUsername(),id,2));
    }

    //加入需求
    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/requirement/{id}/enroll", method = RequestMethod.POST)
    public ResJsonTemplate EnrollProject(HttpServletRequest request, @PathVariable Long id) {
        Account account = getAccount(request);
        if(requirementService.getRequirement(id)!=null)
        {
            if(requirementService.getRequirement(id).getCreatorId()==account.getAccount_id())
            {
                 return new ResJsonTemplate("400","该项目为自己创建，不能报名");
            }
            DevEnrollInfo devEnrollInfo = new DevEnrollInfo(account.getUsername(), id);
            return requirementService.addEnrollInfo(devEnrollInfo);
        }
        else
        {
            return new ResJsonTemplate("404","项目不存在");
        }
    }
    //获取所有需求
    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/requirements", method = RequestMethod.GET)
    public ResJsonTemplate getRequirement() {
        return requirementService.getRequirement();
    }

    //获取账号参加的项目
    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public ResJsonTemplate getProjectList(HttpServletRequest request) {
        Account account = getAccount(request);
        return requirementService.getMyRequirement(account);
       // return projectService.getProjectList(account.getUsername());
    }

    //注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResJsonTemplate register(@RequestBody Account addedUser) throws AuthenticationException {
        if (accountService.register(addedUser) != null) {

            return new ResJsonTemplate<java.lang.String>("201", "注册成功");
        } else {
            return new ResJsonTemplate<java.lang.String>("400", "注册失败");
        }
    }

    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Account getUserByUsername(@RequestParam(value = "username") java.lang.String username) {
        return accountRepository.findByUsername(username);
    }

    //获取token信息
    public Account getAccount(HttpServletRequest request)
    {
        java.lang.String token = request.getHeader("Authorization");
        java.lang.String username = jwtTokenUtil.getUsernameFromToken(token);
        Account account = accountRepository.findByUsername(username);
        return account;
    }

}


class AccountInfo {
    private java.lang.String token;
    private UserInfoDetail userInfoDetail;

    public void setToken(java.lang.String t) {
        token = t;
    }

    public java.lang.String getToken() {
        return token;
    }

    public void setUserInfoDetail(UserInfoDetail u) {
        userInfoDetail = u;
    }

    public UserInfoDetail getUserInfoDetail() {
        return userInfoDetail;
    }
}
