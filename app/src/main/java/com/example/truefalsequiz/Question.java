package com.example.truefalsequiz;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


//make a method that shuffles the list of correct and incorrect answers
public class Question {
    private String question;
    private String correct_answer;
    private List<String> incorrect_answers;
    private String type;

    public Question() {}

    public Question(String question, String correct_answer, List<String> incorrect_answers, String type) {
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public List<String> getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setIncorrect_answers(List<String> incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getRandomizedAnswers(){
        List<String> toReturn = new ArrayList<String>();
        toReturn.add(correct_answer);
        toReturn.addAll(incorrect_answers);
        Collections.shuffle(toReturn);
        return toReturn;

    }

    public boolean checkAnswer(String answer){
        if (answer.equals(correct_answer)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", correct_answer='" + correct_answer + '\'' +
                ", incorrect_answers=" + incorrect_answers +
                ", type='" + type + '\'' +
                '}';
    }
}
