package modules;

import models.Song;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SongLibController implements Initializable {
    @FXML 
    private ListView<Song> songList;
    @FXML
    public ListView<Song> songList;
    @FXML
    private TextField titleField;
    @FXML
    private TextField artistField;
    @FXML
    private TextField albumField;
    @FXML
    private TextField yearField;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    ObservableList<Song> obSongList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        return;
    }
    
    // Reading the contents of a file and returning it as a string
    private String readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        StringBuilder fileContents = new StringBuilder((int)file.length());
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + System.lineSeparator());
            }
            return fileContents.toString();
        }
    }
    
    private void saveToFile(ObservableList<Song> songList) throws IOException {
        JSONArray songArray = new JSONArray(obSongList);
        FileWriter file = new FileWriter("src/songs.json");
        file.write(songArray.toString());
        file.close();
    } 
    
    public void deleteSong(ActionEvent event) {
        Button button = (Button) event.getSource();
        if (button == deleteButton) {
            if(obSongList.size() == 0) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No songs to delete");
                alert.showAndWait();
            }
        }
    }
    
    public void addSong(ActionEvent event) {
        
    }
    
    public void editSong(ActionEvent event) {
        
    }
    
}