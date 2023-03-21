package com.example.mixologymania;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Drink {
    private String drinkDescription;
    private int drinkID;
    private String drinkName;
    private String drinkNameLowercase;
    private String imgURL;
    private ArrayList<String> ingredients;
    private ArrayList<String> instructions;
    private boolean isAlcoholic;


    public Drink(){ }
    public Drink(String drinkDescription, int drinkID, String drinkName, String drinkNameLowercase, String imgURL, ArrayList<String> ingredients, ArrayList<String> instructions, boolean isAlcoholic){
        this.drinkDescription = drinkDescription;
        this.drinkID = drinkID;
        this.drinkName = drinkName;
        this.drinkNameLowercase = drinkNameLowercase;
        this.imgURL = imgURL;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.isAlcoholic = isAlcoholic;
    }
    public void setdrinkName(String drinkName){
        this.drinkName = drinkName;
    }
    public void setdrinkDescription(String drinkDescription){
        this.drinkDescription = drinkDescription;
    }
    public String getdrinkName() {
        return drinkName;
    }
    public String getDrinkNameLowercase(){return drinkNameLowercase;}
    public void setDrinkNameLowercase(String drinkName){
        this.drinkNameLowercase = drinkName;
    }
    public String getimgURL(){
        return this.imgURL;
    }
    public String getdrinkDescription() {
        return drinkDescription;
    }
    public int getDrinkID(){
        return drinkID;
    }
    public ArrayList<String> getIngredients(){
        return this.ingredients;
    }
    public ArrayList<String> getInstructions(){
        return this.instructions;
    }
    public boolean getIsAlcoholic(){
        return isAlcoholic;
    }
    public void setimgURL(String imgURL){
        this.imgURL = imgURL;
    }


}
