package com.example.truefalsequiz;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView questionText;
    private TextView scoreView;
    private ListView answerListView;
    private String string;
    private String string1;
    private String string2;
    private String string3;
    private String selectedAnswer;
    private int score;
    private int currentQ;

    private Quiz quiz;

    public static final String TAG = "MainActivity";
    private ArrayAdapter<String> adapter;


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
        Log.d(TAG, "onCreate: " + jsonString + "\n" + questionList.toString());
        quiz = new Quiz(questionList);
        wireWidgets();
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
        //trueButton.setText(//*set answers);
    }

    private void wireWidgets() {
        questionText=findViewById(R.id.textView_main_question);
        scoreView=findViewById(R.id.textView_main_score);
        answerListView = findViewById(R.id.listview_main_answers);
        setUpListView();
    }

    private void setUpListView() {
        /*adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return quiz.getQuestion(quiz.getCurrentQ()).getIncorrect_answers().size() + 1;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                //get answer choice text
                Question question = quiz.getQuestion(quiz.getCurrentQ());
                String answerText = null;
                if (position == 0) {
                    answerText = question.getCorrect_answer();
                } else {
                    answerText = question.getIncorrect_answers().get(position - 1);
                }
                //wire widgets
                questionText.setText(question.toString());
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View child = inflater.inflate(R.layout.quiz_activity_item, parent, false);
                TextView answerTextView = child.findViewById(R.id.answer_choice_item);
                answerTextView.setText(answerText);
                return child;
            }
        };*/

        //TODO: get randomized answers from quiz
        //TODO: check for correct when item is chosen/selected
        questionText.setText(quiz.getQuestion(quiz.getCurrentQ()).getQuestion());
        scoreView.setText(String.valueOf(score));
        final List<String> answers = quiz.getQuestion(quiz.getCurrentQ()).getRandomizedAnswers();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, answers);
        answerListView.setAdapter(adapter);
        answerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (quiz.getCurrentQ() == 9){
                    if (quiz.checkAnswer(answers.get(position)) == true){
                        score++;

                        Toast.makeText(MainActivity.this, "Great Job! Your Score is: " +quiz.getScore(), Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Great Job! Your Score is: " +quiz.getScore(), Toast.LENGTH_LONG).show();
                    }
                }
                if (quiz.checkAnswer(answers.get(position)) == true){
                    score++;
                    quiz.nextQuestion();
                    setUpListView();
                    Toast.makeText(MainActivity.this, "Correct! :D", Toast.LENGTH_SHORT).show();
                }
                else {
                    quiz.nextQuestion();
                    setUpListView();
                    Toast.makeText(MainActivity.this, "Wrong! >:0", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
