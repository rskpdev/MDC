package com.rskpdev;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Rahul on 07/14/16.
 * this class is used to hold code for  parsing JSON strings
 * parser is taken from json.org (http://mvnrepository.com/artifact/org.json/json)
 * import the json library for it to work
 */
public class Parser{

    /*
    gets an array of urls of the movies covers given a list of movie in JSON
     */
    public String[] coverParse(String input){
        JSONObject obj = new JSONObject(input);
        JSONArray arr = obj.getJSONObject("data").getJSONArray("movies");
        String[] output = new String[arr.length()];

        for (int i = 0; i < arr.length(); i++)
        {
            output[i] = arr.getJSONObject(i).getString("large_cover_image");
        }

        return output;
    }

    //gets the title of a single movie
    public String titleParse(String input){
        JSONObject obj = new JSONObject(input);
        JSONArray arr = obj.getJSONObject("data").getJSONArray("movies");
        String output = arr.getJSONObject(0).getString("title");
        return output;
    }

    //gets the year of a single movie
    public int yearParse(String input){
        JSONObject obj = new JSONObject(input);
        JSONArray arr = obj.getJSONObject("data").getJSONArray("movies");
        int output = arr.getJSONObject(0).getInt("year");
        return output;
    }

    //gets the rating (IMDB) of a single movie
    public double ratingParse(String input){
        JSONObject obj = new JSONObject(input);
        JSONArray arr = obj.getJSONObject("data").getJSONArray("movies");
        double output = arr.getJSONObject(0).getDouble("rating");           //rating is given in double
        return output;
    }

    //gets the summary of a single movie
    public String summaryParse(String input){
        JSONObject obj = new JSONObject(input);
        JSONArray arr = obj.getJSONObject("data").getJSONArray("movies");
        String output = arr.getJSONObject(0).getString("summary");
        return output;
    }

}
