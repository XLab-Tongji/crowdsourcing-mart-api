package com.crazy.util;

import org.springframework.stereotype.Service;

/**分页实现
 * Created by SHIKUN on 2016/10/30.
 */
@Service
public class Pagination {

    //每页显示数据
    private int pageSize;

    //总页数
    private int pageAll;

    public int getPageAll(int Data,int pageSize){

        if(Data%pageSize==0){

            this.pageAll = Data / pageSize;

        }else{

            this.pageAll = Data / pageSize + 1;
        }

        return this.pageAll;
    }













}
