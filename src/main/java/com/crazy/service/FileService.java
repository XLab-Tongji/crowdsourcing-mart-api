package com.crazy.service;

import com.crazy.entity.Attach;
import com.crazy.util.ResJsonTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by SHIKUN on 2016/11/2.
 */
public interface FileService {

    //文件上传
    ResJsonTemplate insertFile(MultipartFile file, String path, Long account_id);

}
