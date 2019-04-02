package com.moveinsync.musical.jukebox.model;

import com.moveinsync.musical.jukebox.common.Rating;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "SONG")
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SONG_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songId;

    @Column(name = "SONG_NAME")
    @NotBlank
    private String songName;

    @Column(name = "ARTIST_NAME")
    @NotBlank
    private String artistName;

    @Column(name = "MOVIE_NAME")
    @NotBlank
    private String movieName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "RATINGS")
    private Rating rating;

    public Song(@NotBlank String songName, @NotBlank String artistName, @NotBlank String movieName,
                int rating) {
        super();
        this.songName = songName;
        this.artistName = artistName;
        this.movieName = movieName;
        this.rating = Rating.getRating(rating);
    }

    public Song() {
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
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

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String play() {
        return "Playing Song " + getSongName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(songId, song.songId) &&
                Objects.equals(songName, song.songName) &&
                Objects.equals(artistName, song.artistName) &&
                Objects.equals(movieName, song.movieName) &&
                rating == song.rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(songId, songName, artistName, movieName, rating);
    }
}
