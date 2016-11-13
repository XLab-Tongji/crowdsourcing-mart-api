package com.crazy.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**页码类
 * Created by SHIKUN on 2016/10/31.
 */
@Service
public class Paging {

//    private int pagesize;



//    @Autowired
//    public Paging( @Value("#{new Integer(10)}") int pagesize) {
//        this.pagesize = pagesize;
//    }

    /*
    得到总页数
    count是查询的总数量
    pageNumber返回的总页码数
     */

    public int getTotalPage(int count,int pageSize) {

        int pagenumber;

        if (count % pageSize == 0) {
            return pagenumber = count / pageSize;
        }else{
            return pagenumber = count / pageSize + 1;
        }

    }

    /*页码转换
    page是前端传进的页码数
    pagesize是每页显示的数量
    StartPage是传进数据库操作的查询起点

     */


    public int convertStartPage(int page,int pageSize) {

        int StartPage=0;

        if (page == 1) {
            return StartPage;
        }else{

            StartPage = (page - 1) * pageSize;
            return StartPage;
        }

    }





}
