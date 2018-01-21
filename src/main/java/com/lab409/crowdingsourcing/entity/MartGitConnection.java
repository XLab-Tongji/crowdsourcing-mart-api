package com.lab409.crowdingsourcing.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by yuhao on 2017/4/13.
 */
@Entity
@Table(name = "MartGitConnection")
public class MartGitConnection {
    @Id
    private Long martAccountId;
    private Long gitAccountId;

    public MartGitConnection(Long martAccountId, Long gitAccountId) {
        this.martAccountId = martAccountId;
        this.gitAccountId = gitAccountId;
    }

    public Long getMartAccountId() {
        return martAccountId;
    }

    public void setMartAccountId(Long martAccountId) {
        this.martAccountId = martAccountId;
    }

    public Long getGitAccountId() {
        return gitAccountId;
    }

    public void setGitAccountId(Long gitAccountId) {
        this.gitAccountId = gitAccountId;
    }
}
