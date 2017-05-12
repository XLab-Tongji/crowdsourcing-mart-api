package com.crazy.controller;

import com.crazy.entity.*;
import com.crazy.repository.*;
import com.crazy.security.JwtAuthenticationRequest;
import com.crazy.security.JwtTokenUtil;
import com.crazy.security.JwtUserFactory;
import com.crazy.service.AccountService;
import com.crazy.service.RequirementDetail;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


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
    @Autowired
    private ProjectExperienceRepository projectExperienceRepository;

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
    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/user/verification", method = RequestMethod.POST)
    public ResJsonTemplate UserInfoVerifacation(
            HttpServletRequest request, @RequestBody UserInfoDetail userInfoDetail) throws AuthenticationException {
        java.lang.String token = request.getHeader("Authorization");
        java.lang.String username = jwtTokenUtil.getUsernameFromToken(token);
        Account account = accountRepository.findByUsername(username);
        if (jwtTokenUtil.validateToken(token, JwtUserFactory.create(account))) {

            account.setInfo_id(userInfoDetailRepository.save(userInfoDetail).getId());
            accountRepository.save(account);
            return new ResJsonTemplate("200", "实名认证成功");
        }
        return new ResJsonTemplate("400", "实名认证失败");

    }
    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/user/projectExperience", method = RequestMethod.POST)
    public ResJsonTemplate developerSkill(
            HttpServletRequest request, @RequestParam(value = "certificate", required = false) MultipartFile file,
            @RequestParam(value = "project_name") String project_name,
            @RequestParam(value = "project_region") String project_region,
            @RequestParam(value = "project_address") String project_address,
            @RequestParam(value = "project_text") String project_text
    ) throws AuthenticationException, IOException {
        java.lang.String token = request.getHeader("Authorization");
        java.lang.String username = jwtTokenUtil.getUsernameFromToken(token);
        Account account = accountRepository.findByUsername(username);


        ProjectExperience projectExperience = new ProjectExperience();
        projectExperience.setAccountId(account.getAccount_id());
        projectExperience.setProjectAddress(project_address);
        projectExperience.setProjectName(project_name);
        projectExperience.setProjectRegion(project_region);
        projectExperience.setProjectText(project_text);
        if (file != null) {
            byte[] data = new byte[file.getInputStream().available()];
            file.getInputStream().read(data);
            projectExperience.setCertificate(data);
        }

        ProjectExperience temp = projectExperienceRepository.save(projectExperience);
        return new ResJsonTemplate("201", temp);

    }

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
        java.lang.String token = request.getHeader("Authorization");
        java.lang.String username = jwtTokenUtil.getUsernameFromToken(token);
        Account account = accountRepository.findByUsername(username);
        Requirement requirement = new Requirement();
        requirement.setCreatorId(account.getAccount_id());
        requirement.setRequirement_name(requirement_name);
        requirement.setRequirement_type(requirement_type);
        requirement.setRequirement_detail(requirement_detail);
        requirement.setNeed_manager(need_manager);
        requirement.setStart_time(start_time);
        requirement.setEnd_time(end_time);
        if (file != null) {
            byte[] data = new byte[file.getInputStream().available()];
            file.getInputStream().read(data);
            requirement.setFile(data);
        }

        requirementRepository.save(requirement);
        return new ResJsonTemplate("201", "创建需求成功");

    }

    @PreAuthorize("hasRole('user')")
    @RequestMapping(value= "/requirement",method=RequestMethod.GET)
    public ResJsonTemplate getRequirement(HttpServletRequest request)
    {


        java.lang.String token = request.getHeader("Authorization");
        java.lang.String username = jwtTokenUtil.getUsernameFromToken(token);
        Account account = accountRepository.findByUsername(username);
        List<Requirement> requirements = requirementRepository.findByCreatorId(account.getAccount_id());
        ArrayList<simpleRequirement> simpleRequirements = new ArrayList<simpleRequirement>();
        for (int i = 0; i < requirements.size(); i++) {
            simpleRequirement s = new simpleRequirement();
            s.setRequirement_id(requirements.get(i).getId());
            s.setRequirement_type(requirements.get(i).getRequirement_type());
            s.setRequirement_name(requirements.get(i).getRequirement_name());
            s.setRequirement_state(requirements.get(i).getRequirement_state());
            simpleRequirements.add(s);
        }
        return new ResJsonTemplate("200", simpleRequirements);
    }

    @RequestMapping(value = "/requirement/{id}", method = RequestMethod.DELETE)
    public ResJsonTemplate DeteleRequirement(HttpServletRequest request, @PathVariable Long id) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return new ResJsonTemplate("401", "权限错误");
        }
        if (!requirementRepository.exists(id)) {
            return new ResJsonTemplate("400", "删除失败");
        }
        requirementRepository.deleteById(id);
        return new ResJsonTemplate("200", "删除成功");
    }

    @RequestMapping(value = "/requirement/{id}", method = RequestMethod.PUT)
    public ResJsonTemplate UpdateRequirement(HttpServletRequest request, @RequestBody Requirement requirement, @PathVariable Long id) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return new ResJsonTemplate("401", "权限错误");
        }
        if (!requirementRepository.exists(id)) {
            return new ResJsonTemplate("400", "删除失败");
        }
        requirement.setId(id);
        requirementRepository.save(requirement);
        return new ResJsonTemplate("200", "更新成功");
    }

    @RequestMapping(value = "/requirement/{id}", method = RequestMethod.GET)
    public ResJsonTemplate GetRequirementDetail(HttpServletRequest request, @PathVariable Long id) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return new ResJsonTemplate("401", "权限错误");
        }
        RequirementDetail requirementDetail = new RequirementDetail();
        requirementDetail = accountService.GetRequirementDetail(id);
        return new ResJsonTemplate("200", requirementDetail);
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

//用于 获取前端的skill信息


class data1 {
    private byte[] certificate;
    private skill skill;

    public void setCertificate(byte[] c) {
        certificate = c;
    }

    public byte[] getCertificate() {
        return certificate;
    }

    public void setSkill(skill s) {
        skill = s;
    }

    public skill getSkill() {
        return skill;
    }
}

class data2 {
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

class skill {
    private java.lang.String skill_name;
    private java.lang.String skill_detail;

    public java.lang.String getSkill_name() {
        return skill_name;
    }

    public void setSkill_name(java.lang.String s) {
        skill_name = s;
    }

    public java.lang.String getSkill_detail() {
        return skill_detail;
    }

    public void setSkill_detail(java.lang.String s) {
        skill_detail = s;
    }

    @Override
    public java.lang.String toString() {
        return "skill{" +
                "skill_name='" + skill_name + '\'' +
                ", skill_detail='" + skill_detail + '\'' +
                '}';
    }
}

class simpleRequirement {
    private Long requirement_id;
    private String requirement_name;
    private String requirement_type;
    private int requirement_state;

    public Long getRequirement_id() {
        return requirement_id;
    }

    public void setRequirement_id(Long requirement_id) {
        this.requirement_id = requirement_id;
    }

    public int getRequirement_state() {
        return requirement_state;
    }

    public void setRequirement_state(int requirement_state) {
        this.requirement_state = requirement_state;
    }

    public String getRequirement_type() {
        return requirement_type;
    }

    public void setRequirement_type(String requirement_type) {
        this.requirement_type = requirement_type;
    }

    public String getRequirement_name() {
        return requirement_name;
    }

    public void setRequirement_name(String requirement_name) {
        this.requirement_name = requirement_name;
    }
}
