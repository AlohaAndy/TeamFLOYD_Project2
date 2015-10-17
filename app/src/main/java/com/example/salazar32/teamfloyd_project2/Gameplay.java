package com.example.salazar32.teamfloyd_project2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;


public class Gameplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Random r = new Random();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameplay);
        TextView scrambleView = (TextView) findViewById(R.id.scrambleView);

        String data = "";
        StringBuffer sb = new StringBuffer();
        // for our anagram, we are using "wordlist.txt" as our word bank
        // we find wordlist.txt in our res/raw folder
        InputStream is = this.getResources().openRawResource(R.raw.wordlist);
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
}

