package com.example.truefalsequiz;

import java.util.List;

public class Quiz {

    private List<Question> questions;

    private int score;
    private int currentQ;

    public Quiz(List<Question> questions){
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void nextQuestion() {
        currentQ++;
    }

    public void setCurrentQ(int i) { currentQ = i;}

    public Question getQuestion(int index){
        return questions.get(index);
    }

    public void scoreUp() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public int getCurrentQ() {
        return currentQ;
    }

    public boolean isThereAnotherQ() {
        if (currentQ + 1 > questions.size())
        {return false;}
        else{return true;}
    }

    public boolean checkAnswer(String userAnswer){
        if (userAnswer.equals(questions.get(currentQ - 1).getCorrect_answer())){
            return true;
        }
        else{
            return false;
        }
    }

}
