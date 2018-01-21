package com.lab409.crowdingsourcing.service.impl;

import com.lab409.crowdingsourcing.entity.Account;
import com.lab409.crowdingsourcing.entity.ProjectExperience;
import com.lab409.crowdingsourcing.repository.ProjectExperienceRepository;
import com.lab409.crowdingsourcing.service.ProjectExperienceService;
import com.lab409.crowdingsourcing.util.ResJsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by jieping on 2017-05-24.
 */
@Service
public class ProjectExperienceServiceImpl implements ProjectExperienceService{
    @Autowired
    private ProjectExperienceRepository projectExperienceRepository;
    @Override
    public ResJsonTemplate addExperience(Account account,
                                         MultipartFile file,
                                         String project_name,
                                         String project_region,
                                         String project_address,
                                         String project_text) throws IOException {
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

    @Override
    public ResJsonTemplate getExperience(Account account) {
       List<ProjectExperience> target = projectExperienceRepository.findByAccountId(account.getAccount_id());
        return new ResJsonTemplate("200",target);

    }

    @Override
    public ResJsonTemplate updateExperience(Account account, MultipartFile file,
                                            String project_name, String project_region, String project_address, String project_text, int projecr_id) {
        ProjectExperience projectExperience = new ProjectExperience();
        projectExperience.setAccountId(account.getAccount_id());
        projectExperience.setProjectAddress(project_address);
        projectExperience.setProjectName(project_name);
        projectExperience.setProjectRegion(project_region);
        projectExperience.setProjectText(project_text);
        projectExperience.setId((long) projecr_id);
        if (file != null) {
            byte[] data = new byte[0];
            try {
                data = new byte[file.getInputStream().available()];
                file.getInputStream().read(data);
                projectExperience.setCertificate(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ProjectExperience temp = projectExperienceRepository.save(projectExperience);
        return new ResJsonTemplate("201", temp);
    }
}
