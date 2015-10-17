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
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;


public class Gameplay extends AppCompatActivity {
    List<String> wordBank;
    Random r = new Random();

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

        // here we are creating proper indexes for the strings by separating the words
        wordBank = Arrays.asList(temp.split("\\s+"));

        // display the first scrambled word of the array into the text view
        scrambleView.setText(scramble(r, wordBank.get(0)));
    }

    /**
     * For the harder difficulty options, the code is exactly the same as the easy option, only
     * difference is that it uses different word banks.  To properly reference text files in
     * Android Studio, create a new text file inside res/raw folder.  Also words should be all lower
     * case.
     **/



    public void clickMedium(View v) {
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
        wordBank = Arrays.asList(temp.split("\\s+"));
        scrambleView.setText(scramble(r, wordBank.get(0)));
    }

    public void clickHard(View v) {
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
        wordBank = Arrays.asList(temp.split("\\s+"));
        scrambleView.setText(scramble(r, wordBank.get(0)));
    }

    public void check (View v){

    }

    public void clear (View v){
        TextView unscrambleView = (TextView) findViewById(R.id.unscrambleView);
        unscrambleView.setText("");

    }

    public void skip (View v){
        TextView scrambleView = (TextView) findViewById(R.id.scrambleView);

        for (ListIterator<String> i = wordBank.listIterator(); i.hasNext();) {
            String item = i.next();
            scrambleView.setText(scramble(r, item));
        }
    }

    public void onClick (View v){
        TextView unscrambleView = (TextView) findViewById(R.id.unscrambleView);
        unscrambleView.setCursorVisible(true);
        unscrambleView.setFocusableInTouchMode(true);
        unscrambleView.setInputType(InputType.TYPE_CLASS_TEXT);
        unscrambleView.requestFocus();

    }
}

