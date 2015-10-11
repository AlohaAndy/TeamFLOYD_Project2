package com.example.salazar32.teamfloyd_project2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }

    public void clickPlay(View v) {
        startActivity(new Intent(MainActivity.this, Difficulty.class));
    }

    public void clickCredits(View v) {
        startActivity(new Intent(MainActivity.this, Credits.class));
    }

}
