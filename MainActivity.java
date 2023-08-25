package com.example.myquizz1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA,ansB,ansC,ansD;
    Button submitBtn;

    int score=0;
    int totalQuestions= QuestionAnswer.questions.length;
    int currentQuestionIndex= 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView =findViewById(R.id.total_questions);
        questionTextView=findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);


        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);


        totalQuestionsTextView.setText("Total questions :"+totalQuestions);

        loadNewquestion();



    }

    @Override
    public void onClick(View view) {
         ansA.setBackgroundColor(Color.WHITE);
         ansB.setBackgroundColor(Color.WHITE);
         ansC.setBackgroundColor(Color.WHITE);
         ansD.setBackgroundColor(Color.WHITE);



         Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(selectedAnswer.equals(QuestionAnswer.correctanswers[currentQuestionIndex])) {
                score++;
            }
            currentQuestionIndex++;
            loadNewquestion();



        }else{

            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }


    }

    void loadNewquestion(){
        if(currentQuestionIndex == totalQuestions){
            finishQuiz();
            return;
        }
        questionTextView.setText(QuestionAnswer.questions[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choises[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choises[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choises[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choises[currentQuestionIndex][3]);
    }

    void finishQuiz(){
        String passStatus="";
        if(score > totalQuestions){

            passStatus = "OOPS :( !!!";

        }else{
            passStatus = "NAILED IT :) !!!";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("YOUR SCORE IS"  +      score    + "OUT OF "+ totalQuestions)
                .setPositiveButton("RESTART",((dialogInterface, i) -> restartQuiz()))
                .setCancelable(false)
                .show();
    }
     void restartQuiz(){
        score =0;
        currentQuestionIndex=0;
        loadNewquestion();
     }

}