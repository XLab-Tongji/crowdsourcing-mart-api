package com.lab409.crowdingsourcing.service;

import com.lab409.crowdingsourcing.entity.Account;
import com.lab409.crowdingsourcing.util.ResJsonTemplate;
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
    public ResJsonTemplate getExperience(Account account) throws IOException;

    public ResJsonTemplate updateExperience(Account account,
                                            MultipartFile file,
                                            String project_name,
                                            String project_region,
                                            String project_address,
                                            String project_text,
                                            int projecr_id);
}
