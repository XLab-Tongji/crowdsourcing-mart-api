package com.crazy.service.impl;

import com.crazy.entity.Account;
import com.crazy.entity.ProjectExperience;
import com.crazy.repository.ProjectExperienceRepository;
import com.crazy.service.ProjectExperienceService;
import com.crazy.util.ResJsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
}
