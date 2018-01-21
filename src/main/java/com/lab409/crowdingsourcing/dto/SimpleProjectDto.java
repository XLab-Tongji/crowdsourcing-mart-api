package com.lab409.crowdingsourcing.dto;

public class SimpleProjectDto {

    private Long project_id;
    private String project_name;
    private String project_type;
    private int project_state;

    public SimpleProjectDto(Long project_id, String project_name, String project_type, int project_state) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.project_type = project_type;
        this.project_state = project_state;
    }

    public int getProject_state() {
        return project_state;
    }

    public void setProject_state(int project_state) {
        this.project_state = project_state;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public Long getProject_id() {
        return project_id;
    }

    @Override
    public String toString() {
        return "project id: " + this.project_id + " , project name: " + this.project_name
                + " , project type: " + this.project_type + " , project state: " + this.project_state;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }
}
