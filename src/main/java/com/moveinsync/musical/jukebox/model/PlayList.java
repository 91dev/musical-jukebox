package com.moveinsync.musical.jukebox.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PLAYLIST")
public class PlayList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PLAYLIST_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playListId;

    @Column(name = "PLAYLIST_NAME")
    private String playlistName;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "PLAYLIST_SONG", joinColumns = @JoinColumn(name = "PLAYLIST_ID", referencedColumnName = "PLAYLIST_ID"), inverseJoinColumns = @JoinColumn(name = "SONG_ID", referencedColumnName = "SONG_ID"))
    private List<Song> songs = new ArrayList<>();

    public PlayList(String playlistName) {
        super();
        this.playlistName = playlistName;
    }

    public PlayList() {
    }

    public Long getPlayListId() {
        return playListId;
    }

    public void setPlayListId(Long playListId) {
        this.playListId = playListId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }


}
