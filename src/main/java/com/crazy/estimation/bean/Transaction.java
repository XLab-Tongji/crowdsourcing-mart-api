package com.crazy.estimation.bean;

import java.util.List;

/**
 * Created by xuawai on 03/05/2017.
 */
public class Transaction {

    private String transactionName;

    private String transactionType;

    private String transactionDescription;

    private List<Step> steps;

    public String countRepeatField;

    public String getCountRepeatField() {
        return countRepeatField;
    }

    public void setCountRepeatField(String countRepeatField) {
        this.countRepeatField = countRepeatField;
    }

    private String regulationOfSameLogic;

    private String regulationOfReturningStatus;

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public String getRegulationOfSameLogic() {
        return regulationOfSameLogic;
    }

    public void setRegulationOfSameLogic(String regulationOfSameLogic) {
        this.regulationOfSameLogic = regulationOfSameLogic;
    }

    public String getRegulationOfReturningStatus() {
        return regulationOfReturningStatus;
    }

    public void setRegulationOfReturningStatus(String regulationOfReturningStatus) {
        this.regulationOfReturningStatus = regulationOfReturningStatus;
    }
}
