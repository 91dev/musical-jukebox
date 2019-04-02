package com.moveinsync.musical.jukebox.service;

import com.moveinsync.musical.jukebox.dto.RatingDto;
import com.moveinsync.musical.jukebox.dto.SongDto;

import java.util.List;
import java.util.Optional;

public interface JukeboxService {
    void addSongs(List<SongDto> songs);

    void createPlayList(String playListName);

    List<String> getAllPlayListName();

    List<SongDto> getSongsInPlayList(String playListName);

    String addSongsInPlayList(String playListName, List<SongDto> songsList);

    List<String> getPlayLists();

    String playFromPlayList(String playListName, boolean isRandom, String songName);

    Optional<SongDto> getSongByName(String songName);

    String playSongBySongName(String songName, boolean isRepeated);

    String playSongs(boolean isRandom);

    String provideSongsRating(String songName, RatingDto ratingDto);

    List<SongDto> getSongsList();

    void deleteSong(String songName);

    void deletePlayList(String playListName);

    void removePlayListSong(String playListName, String songName);
}
