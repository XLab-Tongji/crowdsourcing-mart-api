package com.lab409.crowdingsourcing.dto;

import com.lab409.crowdingsourcing.entity.Requirement;

import java.util.List;

public class ReuireAndDeveloperDto {
    private Requirement requirement;
    private List<DeveloperDto> developerDtoList;

    public Requirement getRequirement() {
        return requirement;
    }

    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
    }

    public List<DeveloperDto> getDeveloperList() {
        return developerDtoList;
    }

    public void setDeveloperList(List<DeveloperDto> developerDtoList) {
        this.developerDtoList = developerDtoList;
    }
}
