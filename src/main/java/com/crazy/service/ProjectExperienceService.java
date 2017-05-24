package com.crazy.service;

import com.crazy.entity.Account;
import com.crazy.util.ResJsonTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by jieping on 2017-05-24.
 */
public interface ProjectExperienceService {
    public ResJsonTemplate addExperience(Account account,
                                         MultipartFile file,
                                         String project_name,
                                         String project_region,
                                         String project_address,
                                         String project_text) throws IOException;
}
