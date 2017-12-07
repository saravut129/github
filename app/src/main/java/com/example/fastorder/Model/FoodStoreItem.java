package com.example.fastorder.Model;

/**
 * Created by Admin on 2/12/2560.
 */

public class FoodStoreItem {
    public final int id;
    public final String thaiName;
    public final String picture;
    public final String details;

    public FoodStoreItem(int id, String thaiName, String details, String picture) {
        this.id = id;
        this.thaiName = thaiName;
        this.details = details;
        this.picture = picture;

    }
}
