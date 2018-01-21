package com.lab409.crowdingsourcing.service;

import com.lab409.crowdingsourcing.entity.Account;
import com.lab409.crowdingsourcing.entity.DevEnrollInfo;
import com.lab409.crowdingsourcing.entity.Requirement;
import com.lab409.crowdingsourcing.util.ResJsonTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * Created by jieping on 2017-05-24.
 */
public interface RequirementService {
    public ResJsonTemplate addRequirement(Account account,
                                          String requirement_name,
                                          String requirement_type,
                                          int need_manager,
                                          Date start_time,
                                          Date end_time,
                                          String requirement_detail,
                                          MultipartFile file,
                                          int project_id) throws IOException;
    public ResJsonTemplate getReuirement(Account account) ;
    public ResJsonTemplate deleteRequirement(Long id);
    public ResJsonTemplate getRequirement();
    public ResJsonTemplate updateRequirement(Account account,
                                             Long reId,
                                             String requirement_name,
                                             String requirement_type,
                                             int need_manager,
                                             Date start_time,
                                             Date end_time,
                                             String requirement_detail,
                                             MultipartFile file,
                                             int state) throws IOException;
    public Requirement getRequirement(Long id);
    public ResJsonTemplate addEnrollInfo(DevEnrollInfo devEnrollInfo);
    public ResJsonTemplate getMyRequirement(Account account);
}
