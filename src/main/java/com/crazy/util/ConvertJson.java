package com.crazy.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Map转Json
 * Created by SHIKUN on 2016/9/30.
 */
@Service
public class ConvertJson {

    String json = null;
    ObjectMapper mapper = new ObjectMapper();

    public  String Map2Json(Map<String,String> mapInput) {

        try {
            json = mapper.writeValueAsString(mapInput);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return json;
    }

    public String List2Json(List listInput) {

        try {
            json = mapper.writeValueAsString(listInput);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return json;
    }

    public List Json2List(String json) {
        List listRes = null;
        try {
             listRes = mapper.readValue(json, new TypeReference<List>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listRes;
    }

}
