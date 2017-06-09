package com.crazy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequirementDetail {
    private String requirement_name ;
    private String requirement_type ;
    private Date start_time;
    private Date end_time;
    private int need_manager;
    private String requirement_detail;
    private byte[] file;
    private List<SimpleAccount> developers = new ArrayList<>();
    private int enroll_count;
    private boolean is_enroll;

    public int getEnroll_count() {
        return enroll_count;
    }

    public void setEnroll_count(int enroll_count) {
        this.enroll_count = enroll_count;
    }

    public String getRequirementName() {
        return requirement_name;
    }

    public void setRequirementName(String requirementName) {
        this.requirement_name = requirementName;
    }

    public String getRequirementType() {
        return requirement_type;
    }

    public void setRequirementType(String requirementType) {
        this.requirement_type = requirementType;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public int getNeed_manager() {
        return need_manager;
    }

    public void setNeed_manager(int need_manager) {
        this.need_manager = need_manager;
    }

    public String getRequirement_detail() {
        return requirement_detail;
    }

    public void setRequirement_detail(String requirement_detail) {
        this.requirement_detail = requirement_detail;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public List<SimpleAccount> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<SimpleAccount> developers) {
        this.developers = developers;
    }
}
