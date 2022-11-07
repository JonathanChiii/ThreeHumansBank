package com.banking.model.ModelUtility;

public enum AccountType {
    SB(1),
    CA(2);

    private int typeCode;

    private AccountType(int type){
        this.typeCode = type;
    }

    public Integer getTypeCode(){
        return this.typeCode;
    }
}
