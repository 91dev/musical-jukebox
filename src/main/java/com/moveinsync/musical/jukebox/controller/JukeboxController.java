package com.moveinsync.musical.jukebox.controller;

import com.moveinsync.musical.jukebox.dto.RatingDto;
import com.moveinsync.musical.jukebox.dto.SongDto;
import com.moveinsync.musical.jukebox.service.JukeboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jukebox")
public class JukeboxController {

    @Autowired
    private JukeboxService jukeboxService;

    @PostMapping("/playlist")
    public ResponseEntity<String> addPlayList(@RequestBody @NotBlank String playListName) {
        jukeboxService.createPlayList(playListName);
        return new ResponseEntity<>(playListName + " PlayList Created!", HttpStatus.CREATED);
    }

    @GetMapping("/playlist")
    public ResponseEntity<List<String>> getPlayList() {
        List<String> playList = jukeboxService.getPlayLists();
        return new ResponseEntity<>(playList, HttpStatus.OK);
    }

    @PutMapping("/songs/playlist/{playListName}")
    public ResponseEntity<String> addSongsInPlayList(@PathVariable("playListName") @NotBlank String playListName, @RequestBody @NotNull List<SongDto> songsList) {
        String message = jukeboxService.addSongsInPlayList(playListName, songsList);
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

    @GetMapping("/play/playlist/{playListName}")
    public ResponseEntity<String> playFromPlayList(@PathVariable("playListName") @NotBlank String playListName, @RequestParam(required = false) boolean isRandom, @RequestParam(required = false) String songName) {
        String playMessage = jukeboxService.playFromPlayList(playListName, isRandom, songName);
        return new ResponseEntity<>(playMessage, HttpStatus.OK);
    }

    @GetMapping("/songs")
    public ResponseEntity<List<SongDto>> getSongsList() {
        List<SongDto> songsList = jukeboxService.getSongsList();
        return new ResponseEntity<>(songsList, HttpStatus.OK);
    }

    @GetMapping("/songs/{songName}")
    public ResponseEntity<Optional<SongDto>> getSongByName(@PathVariable("songName") @NotBlank String songName) {
        Optional<SongDto> song = jukeboxService.getSongByName(songName);
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @GetMapping("/songs/playlist/{playListName}")
    public ResponseEntity<List<SongDto>> getSongsInPlayList(@PathVariable("playListName") @NotBlank String playListName) {
        List<SongDto> songList = jukeboxService.getSongsInPlayList(playListName);
        return new ResponseEntity<>(songList, HttpStatus.OK);
    }

    @GetMapping("play/songs/{songName}")
    public ResponseEntity<String> playSongBySongName(@PathVariable @NotBlank String songName, @RequestParam boolean isRepeated) {
        String playMessage = jukeboxService.playSongBySongName(songName, isRepeated);
        return new ResponseEntity<>(playMessage, HttpStatus.OK);
    }

    @GetMapping("play/songs")
    public ResponseEntity<String> playSongs(@RequestParam(required = false) boolean isRandom) {
        String playMessage = jukeboxService.playSongs(isRandom);
        return new ResponseEntity<>(playMessage, HttpStatus.OK);
    }

    @PutMapping("/songs/rating/{songName}")
    public ResponseEntity<String> provideSongsRating(@PathVariable @NotBlank String songName, @RequestBody @NotNull RatingDto ratingDto) {
        String message = jukeboxService.provideSongsRating(songName, ratingDto);
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

    @PostMapping("/songs")
    public ResponseEntity<String> addSongs(@RequestBody @NotNull List<SongDto> songs) {
        jukeboxService.addSongs(songs);
        return new ResponseEntity<>("Songs added!", HttpStatus.CREATED);
    }

    @DeleteMapping("/songs/{songName}")
    public ResponseEntity<Void> deleteSong(@PathVariable @NotBlank String songName) {
        jukeboxService.deleteSong(songName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("playlist/{playListName}")
    public ResponseEntity<Void> deletePlayList(@PathVariable @NotBlank String playListName) {
        jukeboxService.deletePlayList(playListName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("playlist/{playListName}/songs/{songName}")
    public ResponseEntity<Void> removePlayListSong(@PathVariable @NotBlank String playListName, @PathVariable @NotBlank String songName) {
        jukeboxService.removePlayListSong(playListName, songName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
