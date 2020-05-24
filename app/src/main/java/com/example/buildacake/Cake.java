package com.example.buildacake;

import android.content.res.Resources;

public class Cake {
    private float mCakePrice;
    private String mCakeSize;
    private String mCakeMessage;
    private String mCakeIcing;
    private String mCakeBiscuit;
    private String mCakeFilling;
    private String mCakeToppings;
    private String mDairyFree;
    private String mGlutenFree;
    private String mEggFree;
    private String mAdditionalInfo;

    // CAKE CONSTRUCTOR
    public Cake(float cakePrice, String cakeSize, String cakeMessage, String cakeIcing, String cakeBiscuit, String cakeFilling, String cakeToppings, String dairyFree, String glutenFree, String eggFree, String additionalInfo) {
        mCakePrice = cakePrice;
        mCakeSize = cakeSize;
        mCakeMessage = cakeMessage;
        mCakeIcing = cakeIcing;
        mCakeBiscuit = cakeBiscuit;
        mCakeFilling = cakeFilling;
        mCakeToppings = cakeToppings;
        mDairyFree = dairyFree;
        mGlutenFree = glutenFree;
        mEggFree = eggFree;
        mAdditionalInfo = additionalInfo;
    }

    // CAKE PRICE GETTER AND SETTER
    public float getCakePrice() {
        return mCakePrice;
    }

    public void setCakePrice(float mCakePrice) {
        this.mCakePrice = mCakePrice;
    }

    // CAKE SIZE GETTER AND SETTER
    public String getCakeSize() {
        return mCakeSize;
    }

    public void setCakeSize(String mCakeSize) {
        this.mCakeSize = mCakeSize;
    }

    // CAKE MESSAGE GETTER AND SETTER
    public String getCakeMessage() {
        return mCakeMessage;
    }

    public void setCakeMessage(String mCakeMessage) {
        this.mCakeMessage = mCakeMessage;
    }

    // ICING GETTER AND SETTER
    public String getCakeIcing() {
        return mCakeIcing;
    }

    public void setCakeIcing(String mCakeIcing) {
        this.mCakeIcing = mCakeIcing;
    }

    // BISCUIT GETTER AND SETTER
    public String getCakeBiscuit() {
        return mCakeBiscuit;
    }

    public void setCakeBiscuit(String mCakeBiscuit) {
        this.mCakeBiscuit = mCakeBiscuit;
    }

    // FILLING GETTER AND SETTER
    public String getCakeFilling() {
        return mCakeFilling;
    }

    public void setCakeFilling(String mCakeFilling) {
        this.mCakeFilling = mCakeFilling;
    }

    // TOPPINGS GETTER AND SETTER
    public String getCakeToppings() {
        return mCakeToppings;
    }

    public void setCakeToppings(String mCakeToppings) {
        this.mCakeToppings = mCakeToppings;
    }

    // DAIRY FREE GETTER AND SETTER
    public String isDairyFree() {
        return mDairyFree;
    }

    public void setDairyFree(String mDairyFree) {
        this.mDairyFree = mDairyFree;
    }

    // GLUTEN FREE FLOWERS GETTER AND SETTER
    public String isGlutenFree() {
        return mGlutenFree;
    }

    public void setGlutenFree(String mGlutenFree) {
        this.mGlutenFree = mGlutenFree;
    }

    // EGG FREE GETTER AND SETTER
    public String isEggFree() {
        return mEggFree;
    }

    public void setEggFree(String mEggFree) {
        this.mEggFree = mEggFree;
    }

    // ADDITIONAL INFO
    public String getAdditionalInfo() {
        return mAdditionalInfo;
    }

    public void setAdditionalInfo(String mAdditionalInfo) {
        this.mAdditionalInfo = mAdditionalInfo;
    }

    public String getStates() {
        String allergens = "";
        String yes = Resources.getSystem().getString(android.R.string.yes);
        if (this.isDairyFree().equals(yes)) {
            allergens += "," + "Dairy Free";
        }
        if (this.isGlutenFree().equals(yes)) {
            allergens += "," + "Gluten Free";
        }
        if (this.isEggFree().equals(yes)) {
            allergens += "," + "No Eggs";
        }
        return (yes + "Price: " + Math.round(this.getCakePrice()) + " kn, Size: " + this.getCakeSize() + ", Message: " + this.getCakeMessage()
                + ", Icing: " + this.getCakeIcing() + ", Biscuit: " + this.getCakeBiscuit() + ", Filling: " + this.getCakeFilling()
                + ", Toppings: " + this.getCakeToppings() + allergens + ", Additional Info: " + this.getAdditionalInfo() + "\n");
    }

}
