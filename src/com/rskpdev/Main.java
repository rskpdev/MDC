package com.rskpdev;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main
extends Application {

    static UiLogStream log = new UiLogStream();
    static SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
    static Calendar calendarNow = Calendar.getInstance();
    static BotLogic logic = new BotLogic((PrintStream)log);

    public static void main(String[] args) throws IOException {
        Main.launch((String[])args);
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent)FXMLLoader.load((URL)this.getClass().getResource("MDC.fxml"));
        primaryStage.setTitle("MDC");
        primaryStage.setScene(new Scene(root, 1200.0, 500.0));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event ->DisconnectDC()); //disconnect form DC when closing window
        this.ConnectToDC(); //connect to DC on start (should change to connect on button click)
    }

    public void ConnectToDC() {

        //edit the below parameters for your hub (change so that this is user editable in a setting window)
        logic.configBot("MovieBot" //Bot name
                , "192.168.210.116", //Bot IP
                9000, //Listen port
                10000, //listen port UDP
                "", //password
                "rskpbot", //description
                "ADSL1", //connection type
                "@", //email
                "0", //share size
                2, //upload slots
                4, //download slots
                true, //active mode
                "C:/Users/Rahul/Desktop/tmp", //download folder (must exist, or crashes)
                "C:/Users/Rahul/Desktop/tmp/Incomplete");//incomplete downloads folder (must exist, or crashes)


        //hub IP and port(also change so that this is user editable in a setting window)
        if (logic.connect("192.168.1.4", 411)) {
            log.println(formatter.format(calendarNow.getTime()) + ": Is connected now");
        }
    }

    private void DisconnectDC() {
        logic.disconnect();
        log.println(formatter.format(calendarNow.getTime()) + ": Is disconnected now");
    }

    public static void SearchMovie(String name) {
        logic.search(name);
        log.println(formatter.format(calendarNow.getTime()) + ": Search finished");
    }

    public static void DownloadMovie(String magnet) {
        logic.downloadFile(magnet); //Downloading needs a magnet URL
        log.println(formatter.format(calendarNow.getTime()) + ": Starting download process");
    }
}