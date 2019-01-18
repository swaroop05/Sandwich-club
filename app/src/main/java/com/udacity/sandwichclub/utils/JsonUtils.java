package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        String mainName = null;
        String description = null;
        String imageUrl = null;
        String placeOfOrigin = null;
        JSONArray ingredientsArray;
        JSONArray alsoKnownAsArray;
        String ingredients = null;
        String alsoKnownAs = null;
        int ingredientsArrayCount = 0;
        int alsoKnownAsArrayCount = 0;
        final String NOT_PROVIDED = "Not Provided";
        try {
            JSONObject reader = new JSONObject(json);
            mainName = reader.getJSONObject("name").getString("mainName");
            description = reader.getString("description");
            if (description.isEmpty()) {
                description = NOT_PROVIDED;
            }
            imageUrl = reader.getString("image");
            placeOfOrigin = reader.getString("placeOfOrigin");
            if (placeOfOrigin.isEmpty()) {
                placeOfOrigin = NOT_PROVIDED;
            }
            ingredientsArray = reader.getJSONArray("ingredients");
            ingredientsArrayCount = ingredientsArray.length();
            if (ingredientsArrayCount != 0) {
                ingredients = ingredientsArray.get(0).toString();
                for (int i = 1; i < ingredientsArrayCount; i++) {
                    String ingredient = ingredientsArray.getString(i);
                    ingredients = ingredients + ", " + ingredient;
                }
            } else {
                ingredients = NOT_PROVIDED;
            }


            alsoKnownAsArray = reader.getJSONObject("name").getJSONArray("alsoKnownAs");
            alsoKnownAsArrayCount = alsoKnownAsArray.length();
            if (alsoKnownAsArrayCount != 0) {
                alsoKnownAs = alsoKnownAsArray.get(0).toString();
                for (int i = 1; i < alsoKnownAsArrayCount; i++) {
                    String singleAlsoKnownAs = ingredientsArray.getString(i);
                    alsoKnownAs = alsoKnownAs + ", " + singleAlsoKnownAs;
                }
            } else {
                alsoKnownAs = NOT_PROVIDED;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, imageUrl, ingredients);
    }
}
