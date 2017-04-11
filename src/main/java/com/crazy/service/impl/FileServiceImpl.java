package com.crazy.service.impl;

import com.crazy.JPA.AttachRepository;
import com.crazy.entity.Attach;
import com.crazy.mapper.AttachMapper;
import com.crazy.service.FileService;
import com.crazy.util.ConvertJson;
import com.crazy.util.FileFunc;
import com.crazy.util.ResJsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by SHIKUN on 2016/11/2.
 */

@Service
public class FileServiceImpl implements FileService {


    @Autowired
    private AttachMapper attachMapper;
    @Autowired
    private AttachRepository attachRepository;


    @Autowired
    private ConvertJson convertJson;


    @Override
    public ResJsonTemplate insertFile(MultipartFile file, String path, String username) {

        Attach attach = new Attach();

        attach = FileFunc.fileSave(file, path);
        attach.setUsername(username);

        try {
/*
            return new ResJsonTemplate("200", attachMapper.insertFile(attach.getAttach_url(), attach.getAttach_name(),
                    attach.getSize(),attach.getAttach_type(),
                    convertJson.Map2Json(attach.getMeta_data()), username, attach.getIs_del()));
*/

            return new ResJsonTemplate("200",attachRepository.save(attach));

        } catch (Exception ex) {

            System.out.println(ex);
            return new ResJsonTemplate("500", "插入错误");
        }


    }

}


