package com.lab409.crowdingsourcing.util;

import com.lab409.crowdingsourcing.entity.Attach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Map;
import java.util.UUID;

/**
 * 文件保存服务类
 * Created by SHIKUN on 2016/11/2.
 */

@Service
public class FileFunc {
    @Autowired
    private static  ConvertJson convertJson;
    public static Attach fileSave(MultipartFile file, String path) {

        Attach attach = new Attach();

        if (file == null) {
            System.out.println("没有此文件");
        }

        try {
            if (!new File(path).isDirectory()) {
                new File(path).mkdirs();
            }
            String filename = URLDecoder.decode(file.getOriginalFilename(), "UTF-8");
            String savefilename = renameFileWithUUID(filename);
            String finalPath = new StringBuffer(path).append("/").append(savefilename).toString();
            FileOutputStream fos = new FileOutputStream(finalPath);
            InputStream is = file.getInputStream();
            byte[] buffer = new byte[1024 * 1024];
            int byteread = 0;
            while ((byteread = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteread);
                fos.flush();

            }

            fos.close();
            is.close();



            attach.setAttach_name(savefilename);
            attach.setAttach_url(finalPath);
            attach.setSize(((Long) file.getSize()).intValue());
            attach.setIs_del(false);
           Map<String,String> meta_data = convertJson.Json2Map(attach.getMeta_data());
            meta_data.put("ContentType", file.getContentType());
            attach.setMeta_data(convertJson.Map2Json(meta_data));
         //   attach.getMeta_data().put("ContentType", file.getContentType());
            Map<String, String> result = convertJson.Json2Map(attach.getMeta_data());
            //attach.setAttach_name("服务器文件");


        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return attach;
        }


    /**
     * 用UUID给文件命名
     *
     * @param fileName the fileName
     * @return String
     */
    private static String renameFileWithUUID(String fileName) {
        int index = fileName.lastIndexOf(".");
        String uuid = UUID.randomUUID().toString().substring(0, 4);
        if (index == -1) {
            return new StringBuffer(fileName).append("-").append(uuid).toString();
        } else {
            return new StringBuffer(fileName.substring(0, index)).append("-").append(uuid)
                    .append(fileName.substring(index, fileName.length())).toString();
        }
    }

}
