package com.example.belotcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
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
    int score = 0, countCard = 0;
    boolean allTrumpGame = true, canceled = false, suitTrumpgame = true;


    TextView suitText;
    TextView scoreText;
    Spinner spinner;

    String suit, suitCurrentCard = "Clubs";

    String[] suitsArrays = {"Clubs", "Hearts", "Spades", "Diamonds"};
    List<ImageView> imgList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreText = findViewById(R.id.textView);
        suitText = findViewById(R.id.textView3);

        initSpinner();
        populateImgList();
        RadioButton radioAllTrump = findViewById(R.id.radio_trump);
        radioAllTrump.toggle();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//screw landscape mode
        getSupportActionBar().hide();//screw the action bar
    }


    void initSpinner() {
        spinner = findViewById(R.id.spinnerSuit);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.suits, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    public void increaseScore(int incrementValue) {
        score += incrementValue;
        scoreText.setText("Score: " + score);
    }

    private void resetScore() {
        score = 0;
        scoreText.setText("Score: 0");
    }

    public void ace_clicked(View v) {
        increaseScore(11);
        placeCardOnTable(R.drawable.ac);
    }

    void placeCardOnTable(int img) {

        imgList.get(countCard).setImageResource(img);
        if (countCard < 23)
            countCard++;
        else countCard = 0;
    }

    public void king_clicked(View v) {
        increaseScore(4);
        placeCardOnTable(R.drawable.kc);
    }

    public void queen_clicked(View v) {
        increaseScore(3);
        placeCardOnTable(R.drawable.qc);
    }

    public void jack_clicked(View v) {
        if (suitTrumpgame)
            showOptionsDialog("Jack");
        else {
            if (allTrumpGame) increaseScore(20);
            else increaseScore(2);
        }
    }

    public void ten_clicked(View v) {
        increaseScore(10);
        placeCardOnTable(R.drawable._10c);
    }

    public void nine_clicked(View v) {
        if (suitTrumpgame)
            showOptionsDialog("Nine");
        else {
            if (allTrumpGame) increaseScore(14);
        }
    }

    public void clear(View v) {
        resetScore();
        resetImages();
    }

    void resetImages() {
        for (ImageView img :
                imgList) {
            img.setImageResource(R.drawable.empty);
        }
        countCard = 0;
    }


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
                    increaseScore(card.equals("Nine") ? 14 : 20); //we only call this when the card is either jack or nine
                else increaseScore(card.equals("Nine") ? 0 : 2);

                if (card.equals("Nine"))
                    placeCardOnTable(R.drawable._9c);
                else
                    placeCardOnTable(R.drawable.jc);

                dialogInterface.dismiss();
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

    void deselectSpinner() {
        spinner.setSelection(4);
        spinner.setBackgroundColor(Color.WHITE);
    }

    public void setTrumpGame(View view) {
        allTrumpGame = true;
        suitTrumpgame = false;
        deselectSpinner();
    }

    public void setNonTrumpGame(View view) {
        allTrumpGame = false;
        suitTrumpgame = false;
        deselectSpinner();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        if (position != 4) {
            allTrumpGame = false;
            suitTrumpgame = true;
            spinner.setBackgroundColor(Color.CYAN);

            suit = parent.getItemAtPosition(position).toString();

            RadioButton radioNone = findViewById(R.id.radio_none);//selects an invisible radio button to deselect other radio buttons
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