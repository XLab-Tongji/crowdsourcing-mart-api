package com.crazy.estimation.bean;

/**
 * Created by xuawai on 03/05/2017.
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "requirement")
public class Requirement {
    @Id
    private String id;

    private Description description;

    private List<Transaction> transactions;

    private List<Entity> entities;

    private VAF vaf;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getId() {
        return id;

    }

    public void setId(String id) {
        this.id = id;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public VAF getVaf() {
        return vaf;
    }

    public void setVaf(VAF vaf) {
        this.vaf = vaf;
    }
}
