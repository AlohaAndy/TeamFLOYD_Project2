package com.example.salazar32.teamfloyd_project2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }

    public void clickPlay(View v) {
        startActivity(new Intent(MainActivity.this, Gameplay.class));
    }

    public void clickCredits(View v) {
        startActivity(new Intent(MainActivity.this, Credits.class));
    }

}
