package com.example.mixologymania;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class Recipe_Selected extends AppCompatActivity {
    public TextView recipeName;
    public TextView recipeIngredients;
    public TextView recipeInstructions;
    public ImageView recipeImage;
    public DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_selected);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //gets the values from the users click on the drink
        Intent intent = getIntent();
        String name = intent.getStringExtra(Intent.EXTRA_TEXT);

        recipeName = findViewById(R.id.tvSelectedDrinkName);
        recipeIngredients = findViewById(R.id.tvSelectedDrinkIngredients);
        recipeImage = findViewById(R.id.ivSelectedDrinkImage);
        recipeInstructions = findViewById(R.id.tvSelectedDrinkInstructions);

        database = FirebaseDatabase.getInstance().getReference().child("drinks");
        database.child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Objects.requireNonNull(getSupportActionBar()).setTitle(dataSnapshot.child("drinkName").getValue().toString());
                recipeName.setText(dataSnapshot.child("drinkName").getValue().toString());
                Picasso.get().load(dataSnapshot.child("imgURL").getValue().toString()).into(recipeImage);
                String ingredients = "Ingredients: \n";
                String instructions = "Instructions: \n";
                for(int i = 0 ; i < (int)(dataSnapshot.child("ingredients").getChildrenCount());i++){
                    Drink drink = dataSnapshot.getValue(Drink.class);
                    ingredients += drink.getIngredients().get(i);
                    ingredients += "\n";
                }
                for(int i = 0 ; i < (int)(dataSnapshot.child("instructions").getChildrenCount());i++){
                    Drink drink = dataSnapshot.getValue(Drink.class);
                    instructions += "" + (i+1) + ". " + drink.getInstructions().get(i);
                    instructions += "\n";
                }
                recipeIngredients.setText(ingredients);
                recipeInstructions.setText(instructions);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.selected_recipe_activity_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}