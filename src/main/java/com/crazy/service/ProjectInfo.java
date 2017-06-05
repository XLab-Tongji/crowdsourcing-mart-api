package com.crazy.service;

public class ProjectInfo {
    private Long projectId;
    private String projectName;
    private String projectType;
    private int projectState;

    public ProjectInfo() {
    }

    public ProjectInfo(Long projectId, String projectName, String projectType, int projectState) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectType = projectType;
        this.projectState = projectState;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public int getProjectState() {
        return projectState;
    }

    public void setProjectState(int projectState) {
        this.projectState = projectState;
    }
}
