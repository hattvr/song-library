package modules;

import models.Song;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class SongLibController implements Initializable {
    @FXML private ListView<Song> songList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        return;
    }
    
}