package com.crazy.estimation.bean;

import java.util.List;

/**
 * Created by xuawai on 04/05/2017.
 */
public class Entity {

    private String logicalFileName;

    private String logicalFieldName;

    //前几个字母都是大写的字段存入momgo中，读取的时候会变成rets。RET内部的两个逻辑字段也有同样的情况。不知道原因。
    private List<RET> RETs;

    private String logicalFileType;

    public String getLogicalFileName() {
        return logicalFileName;
    }

    public void setLogicalFileName(String logicalFileName) {
        this.logicalFileName = logicalFileName;
    }

    public String getLogicalFieldName() {
        return logicalFieldName;
    }

    public void setLogicalFieldName(String logicalFieldName) {
        this.logicalFieldName = logicalFieldName;
    }

    public List<RET> getRETs() {
        return RETs;
    }

    public void setRETs(List<RET> RETs) {
        this.RETs = RETs;
    }

    public String getLogicalFileType() {
        return logicalFileType;
    }

    public void setLogicalFileType(String logicalFileType) {
        this.logicalFileType = logicalFileType;
    }
}
