package com.crazy.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**Map转Json
 * Created by SHIKUN on 2016/9/30.
 */
@Service
public class ConvertJson {

    public  String Map2Json(Map<String,String> mapInput) {
        String json=null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(mapInput);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return json;
    }

}
