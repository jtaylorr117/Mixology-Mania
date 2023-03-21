package com.example.mixologymania;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class home_screen extends AppCompatActivity implements View.OnClickListener {
    private CardView cardHelp, cardAbout, cardPlay, cardRecipes, cardSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Log.d("TAG","CLICKED HELP");
        cardHelp = (CardView) findViewById(R.id.card_help);
        cardAbout = (CardView) findViewById(R.id.card_about);
        cardPlay = (CardView) findViewById(R.id.card_play);
        cardRecipes = (CardView) findViewById(R.id.card_recipes);
        cardSettings = (CardView) findViewById(R.id.card_settings);

        cardHelp.setOnClickListener(this);
        cardAbout.setOnClickListener(this);
        cardPlay.setOnClickListener(this);
        cardRecipes.setOnClickListener(this);
        cardSettings.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.card_help:
                i = new Intent(this,help_screen.class);
                startActivity(i);
                break;
            case R.id.card_about:
                i = new Intent(this,about_us_screen.class);
                startActivity(i);
                break;
            case R.id.card_recipes:
                i = new Intent(this, RecipeListScreen.class);
                startActivity(i);
                break;
            case R.id.card_settings:
                i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                break;
            case R.id.card_play:
                i = new Intent(this,select_drink_to_make.class);
                startActivity(i);
                break;

            default:
                break;
        }

    }


}