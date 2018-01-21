package com.lab409.crowdingsourcing.service.impl;

import com.lab409.crowdingsourcing.dto.SimpleRequirementDto;
import com.lab409.crowdingsourcing.entity.Account;
import com.lab409.crowdingsourcing.entity.DevEnrollInfo;
import com.lab409.crowdingsourcing.entity.Requirement;
import com.lab409.crowdingsourcing.repository.DevEnrollInfoRepository;
import com.lab409.crowdingsourcing.repository.RequirementRepository;
import com.lab409.crowdingsourcing.service.RequirementService;
import com.lab409.crowdingsourcing.util.ResJsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by jieping on 2017-05-24.
 */
@Service
public class RequirementServiceImpl implements RequirementService {
    @Autowired
    private RequirementRepository requirementRepository;
    @Autowired
    private DevEnrollInfoRepository devEnrollInfoRepository;

    @Override
    public ResJsonTemplate deleteRequirement(Long id){
        if (!requirementRepository.exists(id)) {
            return new ResJsonTemplate<>("400", "删除失败");
        }
        Requirement target = requirementRepository.findById(id);
        requirementRepository.delete(target);
        //    requirementRepository.deleteById(id);
        return new ResJsonTemplate<>("200", "删除成功");
    }
    @Override
    public ResJsonTemplate addRequirement(Account account, String requirement_name,
                                          String requirement_type, int need_manager,
                                          Date start_time, Date end_time,
                                          String requirement_detail, MultipartFile file,
                                          int project_id) throws IOException {
        Requirement requirement = new Requirement();
        requirement.setCreatorId(account.getAccount_id());
        requirement.setrequirementName(requirement_name);
        requirement.setrequirementType(requirement_type);
        requirement.setRequirementDetail(requirement_detail);
        requirement.setNeedManager(need_manager);
        requirement.setStartTime(start_time);
        requirement.setEndTime(end_time);

        requirement.setProjectId((long) project_id);
        if (file != null) {
            byte[] data = new byte[file.getInputStream().available()];
            file.getInputStream().read(data);
            requirement.setFile(data);
        }
        requirementRepository.save(requirement);
        return new ResJsonTemplate<>("201", "创建需求成功");
    }

    @Override
    public ResJsonTemplate getReuirement(Account account) {
        List<Requirement> requirements = requirementRepository.findByCreatorId(account.getAccount_id());
        ArrayList<SimpleRequirementDto> simpleRequirements = new ArrayList<SimpleRequirementDto>();
        for (int i = 0; i < requirements.size(); i++) {
            SimpleRequirementDto s = new SimpleRequirementDto();
            s.setRequirement_id(requirements.get(i).getId());
            s.setRequirement_type(requirements.get(i).getrequirementType());
            s.setRequirement_name(requirements.get(i).getrequirementName());
            s.setRequirement_state(requirements.get(i).getRequirementState());
            simpleRequirements.add(s);
        }
        return new ResJsonTemplate<>("200", simpleRequirements);
    }

    @Override
    public ResJsonTemplate getRequirement() {
        List<Requirement> requirements = (List<Requirement>) requirementRepository.findAll();
        return new ResJsonTemplate<>("200", requirements);
    }

    @Override
    public ResJsonTemplate updateRequirement(Account account, Long reId, String requirement_name,
                                             String requirement_type, int need_manager, Date start_time,
                                             Date end_time, String requirement_detail, MultipartFile file,
                                             int state) throws IOException {
        Requirement requirement = requirementRepository.findById(reId);
        if(requirement==null)
        {
            return new ResJsonTemplate<>("404", "需求不存在");
        }
        if(!Objects.equals(requirement.getCreatorId(), account.getAccount_id()))
        {
            return new ResJsonTemplate<>("400", "该需求非该用户创建，无法更新");
        }
        requirement.setrequirementName(requirement_name);
        requirement.setrequirementType(requirement_type);
        requirement.setRequirementDetail(requirement_detail);
        requirement.setNeedManager(need_manager);
        requirement.setStartTime(start_time);
        requirement.setEndTime(end_time);
        requirement.setRequirementState(state);
        if (file != null) {
            byte[] data = new byte[file.getInputStream().available()];
            file.getInputStream().read(data);
            requirement.setFile(data);
        }

        requirementRepository.save(requirement);
        return new ResJsonTemplate<>("200", "更新成功");
    }

    @Override
    public Requirement getRequirement(Long id) {
        return requirementRepository.findById(id);
    }

    @Override
    public ResJsonTemplate addEnrollInfo(DevEnrollInfo devEnrollInfo) {
        List<DevEnrollInfo> devEnrollInfos = devEnrollInfoRepository.findByUsername(devEnrollInfo.getUsername());
        for(int i = 0; i<devEnrollInfos.size();i++)
        {
            if(devEnrollInfos.get(i).getProjectId()==devEnrollInfo.getProjectId())
            {
                return new ResJsonTemplate<>("401","用户已报名该项目");
            }
        }
        devEnrollInfoRepository.save(devEnrollInfo);
        return new ResJsonTemplate<>("200","用户报名成功");
    }

    @Override
    public ResJsonTemplate getMyRequirement(Account account) {
        List<Requirement> result = requirementRepository.findByCreatorId(account.getAccount_id());
        List<DevEnrollInfo> enrolls = devEnrollInfoRepository.findByUsername(account.getUsername());
        if (enrolls == null) {
            return new ResJsonTemplate<>("200",result);
        }
        for(int i = 0;i<enrolls.size();i++)
        {
            Requirement temp = requirementRepository.findById(enrolls.get(i).getProjectId());
            if(!result.contains(temp))
            {
                result.add(temp);
            }
        }
        return new ResJsonTemplate<>("200",result);
    }


}