package com.kth.foodtech.digitalfair.model;

import javax.persistence.*;


@Entity
public class Question {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String company;
    private String question;

    @Column(length=2000)
    private String options;

    private String correctAnswer;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
