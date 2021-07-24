package com.example.belotcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int score;
    boolean allTrumpGame;

    TextView suitText;
    TextView scoreText;
    Spinner spinner;

    String suit;

    String[] suitsArrays = {"Clubs", "Hearts", "Spades", "Diamonds"};
    List<ImageView> imgList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreText = findViewById(R.id.textView);
        suitText = findViewById(R.id.textView3);

        score = 0;
        allTrumpGame = true;
        initSpinner();

        populateImgList();
        RadioButton radioAllTrump = findViewById(R.id.radio_trump);
        radioAllTrump.toggle();

        getSupportActionBar().hide();//screw the action bar
    }


    void initSpinner() {
        spinner = findViewById(R.id.spinnerSuit);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.suits, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    boolean canceled = false;
    int countCard = 0;

    public void ace_clicked(View v) {
        score += 11;
        clickedCard(R.drawable.ac);
    }

    void clickedCard(int img) {
        scoreText.setText("Score: " + score);
        imgList.get(countCard).setImageResource(img);
        if (countCard < 23)
            countCard++;
        else countCard = 0;
    }

    public void king_clicked(View v) {
        score += 4;
        clickedCard(R.drawable.kc);
    }

    public void queen_clicked(View v) {
        score += 3;

        clickedCard(R.drawable.qc);
    }

    public void jack_clicked(View v) {
        if (suitTrumpgame)
            showOptionsDialog("Jack");
        else {
            if (allTrumpGame) score += 20;
            else score += 2;
        }
    }

    public void ten_clicked(View v) {
        score += 10;
        clickedCard(R.drawable._10c);
    }

    public void nine_clicked(View v) {
        if (suitTrumpgame)
            showOptionsDialog("Nine");
        else {
            if (allTrumpGame) score += 14;

        }


    }

    public void clear(View v) {
        score = 0;
        scoreText.setText("Score: 0");
        for (ImageView img :
                imgList) {
            img.setImageResource(R.drawable.empty);
        }
        countCard = 0;
    }

    String suitCurrentCard = "Clubs";

    private void showOptionsDialog(String card) {
        suitCurrentCard = "Clubs";
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Which suit?");
        builder.setSingleChoiceItems(suitsArrays, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                suitCurrentCard = suitsArrays[i];
            }
        });


        builder.setPositiveButton("Select", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                canceled = false;
                if ((suit.equals(suitCurrentCard)))
                    score += card.equals("Nine") ? 14 : 20; //we only call this when the card is either jack or nine and the game is
                else score += card.equals("Nine") ? 0 : 2;
                scoreText.setText("Score: " + score);
                dialogInterface.dismiss();
                if (card.equals("Nine")) clickedCard(R.drawable._9c);
                else clickedCard(R.drawable.jc);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                canceled = true;
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }


    public void setTrumpGame(View view) {
        allTrumpGame = true;
        suitTrumpgame = false;
        spinner.setSelection(4);
        spinner.setBackgroundColor(Color.WHITE);
    }

    public void setNonTrumpGame(View view) {
        allTrumpGame = false;
        suitTrumpgame = false;
        spinner.setSelection(4);
        spinner.setBackgroundColor(Color.WHITE);
    }

    boolean suitTrumpgame = true;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        if (position != 4) {
            allTrumpGame = false;
            suitTrumpgame = true;
            spinner.setBackgroundColor(Color.CYAN);

            suit = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), suit, Toast.LENGTH_SHORT).show();
            RadioButton radioNone = findViewById(R.id.radio_none);
            radioNone.toggle();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(adapterView.getContext(), "doestn work? ", Toast.LENGTH_LONG);
    }

    private void populateImgList() { //i hate this monstrosity :ccc
        imgList.add(findViewById(R.id.c1));
        imgList.add(findViewById(R.id.c2));
        imgList.add(findViewById(R.id.c3));
        imgList.add(findViewById(R.id.c4));
        imgList.add(findViewById(R.id.c5));
        imgList.add(findViewById(R.id.c6));
        imgList.add(findViewById(R.id.c7));
        imgList.add(findViewById(R.id.c8));
        imgList.add(findViewById(R.id.c9));
        imgList.add(findViewById(R.id.c10));
        imgList.add(findViewById(R.id.c11));
        imgList.add(findViewById(R.id.c12));
        imgList.add(findViewById(R.id.c13));
        imgList.add(findViewById(R.id.c14));
        imgList.add(findViewById(R.id.c15));
        imgList.add(findViewById(R.id.c16));
        imgList.add(findViewById(R.id.c17));
        imgList.add(findViewById(R.id.c18));
        imgList.add(findViewById(R.id.c19));
        imgList.add(findViewById(R.id.c20));
        imgList.add(findViewById(R.id.c21));
        imgList.add(findViewById(R.id.c22));
        imgList.add(findViewById(R.id.c23));
        imgList.add(findViewById(R.id.c24));
    }

}