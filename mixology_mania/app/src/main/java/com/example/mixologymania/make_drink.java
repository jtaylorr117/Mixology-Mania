package com.example.mixologymania;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class make_drink extends AppCompatActivity {
    TextView instructionText;
    ImageView emptyGlass;
    ImageView ingredient1;
    ImageView ingredient2;
    ImageView ingredient3;
    ImageView ingredient4;
    ImageView ingredient5;
    ImageView ingredient6;
    ImageView ingredient7;
    ImageView ingredient8;
    ImageView ingredient9;
    Button startButton;
    Button homeButton;

    private android.widget.RelativeLayout.LayoutParams layoutParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_drink);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initializeVariables();

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
        //ingredient1.setLayoutParams(layoutParams);
        //sets up placeholder text and the toolbar title
        instructionText.setText("Please Click start to begin");
        getSupportActionBar().setTitle("Bloody Mary");

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setVisibility(View.GONE);
                instructionText.setText("Salt the rim of a tall glass. (Drag salt to glass)");
                ingredient1.setVisibility(View.VISIBLE);
                ingredient2.setVisibility(View.VISIBLE);
                ingredient3.setVisibility(View.VISIBLE);
                ingredient4.setVisibility(View.VISIBLE);
                ingredient5.setVisibility(View.VISIBLE);
                ingredient6.setVisibility(View.VISIBLE);
                ingredient7.setVisibility(View.VISIBLE);
                ingredient8.setVisibility(View.VISIBLE);
                ingredient9.setVisibility(View.VISIBLE);
            }
        });
        ingredient1.setOnTouchListener(new MyTouchListener());
        ingredient2.setOnTouchListener(new MyTouchListener());
        ingredient3.setOnTouchListener(new MyTouchListener());
        ingredient4.setOnTouchListener(new MyTouchListener());
        ingredient5.setOnTouchListener(new MyTouchListener());
        ingredient6.setOnTouchListener(new MyTouchListener());
        ingredient7.setOnTouchListener(new MyTouchListener());
        ingredient8.setOnTouchListener(new MyTouchListener());
        ingredient9.setOnTouchListener(new MyTouchListener());
        emptyGlass.setOnDragListener(new MyDragListener());
    }
    private final class MyTouchListener implements View.OnTouchListener {
        @RequiresApi(api = Build.VERSION_CODES.N)
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                            view);
                    view.startDragAndDrop(null, shadowBuilder, view, 0);
                    view.setVisibility(View.INVISIBLE);
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return true;
        }
    }

    class MyDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent e) {
            int action = e.getAction();
            switch (e.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    ImageView image = (ImageView) e.getLocalState();
                    if (image.getId() == ingredient1.getId()) {
                        //IF INGREDIENT 1
                        emptyGlass.setImageResource(R.drawable.highball_glass_with_salt);
                        instructionText.setText("Fill the glass with ice cubes.");
                    }
                    if (image.getId() == ingredient2.getId()) {
                        //IF INGREDIENT 1
                        emptyGlass.setImageResource(R.drawable.highball_glass_with_salt_ice);
                        instructionText.setText("Add the Vodka");
                    }
                    if (image.getId() == ingredient3.getId()) {
                        //IF INGREDIENT 1
                        emptyGlass.setImageResource(R.drawable.highball_glass_with_salt_vodka);
                        instructionText.setText("Add the Tomato Juice");
                    }
                    if (image.getId() == ingredient4.getId()) {
                        //IF INGREDIENT 1
                        emptyGlass.setImageResource(R.drawable.highball_glass_with_salt_tomato);
                        instructionText.setText("Add Worcestershire sauce");
                    }
                    if (image.getId() == ingredient5.getId()) {
                        //IF INGREDIENT 1
                        emptyGlass.setImageResource(R.drawable.highball_glass_with_salt_sauce);
                        instructionText.setText("Add hot sauce");
                    }
                    if (image.getId() == ingredient6.getId()) {
                        //IF INGREDIENT 1
                        emptyGlass.setImageResource(R.drawable.highball_glass_with_salt_sauce);
                        instructionText.setText("Add Pepper");
                    }
                    if (image.getId() == ingredient7.getId()) {
                        //IF INGREDIENT 1
                        emptyGlass.setImageResource(R.drawable.highball_glass_with_salt_fill);
                        instructionText.setText("Add Garnish, Celery and Olives");
                    }
                    if (image.getId() == ingredient8.getId()) {
                        //IF INGREDIENT 1
                        emptyGlass.setImageResource(R.drawable.highball_glass_with_salt_celery);
                        instructionText.setText("Last the olives");
                    }
                    if (image.getId() == ingredient9.getId()) {
                        //IF INGREDIENT 1
                        emptyGlass.setImageResource(R.drawable.finished_bloody_mary);
                        instructionText.setText("You finished!");
                        homeButton.setVisibility(View.VISIBLE);
                        homeButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(getApplicationContext(),home_screen.class);
                                startActivity(i);
                            }
                        });
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:

                    break;
                default:
                    break;
            }
            return true;
        }
    }

    private void initializeVariables() {
        instructionText = findViewById(R.id.tv_instruction_text);
        emptyGlass = findViewById(R.id.highballGlassImage);
        startButton = findViewById(R.id.startButton);
        homeButton = findViewById(R.id.homeButton);
        ingredient1 = findViewById(R.id.ingredient1);
        ingredient2 = findViewById(R.id.ingredient2);
        ingredient3 = findViewById(R.id.ingredient3);
        ingredient4 = findViewById(R.id.ingredient4);
        ingredient5 = findViewById(R.id.ingredient5);
        ingredient6 = findViewById(R.id.ingredient6);
        ingredient7 = findViewById(R.id.ingredient7);
        ingredient8 = findViewById(R.id.ingredient8);
        ingredient9 = findViewById(R.id.ingredient9);
        ingredient1.setImageResource(R.drawable.salt_shaker);
        ingredient2.setImageResource(R.drawable.ice_cubes);
        ingredient3.setImageResource(R.drawable.vodka_bottle);
        ingredient4.setImageResource(R.drawable.tomato_juice);
        ingredient5.setImageResource(R.drawable.brown_bottle);
        ingredient6.setImageResource(R.drawable.hot_sauce);
        ingredient7.setImageResource(R.drawable.pepper);
        ingredient8.setImageResource(R.drawable.celery);
        ingredient9.setImageResource(R.drawable.olives);

        homeButton.setVisibility(View.INVISIBLE);
        ingredient1.setVisibility(View.INVISIBLE);
        ingredient2.setVisibility(View.INVISIBLE);
        ingredient3.setVisibility(View.INVISIBLE);
        ingredient4.setVisibility(View.INVISIBLE);
        ingredient5.setVisibility(View.INVISIBLE);
        ingredient6.setVisibility(View.INVISIBLE);
        ingredient7.setVisibility(View.INVISIBLE);
        ingredient8.setVisibility(View.INVISIBLE);
        ingredient9.setVisibility(View.INVISIBLE);
    }
}