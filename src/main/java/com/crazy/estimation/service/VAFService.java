package com.crazy.estimation.service;

import com.crazy.estimation.DAO.VAFDao;
import com.crazy.estimation.bean.VAF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xuawai on 16/06/2017.
 */
@Service
public class VAFService {

    @Autowired
    private VAFDao vafDAO;

    public void add(String id, VAF vaf) {
        this.vafDAO.add(id, vaf);
    }

}
