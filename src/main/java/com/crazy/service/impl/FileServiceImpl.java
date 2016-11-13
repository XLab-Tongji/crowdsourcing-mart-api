package com.crazy.service.impl;

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
    private ConvertJson convertJson;


    @Override
    public ResJsonTemplate insertFile(MultipartFile file, String path, Long account_id) {

        Attach attach = new Attach();

        attach = FileFunc.fileSave(file, path);
        attach.setAccount_id(account_id);

        try {
            return new ResJsonTemplate("200", attachMapper.insertFile(attach.getAttach_url(), attach.getAttach_name(),
                    attach.getSize(),attach.getAttach_type(),
                    convertJson.Map2Json(attach.getMeta_data()), account_id, attach.getIs_del()));

        } catch (Exception ex) {
            System.out.println(ex);
            return new ResJsonTemplate("500", "插入错误");
        }


    }

}
