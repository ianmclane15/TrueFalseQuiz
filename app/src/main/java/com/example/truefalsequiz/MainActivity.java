package com.example.truefalsequiz;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton1;
    private Button falseButton2;
    private Button falseButton3;
    private TextView question;
    private TextView score;
    private String string;
    private String string1;
    private String string2;
    private String string3;
    private String selectedAnswer;

    private Quiz quiz;

    public static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream stream = getResources().openRawResource(R.raw.questions);


        String jsonString = readTextFile(stream);
        // create a gson object
        Gson gson = new Gson();
        // read your json file into an array of questions
        Question[] questions =  gson.fromJson(jsonString, Question[].class);
        // convert your array to a list using the Arrays utility class
        List<Question> questionList = Arrays.asList(questions);
        // verify that it read everything properly
        Log.d(TAG, "onCreate: " + questionList.toString());
        quiz = new Quiz(questionList);
        wireWidgets();
        setOnClickListeners();
    }

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }
    private void setOnClickListeners(){
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiz.getQuestion().checkAnswer(trueButton.getText().toString());
            }
        });
        falseButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = trueButton.getText().toString();
            }
        });
        falseButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = trueButton.getText().toString();
            }
        });
        falseButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = trueButton.getText().toString();
            }
        });
    }

    private void setToTrueFalse() {
        falseButton2.setVisibility(View.GONE);
        falseButton3.setVisibility(View.GONE);
        // set the text for true & false buttons to True & False
        trueButton.setText("True");
        falseButton1.setText("False");
    }

    private void setToMultipleChoice(Question question) {
        // make them visible
        falseButton2.setVisibility(View.VISIBLE);
        falseButton3.setVisibility(View.VISIBLE);
        // set the answers to the buttons (can do randomly)
        trueButton.setText(//*set answers);
    }

    private void wireWidgets() {
        trueButton=findViewById(R.id.button_main_true);
        falseButton1=findViewById(R.id.button_main_false1);
        falseButton2=findViewById(R.id.button_main_false2);
        falseButton3=findViewById(R.id.button_main_false3);
        question=findViewById(R.id.textView_main_question);
        score=findViewById(R.id.textView_main_score);

    }

}
