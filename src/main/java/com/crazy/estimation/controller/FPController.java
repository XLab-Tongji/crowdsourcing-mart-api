package com.crazy.estimation.controller;

import com.crazy.estimation.service.FPService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xuawai on 15/05/2017.
 */
@RestController
@RequestMapping(value = "/fp")
public class FPController {

    @Autowired
    FPService fpService;

    @RequestMapping(value = "/ufp/{id}", method = RequestMethod.GET)
    public int calculateUFP( @PathVariable String id) {

        return fpService.calculateUFP(id);

    }

    @RequestMapping(value = "/fp/{id}", method = RequestMethod.POST)
    public int calculateFP(@PathVariable String id, @RequestBody JSONObject jsonObject){
        int ufp = fpService.calculateUFP(id);
        //其他参数，如语言类型等等
        String developmentType = jsonObject.getString("developmentType");
        String developmentPlatform = jsonObject.getString("developmentPlatform");
        String languageType = jsonObject.getString("languageType");
        String DBMS_Used = jsonObject.getString("DBMS_Used");
        //这一部分是数字，要转成float之类
        String RELY = jsonObject.getString("RELY");
        String CPLX = jsonObject.getString("CPLX");
        String TIME = jsonObject.getString("TIME");
        String SCED = jsonObject.getString("SCED");

        //算法
        double afp = ufp;
        // Development Type
        if(developmentType.equalsIgnoreCase("New Development")){
            afp = afp * 0.857;
        }else if(developmentType.equalsIgnoreCase("Enhancement")){
            afp = afp * 0.858;
        }else if(developmentType.equalsIgnoreCase("Re-development")){
            afp = afp * 0.863;
        }
        System.out.println("Adjusted by development type:"+afp);
        // Development Platform
        if(developmentPlatform.equalsIgnoreCase("PC")){
            afp = afp * 0.61;
        }else if(developmentPlatform.equalsIgnoreCase("MR")){
            afp = afp * 1.01;
        }else if(developmentPlatform.equalsIgnoreCase("MF")){
            afp = afp * 1.06;
        }
        System.out.println("Adjusted by development platform:"+afp);
        // Language Type
        if(languageType.equalsIgnoreCase("3GL")){
            afp = afp * 1.06;
        }else if(languageType.equalsIgnoreCase("4GL")){
            afp = afp * 0.87;
        }
        System.out.println("Adjusted by language type:"+afp);

        // COCOMO Cost Driver
        double COCOMOKFs[] = {1,1,1,1};
        COCOMOKFs[0] = Double.parseDouble(RELY);
        COCOMOKFs[1] = Double.parseDouble(CPLX);
        COCOMOKFs[2] = Double.parseDouble(TIME);
        COCOMOKFs[3] = Double.parseDouble(SCED);
        for(int i=0;i<4;i++){
            if(COCOMOKFs[i] > 0 && COCOMOKFs[i] != Double.NaN){
                afp = afp * COCOMOKFs[i];
            }
        }
        System.out.println("Adjusted by cocomo driver:"+afp);

        return (int)afp;


    }
}
