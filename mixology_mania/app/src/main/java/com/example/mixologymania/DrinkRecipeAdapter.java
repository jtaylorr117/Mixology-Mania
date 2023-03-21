package com.example.mixologymania;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.ObservableSnapshotArray;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DrinkRecipeAdapter extends FirebaseRecyclerAdapter<Drink, DrinkRecipeAdapter.DrinksViewHolder>{
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    // Sub Class to create references of the views in card view ("drink_card_view.xml")
    class DrinksViewHolder extends RecyclerView.ViewHolder {
        public TextView drinkName;
        public TextView drinkDescription;
        public ImageView drinkImage;
        public DrinksViewHolder(@NonNull View itemView, OnItemClickListener listener)
        {
            super(itemView);

            drinkName = itemView.findViewById(R.id.textViewName);
            drinkDescription = itemView.findViewById(R.id.textViewDescription);
            drinkImage = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
    public DrinkRecipeAdapter(@NonNull FirebaseRecyclerOptions<Drink> options)
    {
        super(options);

    }

    // Function to bind the view in Card view(here "Drink_card_view.xml") with data in model class(here "Drinks.class")
    @Override
    protected void onBindViewHolder(@NonNull DrinksViewHolder holder, int position, @NonNull Drink model)
    {
        holder.drinkName.setText(model.getdrinkName());
        holder.drinkDescription.setText(model.getdrinkDescription());
        Picasso.get().load(model.getimgURL()).into(holder.drinkImage);
    }

    // Function to tell the class about the Card view (here "drink_card_view.xml")in which the data will be shown
    @NonNull
    @Override
    public DrinksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drink_card_view, parent, false);
        DrinksViewHolder evh = new DrinksViewHolder(view, mListener);
        return evh;
    }
}
