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

    public void deleteSong(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        if (button == deleteButton) {
            if (obSongList.size() == 0) {
                sendAlert(AlertType.ERROR, "Error", null, "No songs to delete");
                return;
            } else {
                Alert alert = sendAlert(AlertType.CONFIRMATION, "Confirmation", null, "Are you sure you want to delete this song?");

                if (alert.getResult() == ButtonType.OK) {
                    obSongList.remove(songList.getSelectionModel().getSelectedIndex());
                    songList.setItems(obSongList);
                    resetSong();
                }
            }
        }
    }

    public void addSong(ActionEvent event) {
        if (titleField.getText().isEmpty() || artistField.getText().isEmpty()) {
            sendAlert(AlertType.ERROR, "Error", null, "Please enter a title and artist");
        } else {
            Song song = new Song(
                titleField.getText(),
                artistField.getText(),
                albumField.getText(),
                yearField.getText()
            );

            obSongList.add(song);
            songList.setItems(obSongList);
            resetSong();
        }
    }

    public void editSong(ActionEvent event) {
        Song selectedSong = songList.getSelectionModel().getSelectedItem();

        if (selectedSong == null) {
            sendAlert(AlertType.ERROR, "Error", null, "Please select a song to edit");
        } else if (titleField.getText().isEmpty() || artistField.getText().isEmpty()) {
            sendAlert(AlertType.ERROR, "Error", null, "Please enter a title and artist");
        } else {
            selectedSong.setTitle(titleField.getText());
            selectedSong.setArtist(artistField.getText());
            selectedSong.setAlbum(albumField.getText());
            selectedSong.setYear(yearField.getText());
            songList.setItems(obSongList);
            resetSong();
        }
        
        return;
        }

    public void resetSong() {
        titleField.setText("");
        artistField.setText("");
        albumField.setText("");
        yearField.setText("");
    }

    public Alert sendAlert(AlertType alert_type, String title, String header, String content) {
        Alert alert = new Alert(alert_type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();

        return alert;
    }
}
