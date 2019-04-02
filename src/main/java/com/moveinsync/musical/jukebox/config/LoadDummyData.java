package com.moveinsync.musical.jukebox.config;

import com.moveinsync.musical.jukebox.model.PlayList;
import com.moveinsync.musical.jukebox.model.PlayListSong;
import com.moveinsync.musical.jukebox.model.Song;
import com.moveinsync.musical.jukebox.repository.PlayListRepository;
import com.moveinsync.musical.jukebox.repository.PlayListSongRepository;
import com.moveinsync.musical.jukebox.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@DependsOn({"songRepository", "playListRepository", "playListSongRepository"})
public class LoadDummyData {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private PlayListRepository playListRepository;

    @Autowired
    private PlayListSongRepository playListSongRepository;

    @PostConstruct
    void loadSongs() {
        List<Song> songList = new ArrayList<>();
        songList.add(new Song("One Kiss", "Calvin Harris & Dua Lipa", "One Kiss", 5));
        songList.add(new Song("God's Plan", "Drake", "God's Plan", 5));
        songList.add(new Song("Shotgun", "George Ezra", "Shotgun", 4));
        songList.add(new Song("My Heart Will Go On", "Celine Dion", "Titanic", 5));
        songList.add(new Song("The Men In Black", "Will Smith", "Men In Black", 3));
        songList.add(new Song("Tareefan", "Badshah", "Veere Di Wedding", 5));
        songList.add(new Song("Bom Diggy Diggy", "Zack Knight | Jasmin Walia", "Sonu Ke Titu Ki Sweety", 5));
        songList.add(new Song("Dilbar", "Neha Kakkar | Dhvani", "Satyameva Jayate", 5));
        songList.add(new Song("Proper Patola", "Badshah | Diljit", "Namaste England", 5));
        songList.add(new Song("Tere Naal Nachna", "Badshah, Sunanda S", "Nawabzaade", 5));
        songRepository.saveAll(songList);
    }

    @PostConstruct
    void loadPlayList() {
        playListRepository.saveAll(Arrays.asList(new PlayList("Hollywood Top 5"), new PlayList("Bollywood Top 5 2018")));
    }

    @PostConstruct
    void loadPlayListSong() {
        List<PlayListSong> playListSongs = new ArrayList<>();
        playListSongs.add(new PlayListSong(1L, 1L));
        playListSongs.add(new PlayListSong(1L, 2L));
        playListSongs.add(new PlayListSong(1L, 3L));
        playListSongs.add(new PlayListSong(1L, 4L));
        playListSongs.add(new PlayListSong(1L, 5L));
        playListSongs.add(new PlayListSong(2L, 6L));
        playListSongs.add(new PlayListSong(2L, 7L));
        playListSongs.add(new PlayListSong(2L, 8L));
        playListSongs.add(new PlayListSong(2L, 9L));
        playListSongs.add(new PlayListSong(2L, 10L));
        playListSongRepository.saveAll(playListSongs);
    }
}
