package com.example.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int score = 0;
    final int questionOneAnswer = R.id.question_1_answer_1;
    final String questionTwoAnswer = "8";
    final int questionFourAnswer = R.id.question_4_answer_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleSubmit(View view) {
        if (getQuestion1()) score++;
        if (getQuestion2()) score++;
        if (getQuestion3()) score++;
        if (getQuestion4()) score++;
        display();
        reset();
    }

    private boolean getQuestion1() {
        RadioGroup groupOne = (RadioGroup) findViewById(R.id.group_one);
        return groupOne.getCheckedRadioButtonId() == questionOneAnswer;
    }

    private boolean getQuestion2() {
        EditText answerTwo = (EditText) findViewById(R.id.question_2_answer);
        return answerTwo.getText().toString().equals(questionTwoAnswer);

    }

    private boolean getQuestion3() {
        boolean questionThreeAnsOne = ((CheckBox) findViewById(R.id.question_3_answer_1)).isChecked();
        boolean questionThreeAnsTwo = ((CheckBox) findViewById(R.id.question_3_answer_2)).isChecked();
        return questionThreeAnsOne && questionThreeAnsTwo;
    }

    private boolean getQuestion4() {
        RadioGroup groupFour = (RadioGroup) findViewById(R.id.group_four);
        return groupFour.getCheckedRadioButtonId() == questionFourAnswer;
    }

    private void display() {
        Toast.makeText(this, "Congratulations!! Your Score is " + score + " Out of 4", Toast.LENGTH_LONG).show();
    }

    private void reset() {
        score = 0;
    }
}