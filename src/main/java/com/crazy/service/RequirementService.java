package com.crazy.service;

import com.crazy.entity.Account;
import com.crazy.entity.DevEnrollInfo;
import com.crazy.entity.Requirement;
import com.crazy.util.ResJsonTemplate;
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
                                          MultipartFile file) throws IOException;
    public ResJsonTemplate getReuirement(Account account) ;

    public ResJsonTemplate getRequirement();
    public ResJsonTemplate updateRequirement(Account account,
                                             Long reId,
                                             String requirement_name,
                                             String requirement_type,
                                             int need_manager,
                                             Date start_time,
                                             Date end_time,
                                             String requirement_detail,
                                             MultipartFile file) throws IOException;
    public Requirement getRequirement(Long id);
    public ResJsonTemplate addEnrollInfo(DevEnrollInfo devEnrollInfo);
}