package com.moveinsync.musical.jukebox.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PLAYLIST_SONG")
public class PlayListSong implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLS_ID")
    private Long id;

    @Column(name = "PLAYLIST_ID")
    private Long playListId;

    @Column(name = "SONG_ID")
    private Long songId;

    public PlayListSong(Long playListId, Long songId) {
        this.playListId = playListId;
        this.songId = songId;
    }

    public PlayListSong() {
    }

    public Long getPlayListId() {
        return playListId;
    }

    public void setPlayListId(Long playListId) {
        this.playListId = playListId;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
