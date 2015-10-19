package com.example.salazar32.teamfloyd_project2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Gameplay extends AppCompatActivity {
    List<String> wordBank;
    Random r = new Random();

    int i = 0; // our global counter for the skip method
    int myScore = 0; // our score keeping
    int strikes = 0; // number of strikes (wrong answers)
    int anagrams = 0; // number of anagrams remaining

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // we see the difficulty option layout before the gameplay
        setContentView(R.layout.difficulty);

    }

    public String scramble(Random random, String inputString) {
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
        TextView anagramsView = (TextView) findViewById(R.id.anagrams);


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
        anagrams = wordBank.size(); // number of anagrams left
        // display the first scrambled word of the array into the text view
        scrambleView.setText(scramble(r, wordBank.get(0)));
        anagramsView.setText(anagrams + " out of " + wordBank.size() + " remaining");

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
        TextView anagramsView = (TextView) findViewById(R.id.anagrams);

        String data = "";
        StringBuffer sb = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.medlist);
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
        anagrams = wordBank.size();
        scrambleView.setText(scramble(r, wordBank.get(0)));
        anagramsView.setText(anagrams + " out of " + wordBank.size() + " remaining");
    }

    public void clickHard(View v) {
        setContentView(R.layout.gameplay);
        TextView scrambleView = (TextView) findViewById(R.id.scrambleView);
        TextView anagramsView = (TextView) findViewById(R.id.anagrams);

        String data = "";
        StringBuffer sb = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.hardlist);
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
        anagrams = wordBank.size();
        scrambleView.setText(scramble(r, wordBank.get(0)));
        anagramsView.setText(anagrams + " out of " + wordBank.size() + " remaining");
    }

    public void check (View v){
        TextView unscrambleView = (TextView) findViewById(R.id.unscrambleView);
        TextView anagramsView = (TextView) findViewById(R.id.anagrams);

        // toasts are pop up notifications, show "correct!" when answer is right
        if (unscrambleView.getText().toString().equals(wordBank.get(i))) {
            unscrambleView.setText("");
            Context context = getApplicationContext();
            CharSequence text = "Correct!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            myScore++;
            // skip to next word when correct
            skip(v);
            anagramsView.setText(anagrams + " out of " + wordBank.size() + " remaining");

        }
        else {
            // show "wrong!" when answer is wrong
            // you have a limited amount of attempts per word before it gets marked wrong
            if (strikes == 3) {
                skip(v);
                strikes = 0;
                anagramsView.setText(anagrams + " out of " + wordBank.size() + " remaining");
            }
            else {
                unscrambleView.setText("");
                Context context = getApplicationContext();
                CharSequence text = "Wrong!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                strikes++;
            }
        }
    }

    public void clear (View v){
        // clear the text view
        TextView unscrambleView = (TextView) findViewById(R.id.unscrambleView);
        unscrambleView.setText("");

    }

    public void gameOver(View v) {
        // show a score at the end
        setContentView(R.layout.gameover);
        TextView scoreView = (TextView) findViewById(R.id.score);
        scoreView.setText("you scored a " + myScore);

    }
    public void skip (View v) {
        // this method is used to skip
        TextView anagramsView = (TextView) findViewById(R.id.anagrams);
        if ( i == wordBank.size() - 1) {
            gameOver(v);
        }
        else {
            i++;
            TextView scrambleView = (TextView) findViewById(R.id.scrambleView);
            TextView unscrambleView = (TextView) findViewById(R.id.unscrambleView);
            String item = wordBank.get(i);
            scrambleView.setText(scramble(r, item));
            unscrambleView.setText("");
            --anagrams;
            anagramsView.setText(anagrams + " out of " + wordBank.size() + " remaining");
        }
    }

    public void onClick (View v){
        // this method handles editing the unscramble text view
        TextView unscrambleView = (TextView) findViewById(R.id.unscrambleView);
        unscrambleView.setCursorVisible(true);
        unscrambleView.setFocusableInTouchMode(true);
        unscrambleView.setInputType(InputType.TYPE_CLASS_TEXT);
        unscrambleView.requestFocus();
    }
}

