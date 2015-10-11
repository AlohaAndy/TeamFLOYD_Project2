package com.example.salazar32.teamfloyd_project2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class Difficulty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulty);
    }

    public void clickEasy(View v) {
        startActivity(new Intent(Difficulty.this, Gameplay.class));
    }

    public void clickMedium(View v) {
        startActivity(new Intent(Difficulty.this, Gameplay.class));
    }

    public void clickHard(View v) {
        startActivity(new Intent(Difficulty.this, Gameplay.class));
    }
}