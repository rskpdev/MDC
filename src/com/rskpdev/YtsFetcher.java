package com.rskpdev;

import org.json.JSONObject;
import java.io.BufferedReader;
import org.json.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * Created by Rahul on 07/14/16.
 * Fetches JSON strings from YIFY  api
 * needs a working internet connection or causes a crash(must be fixed)
 */
public class YtsFetcher {

    private static String top_movies;

    //fetches top movies from yify
    public static void fetchTop(int page, int limit) throws IOException {
        top_movies = fetchResult("https://yts.ag/api/v2/list_movies.json?limit="+limit+"&sort_by=download_count&page="+page);
    }

    public static String getTop(){
        return top_movies;
    }

    public static String searchMovie(String name) throws IOException {

        //cannot handle movies with spaces in title (fix this)
        return fetchResult("https://yts.ag/api/v2/list_movies.json?query_term=" + name);
    }



    //fetches JSON String from query url
    private static String fetchResult(String query) throws IOException {
        URLConnection connection = new URL(query).openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.connect();

        BufferedReader r  = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

}
