package com.example.truefalsequiz;

import java.util.List;

public class Quiz {

    private List<Question> questions;

    private int score;
    private int currentQ;

    public Quiz(List<Question> questions){
        this.questions = questions;
    }
}
