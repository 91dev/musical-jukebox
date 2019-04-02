package com.moveinsync.musical.jukebox.dto;

public class SongDto {
    private String songName;
    private String artistName;
    private String movieName;
    private int rating;

    public SongDto(String songName, String artistName, String movieName, int rating) {
        super();
        this.songName = songName;
        this.artistName = artistName;
        this.movieName = movieName;
        this.rating = rating;
    }

    public SongDto() {
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


}
