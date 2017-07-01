package com.crazy.estimation.bean;

import java.util.List;

/**
 * Created by xuawai on 03/05/2017.
 */
public class Step {

    private String stepName;

    private List<ConcerningDataSet> concerningDataSets;

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public List<ConcerningDataSet> getConcerningDataSets() {
        return concerningDataSets;
    }

    public void setConcerningDataSets(List<ConcerningDataSet> concerningDataSets) {
        this.concerningDataSets = concerningDataSets;
    }
}
