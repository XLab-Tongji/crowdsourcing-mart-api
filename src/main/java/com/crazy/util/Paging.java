package com.crazy.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by SHIKUN on 2016/10/31.
 */
@Service
public class Paging {

    private int pagesize=10;



    public int getTotalPage(int count) {

        int pagenumber;

        if (count % pagesize == 0) {
            return pagenumber = count / pagesize;
        }else{
            return pagenumber = count / pagesize + 1;
        }

    }

    /*页码转换
    page是前端传进的页码数
    pagesize是每页显示的数量
    StartPage是传进数据库操作的查询起点

     */

    public int getPagesize() {

        return pagesize;
    }

    public int convertStartPage(int page) {

        int StartPage=0;

        if (page == 1) {
            return StartPage;
        }else{
            return StartPage =(page-1)* pagesize + 1;
        }



    }





}
