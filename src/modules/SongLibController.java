package modules;

import models.Song;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class SongLibController implements Initializable {
    @FXML ListView<Song> songList;
    
    @FXML Button addButton;
    @FXML Button editButton;
    @FXML Button deleteButton;
    
    @FXML TextField titleField;
    @FXML TextField artistField;
    @FXML TextField albumField;
    @FXML TextField yearField;

    ObservableList<Song> obSongList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            readFile("src/attributes/songs.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return;
    }

    // Reading the contents of a file and returning it as a string
    private void readFile(String fileName) throws FileNotFoundException {
        Path fp = Paths.get(fileName);

        try {
            String content = new String(Files.readAllBytes(fp), StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(content);
        
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String artist = jsonObject.getString("artist");
                String album = jsonObject.getString("album");
                String year = jsonObject.getString("year");
                Song song = new Song(title, artist, album, year);

                obSongList.add(song);
            }
            
            songList.setItems(obSongList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile(ObservableList<Song> songList) throws IOException {
        JSONArray songArray = new JSONArray();
        FileWriter file = new FileWriter("attributes/songs.json");
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
        int count = 0;
        if (titleField.getText().isEmpty() || artistField.getText().isEmpty()) {
            sendAlert(AlertType.ERROR, "Error", null, "Please enter a title and artist");
        } else if(!isValidYear(yearField.getText())) {
            sendAlert(AlertType.ERROR, "Error", null, "Please enter a valid year");
        } else {
            Song song = new Song(titleField.getText(), artistField.getText(), albumField.getText(), yearField.getText());
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
    
    private boolean isValidYear(String yearString) {
        try {
            int year = Integer.parseInt(yearString);
            return year >= 0 && year <= 9999;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
