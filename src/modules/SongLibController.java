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

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class SongLibController implements Initializable {
    @FXML // fx:id="ALBUM_LIST_VIEW"
    ListView<Song> songList;
    @FXML // fx:id="ADD_SONG_BUTTON"
    Button addButton;
    @FXML // fx:id="EDIT_SONG_BUTTON"
    Button editButton;
    @FXML // fx:id="DELETE_SONG_BUTTON"
    Button deleteButton;
    @FXML // fx:id=""
    TextField titleField;
    @FXML // fx:id=""
    TextField artistField;
    @FXML // fx:id=""
    TextField albumField;
    @FXML // fx:id=""
    TextField yearField;

    private ObservableList<Song> obSongList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        obSongList = FXCollections.observableArrayList();
        songList.setItems(obSongList);

        return;
    }

    // Reading the contents of a file and returning it as a string
    private String readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        StringBuilder fileContents = new StringBuilder((int) file.length());
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + System.lineSeparator());
            }
            return fileContents.toString();
        }
    }

    private void saveToFile(ObservableList<Song> songList) throws IOException {
        JSONArray songArray = new JSONArray();
        FileWriter file = new FileWriter("json/songs.json");
        file.write(songArray.toString());
        file.close();
    }

    public void deleteSong(ActionEvent event) {
        Button button = (Button) event.getSource();
        if (button == deleteButton) {
            if (obSongList.size() == 0) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No songs to delete");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this song?");
            alert.showAndWait();
        }
    }

    public void addSong(ActionEvent event) {

    }

    public void editSong(ActionEvent event) {

    }

}
