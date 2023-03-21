package com.example.mixologymania;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecipeListScreen extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    DrinkRecipeAdapter adapter;
    DrinkRecipeAdapter newAdapter;
    DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list_screen);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = FirebaseDatabase.getInstance().getReference().child("drinks");
        mRecyclerView = findViewById(R.id.recyclerView);
        //set this to true if you know that the view will not change in size
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Drink> options = new FirebaseRecyclerOptions.Builder<Drink>().setQuery(database, Drink.class).build();
        adapter = new DrinkRecipeAdapter(options);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new DrinkRecipeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                adapter.notifyItemChanged(position);

                //gets items ready to start the activity with the drink information
                Intent intent = new Intent(getApplicationContext(), Recipe_Selected.class);
                intent.putExtra(Intent.EXTRA_TEXT, adapter.getItem(position).getdrinkName());
                startActivity(intent);
            }

        });


    }

    private void firebaseSearch(String searchText){
        String queryString = searchText.toLowerCase();
        Query searchResults = database.orderByChild("drinkNameLowercase").startAt(queryString).endAt(queryString + "\uf8ff");
        FirebaseRecyclerOptions<Drink> newOptions = new FirebaseRecyclerOptions.Builder<Drink>().setQuery(searchResults, Drink.class).setLifecycleOwner(this).build();
        adapter = new DrinkRecipeAdapter(newOptions);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new DrinkRecipeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //adapter.notifyItemChanged(position);
                Intent intent = new Intent(getApplicationContext(), Recipe_Selected.class);
                intent.putExtra(Intent.EXTRA_TEXT, adapter.getItem(position).getdrinkName());
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_list_menu, menu);
        MenuItem item = menu.findItem(R.id.search);


        menu.findItem(R.id.checkboxAllDrinks).setChecked(true);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                firebaseSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Query query;
        FirebaseRecyclerOptions<Drink> newOptions;
        switch (id){
            case R.id.checkboxAllDrinks:
                    Toast.makeText(this, "ALL selected", Toast.LENGTH_SHORT).show();
                    item.setChecked(false);
                    mRecyclerView.setAdapter(adapter);
                return true;
            case R.id.checkboxNonAlc:
                    item.setChecked(true);
                    setListToFilter("isNonAlcoholic");
                return true;
            case R.id.checkboxVodka:
                    item.setChecked(true);
                    setListToFilter("vodka");
                return true;
            case R.id.checkboxRum:
                    item.setChecked(true);
                    setListToFilter("rum");
                return true;
            case R.id.checkboxTequila:
                item.setChecked(true);
                setListToFilter("tequila");
                return true;
            case R.id.checkboxWhiskey:
                item.setChecked(true);
                setListToFilter("whiskey");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setListToFilter(String filterItem){
        Query query;
        FirebaseRecyclerOptions<Drink> newOptions;

        Toast.makeText(this, filterItem + " selected", Toast.LENGTH_SHORT).show();
        query = database.orderByChild("attributes/"+filterItem).equalTo(true);
        //Query searchResults = database.orderByChild("drinkNameLowercase").startAt(queryString).endAt(queryString + "\uf8ff");
        newOptions = new FirebaseRecyclerOptions.Builder<Drink>().setQuery(query, Drink.class).setLifecycleOwner(this).build();
        newAdapter = new DrinkRecipeAdapter(newOptions);
        mRecyclerView.setAdapter(newAdapter);

        newAdapter.setOnItemClickListener(new DrinkRecipeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //newAdapter.notifyItemChanged(position);
                Intent intent = new Intent(getApplicationContext(), Recipe_Selected.class);
                intent.putExtra(Intent.EXTRA_TEXT, newAdapter.getItem(position).getdrinkName());
                startActivity(intent);
            }

        });
    }
}