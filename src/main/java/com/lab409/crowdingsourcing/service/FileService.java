package com.lab409.crowdingsourcing.service;

import com.lab409.crowdingsourcing.util.ResJsonTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by SHIKUN on 2016/11/2.
 */
public interface FileService {

    //文件上传
    ResJsonTemplate insertFile(MultipartFile file, String path, String username);

}
