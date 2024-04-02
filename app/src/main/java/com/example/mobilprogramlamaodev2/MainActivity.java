package com.example.mobilprogramlamaodev2;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int randomNumber;
    private EditText guessEditText;
    private TextView resultTextView;
    private Button playAgainButton;
    private TextView tahminHakkitxt;

    int tahminHakki = 6;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guessEditText = findViewById(R.id.guessEditText);
        resultTextView = findViewById(R.id.resultTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        tahminHakkitxt = findViewById(R.id.hakki);
        Button guessButton = findViewById(R.id.guessButton);



        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
                tahminHakkiGuncelle();
            }
        });


        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });


        generateRandomNumber();
    }

    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
    }

    private void checkGuess() {
        String guessString = guessEditText.getText().toString().trim();
        if (!guessString.isEmpty()) {
            int guess = Integer.parseInt(guessString);
            if (guess == randomNumber) {
                resultTextView.setText("Tebrikler Doğru Sayıyı Tahmin Ettiniz");
                playAgainButton.setVisibility(View.VISIBLE);
            } else if (guess < randomNumber) {
                resultTextView.setText("Daha Yüksek Bir Sayı Deneyiniz");
            } else {
                resultTextView.setText("Daha Düşük Bir Sayı Deneyiniz");
            }
            if(tahminHakki==0){
                resultTextView.setText("Tahmin hakkınız kalmadı");
                playAgainButton.setVisibility(View.VISIBLE);
            }
            guessEditText.setText("");
        }
    }

    private void tahminHakkiGuncelle(){
        if(tahminHakki==1){
            tahminHakkitxt.setText("Kalan tahmin hakki 0");
            resultTextView.setText("Tahmin hakkınız kalmadı. Kaybettiniz");
            playAgainButton.setVisibility(View.VISIBLE);
        }else{
            tahminHakki--;
            tahminHakkitxt.setText("Kalan tahmin hakkiniz: "+String.valueOf(tahminHakki));
        }
    }

    private void restartGame() {
        tahminHakki = 6;
        generateRandomNumber();
        resultTextView.setText("");
        guessEditText.setText("");
        playAgainButton.setVisibility(View.GONE);
        tahminHakkitxt.setText(String.format("Kalan tahmin hakkınız: %d", tahminHakki));
    }
}