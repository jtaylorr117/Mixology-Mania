package com.example.mixologymania;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;

public class select_drink_to_make extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    DrinkRecipeAdapter adapter;
    DrinkRecipeAdapter newAdapter;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_drink_to_make);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = FirebaseDatabase.getInstance().getReference().child("drinks");
        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Drink> options = new FirebaseRecyclerOptions.Builder<Drink>().setQuery(database, Drink.class).build();
        adapter = new DrinkRecipeAdapter(options);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new DrinkRecipeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                adapter.notifyItemChanged(position);
                Intent intent = new Intent(getApplicationContext(), make_drink.class);
                startActivity(intent);
            }

        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}