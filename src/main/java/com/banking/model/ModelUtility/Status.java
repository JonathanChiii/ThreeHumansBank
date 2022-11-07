package com.banking.model.ModelUtility;

public enum Status {
    Enabled(1),
    Disabled(0);

    private int statusCode;

    private Status(int status){
        this.statusCode = status;
    }

    public Integer getStatusCode(){
        return this.statusCode;
    }
}
