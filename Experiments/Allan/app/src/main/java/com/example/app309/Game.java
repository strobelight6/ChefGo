package com.example.app309;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final String[] options = {"Rock", "Paper", "Scissors"};
        final String[] computerChoices = new String[3];
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        generateGame(options,computerChoices);
        // Button action
        Button middle = findViewById(R.id.home);
        middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Game.this, MainActivity.class);
                startActivity(intent);

            }
        });
        final Random rand = new Random();
        //Left Option Press
        final Button Left = findViewById(R.id.button);
        Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            String choice = Left.getText().toString();
            TextView userScreen = findViewById(R.id.userPick);
            userScreen.setText(choice);
            TextView computerScreen = findViewById((R.id.computerPick));
            computerScreen.setText(options[(rand.nextInt(3))]);
            Left.setEnabled(false);
            Outcome();
            }
        });

        //middle option
        final Button Middle = findViewById(R.id.button2);
        Middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choice = Middle.getText().toString();
                TextView userScreen = findViewById(R.id.userPick);
                userScreen.setText(choice);
                TextView computerScreen = findViewById((R.id.computerPick));
                computerScreen.setText(options[(rand.nextInt(3))]);
                Middle.setEnabled(false);
                Outcome();
            }
        });
        //Right option
        final Button Right = findViewById(R.id.option3);
        Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choice = Right.getText().toString();
                TextView userScreen = findViewById(R.id.userPick);
                userScreen.setText(choice);
                TextView computerScreen = findViewById((R.id.computerPick));
                computerScreen.setText(options[(rand.nextInt(3))]);
                Right.setEnabled(false);
                Outcome();

            }
        });

    }




    public void Outcome(){
        TextView userPick = findViewById(R.id.userPick);
        TextView computerPick = findViewById(R.id.computerPick);

        String userChoice = userPick.getText().toString();
        String computerChoice = computerPick.getText().toString();
        TextView Outcome = findViewById(R.id.outcome);
        final Button Right = findViewById(R.id.option3);
        final Button Middle = findViewById(R.id.button2);
        final Button Left = findViewById(R.id.button);
        if((userChoice == "Rock" && computerChoice == "Paper") || (userChoice == "Paper" && computerChoice == "Scissors") || (userChoice == "Scissors" && computerChoice == "Rock"))
        {

            Outcome.setText("You Lose!");
            Right.setEnabled(false);
            Middle.setEnabled(false);
            Left.setEnabled(false);
        }
        else if((userChoice == "Rock" && computerChoice == "Scissors") || (userChoice == "Paper" && computerChoice == "Rock") || (userChoice == "Scissors" && computerChoice == "Paper")){
            Outcome.setText("You Win!");
            Right.setEnabled(false);
            Middle.setEnabled(false);
            Left.setEnabled(false);

        }
        else{
            Outcome.setText("You Tied, Try Again");
        }

    }
    public void generateGame(String[] options, String[] computerChoices){
        Random rand = new Random();

        for(int i = 0; i<2;i++){
            int choice = rand.nextInt(3);
            computerChoices[i] = options[choice];
        }
        int one = rand.nextInt(3);
        int two = rand.nextInt(3);
        int three = rand.nextInt(3);
        TextView button = findViewById((R.id.button));
        button.setText((options[one]));

        TextView button2 = findViewById((R.id.button2));
        button2.setText((options[two]));

        TextView button3 = findViewById((R.id.option3));
        button3.setText((options[three]));
    }
}
