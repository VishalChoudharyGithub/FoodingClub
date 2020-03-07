package com.example.foodingclub;



import java.util.Arrays;
import java.util.List;

public class items {
    String name;
    String pic;
    List<String>subCategoryNames;
    List<String>subCategoryPics;
    List<String>subCategoryRates;

    public  items(){}

    public items(String name, String pic, String[] subCategoryNames, String[] subCategoryPics, String[] subCategoryRates) {
        this.name = name;
        this.pic = pic;
        this.subCategoryNames = Arrays.asList(subCategoryNames);
        this.subCategoryPics = Arrays.asList(subCategoryPics);
        this.subCategoryRates = Arrays.asList(subCategoryRates);
    }

    public String getName() {
        return name;
    }

    public String getPic() {
        return pic;
    }

    public List<String> getSubCategoryNames() {
        return subCategoryNames;
    }

    public List<String> getSubCategoryPics() {
        return subCategoryPics;
    }

    public List<String> getSubCategoryRates() {
        return subCategoryRates;
    }
}
