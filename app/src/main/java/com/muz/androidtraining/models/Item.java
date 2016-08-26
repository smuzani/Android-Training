package com.muz.androidtraining.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Muz on 2016-08-23.
 */
public class Item {
    public String itemName;
    public String itemDescription;
    public String itemPlace;

    public Item(){}

    public Item(String itemName, String itemDescription, String itemPlace) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPlace = itemPlace;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("itemName", itemName);
        result.put("itemDescription", itemDescription);
        result.put("itemPlace", itemPlace);
        return result;
    }
}
