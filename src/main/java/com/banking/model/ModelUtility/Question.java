package com.banking.model.ModelUtility;

public enum Question {
    Q1("In which city were you born?"),
    Q2("What is the middle name of your mother?"),
    Q3("What is your favorite band?");

    private String question;
    private Question(String question) {
    this.question = question;
    }

    public String getQuestion(){
        return this.question;
    }
}
