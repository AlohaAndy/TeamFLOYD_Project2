package com.example.salazar32.teamfloyd_project2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;


public class Gameplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // we see the difficulty option layout before the gameplay
        setContentView(R.layout.difficulty);

    }

    public static String scramble(Random random, String inputString) {
        // convert string to char array
        char a[] = inputString.toCharArray();
        // scramble letters
        for (int i = 0; i < a.length - 1; i++) {
            int j = random.nextInt(a.length - 1);
            // swap
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        return new String(a);
    }

    public void clickEasy(View v) {
        Random r = new Random();
        // show the gameplay layout after choosing a difficulty
        setContentView(R.layout.gameplay);
        TextView scrambleView = (TextView) findViewById(R.id.scrambleView);

        String data = "";
        StringBuffer sb = new StringBuffer();
        // for our anagram, we are using "easylist.txt" as our word bank
        // we find easylist.txt in our res/raw folder
        InputStream is = this.getResources().openRawResource(R.raw.easylist);
        // create a new buffer reader to read the file
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        // read the lines out and store it in the buffer
        if (is != null) {
            try {
                while ((data = br.readLine()) != null) {
                    sb.append(data + "\n");
                }
                is.close();
            } catch (IOException  e){
                e.printStackTrace();
            }
        }
        // create a temp variable to convert the buffer to a string(s)
        String temp = sb.toString();

        // convert the strings into a string array and...
        String[] wordBank = temp.split("\\s+");
        // give them proper indexes
        for (int i = 0; i < wordBank.length; i++) {
            // we also replace all unnecessary characters with whitespace
            wordBank[i] = wordBank[i].replaceAll("[^\\w]", "");
        }
        // display the first scrambled word of the array into the text view
        scrambleView.setText(scramble(r, wordBank[0]));
    }

    /**
     * For the harder difficulty options, the code is exactly the same as the easy option, only
     * difference is that it uses different word banks.  To properly reference text files in
     * Android Studio, create a new text file inside res/raw folder.  Also words should be all lower
     * case.
     *
     */

    public void clickMedium(View v) {
        Random r = new Random();
        setContentView(R.layout.gameplay);
        TextView scrambleView = (TextView) findViewById(R.id.scrambleView);

        String data = "";
        StringBuffer sb = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.easylist);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        if (is != null) {
            try {
                while ((data = br.readLine()) != null) {
                    sb.append(data + "\n");
                }
                is.close();
            } catch (IOException  e){
                e.printStackTrace();
            }
        }
        String temp = sb.toString();
        String[] wordBank = temp.split("\\s+");
        for (int i = 0; i < wordBank.length; i++) {
            wordBank[i] = wordBank[i].replaceAll("[^\\w]", "");
        }
        scrambleView.setText(scramble(r, wordBank[1]));
    }

    public void clickHard(View v) {
        Random r = new Random();
        setContentView(R.layout.gameplay);
        setContentView(R.layout.gameplay);
        TextView scrambleView = (TextView) findViewById(R.id.scrambleView);

        String data = "";
        StringBuffer sb = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.easylist);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        if (is != null) {
            try {
                while ((data = br.readLine()) != null) {
                    sb.append(data + "\n");
                }
                is.close();
            } catch (IOException  e){
                e.printStackTrace();
            }
        }
        String temp = sb.toString();
        String[] wordBank = temp.split("\\s+");
        for (int i = 0; i < wordBank.length; i++) {
            wordBank[i] = wordBank[i].replaceAll("[^\\w]", "");
        }
        scrambleView.setText(scramble(r, wordBank[2]));
    }

    public void check (View v){

    }

    public void clear (View v){

    }

    public void skip (View v){

    }

    public void onClick (View v){
        TextView unscrambleView = (TextView) findViewById(R.id.unscrambleView);
        unscrambleView.setText("");
        unscrambleView.setCursorVisible(true);
        unscrambleView.setFocusableInTouchMode(true);
        unscrambleView.setInputType(InputType.TYPE_CLASS_TEXT);
        unscrambleView.requestFocus();

    }
}

