package com.crazy.util;

/**
 * Created by SHIKUN on 2016/10/14.
 */
public class ResJsonTemplate<T> {

    private String status;

    private T result;



    public ResJsonTemplate(String status, T result) {
        this.status = status;
        this.result = result;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
