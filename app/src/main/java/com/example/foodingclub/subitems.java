package com.example.foodingclub;

public class subitems {

    String[] catNames;
    String[] catPics;
    String[] catRates;

    public subitems(String[] catNames, String[] catPics, String[] catRates) {
        this.catNames = catNames;
        this.catPics = catPics;
        this.catRates = catRates;
    }

    public String[] getCatNames()
    {
        return catNames;
    }

    public String[] getCatPics() {
        return catPics;
    }

    public String[] getCatRates() {
        return catRates;
    }
}
