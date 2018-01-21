package com.lab409.crowdingsourcing.controller;

import com.lab409.crowdingsourcing.dto.*;
import com.lab409.crowdingsourcing.entity.*;
import com.lab409.crowdingsourcing.repository.*;
import com.lab409.crowdingsourcing.service.*;
import com.lab409.crowdingsourcing.util.ResJsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "api")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProjectExperienceRepository projectExperienceRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserInfoDetailService userInfoDetailService;

    @Autowired
    private DevEnrollInfoRepository devEnrollInfoRepository;
    @Autowired
    private ProjectExperienceService projectExperienceService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RequirementService requirementService;
    @Autowired
    private TestRepository testRepository;

    @RequestMapping(value = "/all/account", method = RequestMethod.GET)
    public ResJsonTemplate showAllAccount(HttpServletRequest request){
        Account account = accountService.getAccount(request);
        if (account == null) {
            return new ResJsonTemplate<>("404","unauthorized");
        }
        if (account.getRole_id() != 999){
            return new ResJsonTemplate<>("400", "无此权限");
        }
        List<Account> accountList = accountRepository.findAll();
        return new ResJsonTemplate<>("200",accountList);
    }

    @RequestMapping(value = "/account/{username}", method = RequestMethod.GET)
    public ResJsonTemplate getAnAccount(HttpServletRequest request,@PathVariable String username){
        Account account = accountService.getAccount(request);
        if (account == null) {
            return new ResJsonTemplate<>("404","unauthorized");
        }
        if (account.getRole_id() != 999){
            return new ResJsonTemplate<>("400", "无此权限");
        }
        Account accountInfo = accountRepository.findByUsername(username);
        return new ResJsonTemplate<>("200",accountInfo);
    }
    @RequestMapping(value = "/test")
    public ResJsonTemplate<List<Project>> test(@RequestParam String name){
        return new ResJsonTemplate<>("200",testRepository.findByEmailAddress(name));
    }

    @RequestMapping(value = "/accountAndExperience", method = RequestMethod.GET)
    public ResJsonTemplate accountAndExperience(@RequestParam(value = "username") String username){
        Account account = accountRepository.findByUsername(username);
        if(account == null){
            return new ResJsonTemplate<>("400","无此用户");
        }
        List<ProjectExperience> projectExperienceList = projectExperienceRepository.findByAccountId(account.getAccount_id());
        AccountAndExperience accountAndExperience = new AccountAndExperience(account.getName(),account.getAccount_id(),
                account.getMobile(),account.getEmail(),projectExperienceList);
        return new ResJsonTemplate<>("200",accountAndExperience);
    }
    /**
     * 更新用户账户信息
     * @param request
     * @param email
     * @param mobile
     * @param name
     * @return
     */
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResJsonTemplate updateAccount(HttpServletRequest request, @RequestParam(value = "email") String email,
                                         @RequestParam(value = "mobile") String mobile, @RequestParam(value = "name") String name){
        Account account = accountService.getAccount(request);
        if (account == null) {
            return new ResJsonTemplate<>("404","unauthorized");
        }
        account.setEmail(email);
        account.setMobile(mobile);
        account.setName(name);
        return accountService.updateInfo(account);
    }
    /**
     * login
     * @param accountInfo 以username和password为属性的json
     * @return 如果登陆成功则返回("200",accountDetail)
     *          如果失败则返回("404","unauthorized")
     */
    @RequestMapping(value = "/session", method = RequestMethod.POST)
    public ResJsonTemplate<Object> createAuthenticationToken(@RequestBody AccountInfo accountInfo){
        return accountService.login(accountInfo);
    }

    /**
     * 实名认证
     * @param request 获取header中的tokenID进行身份验证
     * @param userInfoDetail 添加身份认证的信息
     * @return token验证失败则（404，unauthorized）成功则（200，"实名认证成功"）
     */
    @RequestMapping(value = "/user/verification", method = RequestMethod.POST)
    public ResJsonTemplate UserInfoVerifacation(
            HttpServletRequest request, @RequestBody UserInfoDetail userInfoDetail) {
        Account account = accountService.getAccount(request);
        if (account == null) {
            return new ResJsonTemplate<>("404","unauthorized");
        }
        return userInfoDetailService.addUserInfoDetail(account,userInfoDetail);
    }

    /**
     * 新增项目经历
     * @param request token验证
     * @param file
     * @param project_name
     * @param project_region
     * @param project_address
     * @param project_text
     * @return
     */
    @RequestMapping(value = "/user/projectExperience", method = RequestMethod.POST)
    public ResJsonTemplate developerSkill(
            HttpServletRequest request, @RequestParam(value = "certificate", required = false) MultipartFile file,
            @RequestParam(value = "project_name") String project_name,
            @RequestParam(value = "project_region") String project_region,
            @RequestParam(value = "project_address") String project_address,
            @RequestParam(value = "project_text") String project_text
    ) throws IOException {
        Account account = accountService.getAccount(request);
        if (account == null) {
            return new ResJsonTemplate<>("404","unauthorized");
        }
        return projectExperienceService.addExperience(account,
                file,
                project_name,
                project_region,
                project_address,
                project_text);
    }

    /**
     * 更新项目经历
     * @param request token验证
     * @param file
     * @param project_name
     * @param project_region
     * @param project_address
     * @param project_text
     * @return
     */
    @RequestMapping(value = "/user/projectExperience", method = RequestMethod.PUT)
    public ResJsonTemplate updateDeveloperSkill(
            HttpServletRequest request, @RequestParam(value = "certificate", required = false) MultipartFile file,
            @RequestParam(value = "project_id") int project_id,
            @RequestParam(value = "project_name") String project_name,
            @RequestParam(value = "project_region") String project_region,
            @RequestParam(value = "project_address") String project_address,
            @RequestParam(value = "project_text") String project_text
    ) throws IOException {
        Account account = accountService.getAccount(request);
        if (account == null) {
            return new ResJsonTemplate<>("404","unauthorized");
        }
        return projectExperienceService.updateExperience(account,
                file,
                project_name,
                project_region,
                project_address,
                project_text,
                project_id);
    }


    /**
     * 获取项目经历
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/user/projectExperience", method = RequestMethod.GET)
    public ResJsonTemplate getDeveloperSkill(HttpServletRequest request)throws IOException
    {
        Account account = accountService.getAccount(request);
        if (account == null) {
            return new ResJsonTemplate<>("404","unauthorized");
        }
        return projectExperienceService.getExperience(account);
    }

    /**
     * 新增需求
     * @param request
     * @param requirement_name
     * @param requirement_type
     * @param need_manager
     * @param start_time
     * @param end_time
     * @param requirement_detail
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/requirement", method = RequestMethod.POST)
    public ResJsonTemplate createRequirement(
            HttpServletRequest request,
            @RequestParam(value = "requirement_name") String requirement_name,
            @RequestParam(value = "requirement_type") String requirement_type,
            @RequestParam(value = "need_manager") int need_manager,
            @RequestParam(value = "start_time") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start_time,
            @RequestParam(value = "end_time") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end_time,
            @RequestParam(value = "requirement_detail") String requirement_detail,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "project_id") int project_id) throws IOException {
        Account account = accountService.getAccount(request);
        if (account == null) {
            return new ResJsonTemplate<>("404","unauthorized");
        }
        return  requirementService.addRequirement(account,requirement_name,
                requirement_type,
                need_manager,
                start_time,
                end_time,
                requirement_detail,
                file,
                project_id);
    }

    /**
     * 获取需求
     * @param request
     * @return
     */
    @RequestMapping(value= "/requirement",method=RequestMethod.GET)
    public ResJsonTemplate getRequirement(HttpServletRequest request)
    {
        Account account = accountService.getAccount(request);
        if (account == null) {
            return new ResJsonTemplate<>("404","unauthorized");
        }
        return  requirementService.getReuirement(account);
    }

    /**
     * 删除需求
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/requirement/{id}", method = RequestMethod.DELETE)
    public ResJsonTemplate DeteleRequirement(HttpServletRequest request, @PathVariable Long id) {
        Account account = accountService.getAccount(request);
        if (account == null) {
            return new ResJsonTemplate<>("404","unauthorized");
        }
        Requirement requirement = requirementService.getRequirement(id);
        if (requirement == null){
            return new ResJsonTemplate<>("400","无此项目");
        }
        projectService.deleteProject(requirement.getProjectId());
        return requirementService.deleteRequirement(id);
    }

    /**
     * 删除项目经历
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/projectExperience/{id}", method = RequestMethod.DELETE)
    public ResJsonTemplate<String> deteleExperience(HttpServletRequest request, @PathVariable Long id) {
        Account account = accountService.getAccount(request);
        if (account == null) {
            return new ResJsonTemplate<>("404","unauthorized");
        }
        ProjectExperience projectExperience = projectExperienceRepository.findById(id);
        if(projectExperience == null){
            return new ResJsonTemplate<>("400", "无此项目经历");
        }
        if(!projectExperience.getAccountId().equals(account.getAccount_id())){
            return new ResJsonTemplate<>("401","无此权限");
        }
        projectExperienceRepository.delete(projectExperience);
        return new ResJsonTemplate<>("200","删除成功");

    }

    /**
     * 更新需求
     * @param request
     * @param requirement_name
     * @param requirement_type
     * @param need_manager
     * @param start_time
     * @param end_time
     * @param requirement_detail
     * @param file
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/updateRequirement/{id}", method = RequestMethod.POST)
    public ResJsonTemplate UpdateRequirement(HttpServletRequest request,
                                             @RequestParam(value = "requirement_name") String requirement_name,
                                             @RequestParam(value = "requirement_type") String requirement_type,
                                             @RequestParam(value = "need_manager") int need_manager,
                                             @RequestParam(value = "start_time") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start_time,
                                             @RequestParam(value = "end_time") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end_time,
                                             @RequestParam(value = "requirement_detail") String requirement_detail,
                                             @RequestParam(value = "file", required = false) MultipartFile file,
                                             @PathVariable Long id,
                                             @RequestParam(value = "requirement_state") int state) throws IOException {


        Account account = accountService.getAccount(request);
        if (account == null) {
            return new ResJsonTemplate<>("404","unauthorized");
        }
        return  requirementService.updateRequirement(account,id,requirement_name,
                requirement_type,
                need_manager,
                start_time,
                end_time,
                requirement_detail,
                file,
                state);
    }

    //获取详细需求
    @RequestMapping(value = "/requirement/{id}", method = RequestMethod.GET)
    public ResJsonTemplate GetRequirementDetail(HttpServletRequest request, @PathVariable Long id) {
//        Account account = accountService.getAccount(request);
//        if (account == null) {
//            return new ResJsonTemplate<>("404","unauthorized");
//        }
        Requirement requirement = requirementService.getRequirement(id);
        List<DevEnrollInfo> devEnrollInfos = devEnrollInfoRepository.findByProjectId(requirement.getProjectId());
        if(devEnrollInfos == null){
            devEnrollInfos = new LinkedList<>();
            return new ResJsonTemplate<>("200",devEnrollInfos);
        }
        List<DeveloperDto> developerDtos = new LinkedList<>();
        List<Account> accounts = new LinkedList<>();
        for (DevEnrollInfo devEnrollInfo : devEnrollInfos) {
            accounts.add(accountRepository.findByUsername(devEnrollInfo.getUsername()));
        }
        for (Account account : accounts) {
            developerDtos.add(new DeveloperDto(account.getUsername(),account.getEmail(),account.getMobile()));
        }
        ReuireAndDeveloperDto reuireAndDeveloperDto = new ReuireAndDeveloperDto();
        reuireAndDeveloperDto.setDeveloperList(developerDtos);
        reuireAndDeveloperDto.setRequirement(requirement);
        return new ResJsonTemplate<>("200", reuireAndDeveloperDto);
    }

    //报名加入项目
    @RequestMapping(value = "/requirement/{id}/enroll", method = RequestMethod.POST)
    public ResJsonTemplate EnrollProject(HttpServletRequest request, @PathVariable Long id) {
        Account account = accountService.getAccount(request);
        if (account == null) {
            return new ResJsonTemplate<>("404","unauthorized");
        }
        Requirement requirement = requirementService.getRequirement(id);
        if(requirement!=null)
        {
            if(Objects.equals(requirement.getCreatorId(), account.getAccount_id()))
            {
                return new ResJsonTemplate<>("400","该项目为自己创建，不能报名");
            }
            id = requirement.getProjectId();
            DevEnrollInfo devEnrollInfo = new DevEnrollInfo(account.getUsername(), id);
            return requirementService.addEnrollInfo(devEnrollInfo);
        }
        else
        {
            return new ResJsonTemplate<>("404","项目不存在");
        }
    }

    //获取所有需求
    @RequestMapping(value = "/requirements", method = RequestMethod.GET)
    public ResJsonTemplate getRequirement() {
        return requirementService.getRequirement();
    }

    //获取账号报名的项目
    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public ResJsonTemplate getProjectList(HttpServletRequest request) {
        Account account = accountService.getAccount(request);
        if (account == null) {
            return new ResJsonTemplate<>("404","unauthorized");
        }
        return projectService.getProjectList(account.getUsername());
//        return requirementService.getMyRequirement(account);
        // return projectService.getProjectList(account.getUsername());
    }

    //注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResJsonTemplate register(@RequestBody LoginAccountDto addedUser) {
        return accountService.register(addedUser);
    }
//
//    @PreAuthorize("hasRole('user')")
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public Account getUserByUsername(@RequestParam(value = "username") java.lang.String username) {
//        return accountRepository.findByUsername(username);
//    }

}
