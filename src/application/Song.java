package application;

public class Song {
    private String title;
    private String artist;
    private String album;
    private String year;

    public Song(String title, String artist, String album, String year) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return title + " by " + artist;
    }
}
