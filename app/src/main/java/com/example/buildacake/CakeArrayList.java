package com.example.buildacake;

import java.util.ArrayList;

public class CakeArrayList {
    private static CakeArrayList mInstance;
    private ArrayList<Cake> cakes = null;

    private CakeArrayList() {
        cakes = new ArrayList<Cake>();
    }

    public static CakeArrayList getInstance() {
        if (mInstance == null)
            mInstance = new CakeArrayList();
        return mInstance;
    }

    public ArrayList<Cake> getArray() {
        return this.cakes;
    }

    public void remove(int position) {
        cakes.remove(position);
    }

}