package com.rskpdev;

import com.rskpdev.Main;
import com.rskpdev.Parser;
import com.rskpdev.YtsFetcher;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller
implements Initializable {
    @FXML
    TextField search_box;
    @FXML
    Label title;
    @FXML
    Label year;
    @FXML
    Label summary;
    @FXML
    Label rating;
    @FXML
    Label available;

    public void initialize(URL location, ResourceBundle resources) {
    }

    //search button is clicked
    public void searchClicked() throws IOException {
        //find movie on YIFY
        //if field is left empty it give a random movie ( fix this)
        String movie = YtsFetcher.searchMovie((String)this.search_box.getText());

        Parser parse = new Parser();
        //set all fields
        this.summary.setText(parse.summaryParse(movie));
        this.title.setText(parse.titleParse(movie));
        this.year.setText(String.valueOf(parse.yearParse(movie)));
        this.rating.setText(String.valueOf(parse.ratingParse(movie)) + "/10 IMDB");
        //search for the movie on DC
        Main.SearchMovie(this.title.getText());
    }

    public void download(Event event) {
    }
}