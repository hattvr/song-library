/*
Rutgers University CS213 Software Methodology
Assignment 1 - Song Library Application
@author Zaeem Zahid
@author Shiv Patel
*/

package application;

import javafx.application.Application;
import javafx.stage.Stage;


public class SongLib extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Do application setup here
        } catch (Exception error) {
            // Catch exceptions to view tracebacks
            error.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}