package com.crazy.controller;

import com.crazy.entity.Account;
import com.crazy.entity.Developer;
import com.crazy.entity.Requirement;
import com.crazy.entity.UserInfoDetail;
import com.crazy.repository.AccountRepository;
import com.crazy.repository.DeveloperRepository;
import com.crazy.repository.RequirementRepository;
import com.crazy.repository.UserInfoDetailRepository;
import com.crazy.security.JwtAuthenticationRequest;
import com.crazy.security.JwtTokenUtil;
import com.crazy.security.JwtUserFactory;
import com.crazy.service.AccountService;
import com.crazy.util.ConvertJson;
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
    UserInfoDetailRepository userInfoDetailRepository;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private RequirementRepository requirementRepository;
    @Autowired
    private ConvertJson convertJson;

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
        data2 d = new data2();
        d.setToken(token);
        d.setUserInfoDetail(userInfoDetail);

        return new ResJsonTemplate("200", d);


        // Return the token
        //     return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "/user/verification", method = RequestMethod.POST)
    public ResJsonTemplate UserInfoVerifacation(
            HttpServletRequest request, @RequestBody UserInfoDetail userInfoDetail) throws AuthenticationException {
        java.lang.String token = request.getHeader("Authorization");
        if (token == null) {
            return new ResJsonTemplate("400", "实名认证失败");
        }
        java.lang.String username = jwtTokenUtil.getUsernameFromToken(token);
        Account account = accountRepository.findByUsername(username);
        if (jwtTokenUtil.validateToken(token, JwtUserFactory.create(account))) {

            account.setInfo_id(userInfoDetailRepository.save(userInfoDetail).getId());
            accountRepository.save(account);
            return new ResJsonTemplate("200", "实名认证成功");
        }
        return new ResJsonTemplate("400", "实名认证失败");

    }

    @RequestMapping(value = "/user/skill", method = RequestMethod.POST)
    public ResJsonTemplate developerSkill(
            HttpServletRequest request,@RequestParam(value = "certificate") MultipartFile file, @RequestParam(value = "skill_name") String skill_name,@RequestParam(value = "skill_detail") String skill_detail) throws AuthenticationException, IOException {
        java.lang.String token = request.getHeader("Authorization");
        byte[] data = new byte[file.getInputStream().available()];
        file.getInputStream().read(data);


        if (token == null) {
            return new ResJsonTemplate("400", "上传失败，无该用户");
        }
        java.lang.String username = jwtTokenUtil.getUsernameFromToken(token);
        Developer developer = developerRepository.findByUsername(username);
        developer.setSkill_name(skill_name);
        developer.setSkill_detail(skill_detail);
        developer.setCertificate(data);
        developerRepository.save(developer);



        data1 d = new data1();
        d.setCertificate(data);
        skill s = new skill();
        s.setSkill_name(skill_name);
        s.setSkill_detail(skill_detail);
        d.setSkill(s);
        return new ResJsonTemplate("201",d);

    }
    @RequestMapping(value = "/user/requirement", method = RequestMethod.POST)
    public ResJsonTemplate createRequirement(
            HttpServletRequest request,
            @RequestParam(value = "requirement_name") String requirement_name,
            @RequestParam(value = "requirement_type") String requirement_type,
            @RequestParam(value = "need_manager") int need_manager,
            @RequestParam(value = "start_time" ) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
            @RequestParam(value = "end_time") @DateTimeFormat(pattern="yyyy-MM-dd") Date end_time,
            @RequestParam(value = "requirement_detail") String requirement_detail,
            @RequestParam(value = "file") MultipartFile file) throws AuthenticationException, IOException {

        Requirement requirement = new Requirement();
        requirement.setRequirement_name(requirement_name);
        requirement.setRequirement_type(requirement_type);
        requirement.setRequirement_detail(requirement_detail);
        requirement.setNeed_manager(need_manager);
        requirement.setStart_time(start_time);
        requirement.setEnd_time(end_time);
        byte[] data = new byte[file.getInputStream().available()];
        file.getInputStream().read(data);
        requirement.setFile(data);
        requirementRepository.save(requirement);
        return new ResJsonTemplate("201","创建需求成功");

    }
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

}
//
//    @Autowired
//    private AccountService accountService;
//
//
//    @Autowired
//    private Encryption encryption;
//
//
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    @ResponseBody
//    public ResJsonTemplate addAccount(@RequestBody Account account) {
//        return accountService.addAccount(account);
//    }
//
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @ResponseBody
//    public  ResJsonTemplate checkAccount(@RequestBody Account account, @RequestHeader(value = "User-Agent") String useragent,
//                                 HttpServletRequest request) {
//
//        return accountService.checkAccount(account, useragent, request);
//    }
//
//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public String test(@RequestParam(value = "token") String token) {
//        return encryption.tokenValidate(token);
//    }



//用于 获取前端的skill信息


class data1 {
    private byte[] certificate;
    private skill skill;
    public void setCertificate(byte[] c)
    {
        certificate = c;
    }
    public byte[] getCertificate()
    {
        return certificate;
    }
    public void setSkill(skill s)
    {
        skill = s;
    }
    public skill getSkill()
    {
        return skill;
    }
}
class data2
{
    private java.lang.String token;
    private UserInfoDetail userInfoDetail;
    public void setToken(java.lang.String t)
    {
        token = t;
    }
    public java.lang.String getToken()
    {
        return token;
    }
    public void setUserInfoDetail(UserInfoDetail u)
    {
        userInfoDetail = u;
    }
    public UserInfoDetail getUserInfoDetail()
    {
        return userInfoDetail;
    }
}
class skill
{
    private java.lang.String skill_name;
    private java.lang.String skill_detail;
    public java.lang.String getSkill_name()
    {
        return skill_name;
    }
    public void setSkill_name(java.lang.String s)
    {
        skill_name = s;
    }
    public java.lang.String getSkill_detail()
    {
        return skill_detail;
    }
    public void setSkill_detail(java.lang.String s)
    {
        skill_detail = s;
    }
    @Override
    public java.lang.String toString()
    {
        return "skill{" +
                "skill_name='" + skill_name+ '\'' +
                ", skill_detail='" + skill_detail + '\'' +
                '}';
    }
}
