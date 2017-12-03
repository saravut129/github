package com.example.fastorder.Model;

/**
 * Created by Admin on 2/12/2560.
 */

public class FoodStoreItem {
    public final String englishName;
    public final String thaiName;
    public final int picture;
    public final String details;

    public FoodStoreItem(String englishName, String thaiName, int picture, String details) {
        this.englishName = englishName;
        this.thaiName = thaiName;
        this.picture = picture;
        this.details = details;
    }
}
