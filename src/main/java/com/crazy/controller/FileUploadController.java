package com.crazy.controller;

import com.crazy.service.FileService;
import com.crazy.util.ResJsonTemplate;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传包括头像 附件 等
 * Created by SHIKUN on 2016/11/2.
 */
@RestController
@RequestMapping(value = "/api/file")
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/icon", method = RequestMethod.POST)
    @ResponseBody
    public ResJsonTemplate updateHeadIcon(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "username") String username) {
        return fileService.insertFile(file, "src/main/webapp", username);

    }


}
