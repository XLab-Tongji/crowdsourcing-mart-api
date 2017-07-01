package com.crazy.estimation.service;

import com.crazy.estimation.DAO.DescriptionDAO;
import com.crazy.estimation.bean.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xuawai on 03/05/2017.
 */
@Service
public class DescriptionService {
    @Autowired
    private DescriptionDAO descriptionDAO;

    public void add(String id, Description description){
        this.descriptionDAO.add(id, description);
    }
}
