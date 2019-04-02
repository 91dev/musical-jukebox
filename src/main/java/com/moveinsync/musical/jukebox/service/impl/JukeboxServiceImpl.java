package com.moveinsync.musical.jukebox.service.impl;

import com.moveinsync.musical.jukebox.common.JukeBoxConstant;
import com.moveinsync.musical.jukebox.common.Rating;
import com.moveinsync.musical.jukebox.dto.RatingDto;
import com.moveinsync.musical.jukebox.dto.SongDto;
import com.moveinsync.musical.jukebox.model.PlayList;
import com.moveinsync.musical.jukebox.model.PlayListSong;
import com.moveinsync.musical.jukebox.model.Song;
import com.moveinsync.musical.jukebox.repository.PlayListRepository;
import com.moveinsync.musical.jukebox.repository.PlayListSongRepository;
import com.moveinsync.musical.jukebox.repository.SongRepository;
import com.moveinsync.musical.jukebox.service.JukeboxService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class JukeboxServiceImpl implements JukeboxService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JukeboxServiceImpl.class);

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private PlayListRepository playListRepository;

    @Autowired
    private PlayListSongRepository playListSongRepository;

    @Override
    public void addSongs(List<SongDto> songs) {
        songs.forEach(s -> songRepository.save(new Song(s.getSongName(), s.getArtistName(), s.getMovieName(), s.getRating())));

    }

    @Override
    public void createPlayList(String playListName) {
        Optional<PlayList> playListOptional = playListRepository.findFirstByPlaylistName(playListName);
        if (!playListOptional.isPresent()) {
            playListRepository.save(new PlayList(playListName));
        }
    }

    @Override
    public List<String> getAllPlayListName() {
        List<String> playListNames = new ArrayList<>();
        playListRepository.findAll().forEach(pl -> playListNames.add(pl.getPlaylistName()));
        return playListNames;
    }

    @Override
    public List<SongDto> getSongsInPlayList(String playListName) {
        Optional<PlayList> playListOptional = playListRepository.findFirstByPlaylistName(playListName);
        if (!playListOptional.isPresent()) {
            return new ArrayList<>(0);
        }
        return playListOptional.get().getSongs().stream().map(s -> new SongDto(s.getSongName(), s.getArtistName(), s.getMovieName(), s.getRating() == null ? 0 : s.getRating().getRating())).collect(Collectors.toList());
    }

    @Override
    public String addSongsInPlayList(String playListName, List<SongDto> songsList) {
        Optional<PlayList> playListOptional = playListRepository.findFirstByPlaylistName(playListName);
        if (!playListOptional.isPresent()) {
            return JukeBoxConstant.MSG_NO_PLAYLIST_FOUND + playListName;
        }
        PlayList playList = playListOptional.get();
        if (songsList != null && !songsList.isEmpty()) {
            songsList.forEach(pld -> {
                Song song;
                Optional<Song> songOptional = songRepository.findOneBySongName(pld.getSongName());
                song = songOptional.orElseGet(() -> songRepository.save(new Song(pld.getSongName(), pld.getArtistName(), pld.getMovieName(), pld.getRating())));
                if (!playList.getSongs().contains(song)) {
                    playListSongRepository.save(new PlayListSong(playList.getPlayListId(), song.getSongId()));
                }
            });
        }
        return "Songs Added in PlayList " + playListName;
    }

    @Override
    public List<String> getPlayLists() {
        List<PlayList> list = playListRepository.findAll();
        List<String> listPlayList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            list.forEach(l -> listPlayList.add(l.getPlaylistName()));
        }
        return listPlayList;
    }

    @Override
    public String playFromPlayList(String playListName, boolean isRandom, String songName) {
        Optional<PlayList> playListOptional = playListRepository.findFirstByPlaylistName(playListName);
        if (playListOptional.isPresent()) {
            if (isRandom) {
                return playListOptional.get().getSongs().get(getRandomNumber(playListOptional.get().getSongs().size())).play() + " Randomly";
            }
            if (StringUtils.isNotBlank(songName)) {
                Optional<Song> song = playListOptional.get().getSongs().stream().filter(s -> s.getSongName().equals(songName)).findFirst();
                if (!song.isPresent()) {
                    return "No Song Found in PlayList With Name" + songName;
                }
                return song.get().play();
            }
            return playListOptional.get().getSongs().get(0).play();
        }
        return JukeBoxConstant.MSG_NO_PLAYLIST_FOUND + playListName;
    }

    private int getRandomNumber(int number) {
        return new Random().nextInt(number - 1);
    }

    @Override
    public Optional<SongDto> getSongByName(String songName) {
        Optional<Song> songOptional = songRepository.findOneBySongName(songName);
        if (songOptional.isPresent()) {
            Song song = songOptional.get();
            return Optional.of(new SongDto(song.getSongName(), song.getArtistName(), song.getMovieName(), song.getRating().rating));
        }
        return Optional.empty();
    }

    @Override
    public String playSongBySongName(String songName, boolean isRepeated) {
        Optional<Song> songOptional = songRepository.findOneBySongName(songName);
        if (songOptional.isPresent()) {
            if (isRepeated) {
                return songOptional.get().play() + " Repeatedly";
            }
            return songOptional.get().play();
        }
        return "No Song Found With Name" + songName;
    }

    @Override
    public String playSongs(boolean isRandom) {
        List<Song> songList = songRepository.findAll();
        if (songList != null && !songList.isEmpty()) {
            if (isRandom) {
                return songList.get(getRandomNumber(songList.size())).play() + " Randomly From Songs Collection";
            }
            return songList.get(0).play() + " From Songs Collection";
        }
        return "No Songs Found in Songs Collection";
    }

    @Override
    public String provideSongsRating(String songName, RatingDto ratingDto) {
        Optional<Song> songOptional = songRepository.findOneBySongName(songName);
        if (songOptional.isPresent()) {
            Song song = songOptional.get();
            song.setRating(Rating.getRating(ratingDto.getRating()));
            songRepository.save(song);
            return "Songs Rating Updated";
        }
        return "No Songs Found with Name " + songName;
    }

    @Override
    public List<SongDto> getSongsList() {
        List<SongDto> songDtoList = new ArrayList<>();
        List<Song> songList = songRepository.findAll();
        if (songList != null && !songList.isEmpty()) {
            songList.forEach(s -> songDtoList.add(new SongDto(s.getSongName(), s.getArtistName(), s.getMovieName(), s.getRating() == null ? 0 : s.getRating().getRating())));
        }
        return songDtoList;
    }

    @Override
    public void deleteSong(String songName) {
        Optional<Song> songOptional = songRepository.findOneBySongName(songName);
        if (songOptional.isPresent()) {
            Song song = songOptional.get();
            playListSongRepository.deleteBySongId(song.getSongId());
            songRepository.delete(song);
            LOGGER.info("Song Deleted with Name {}", songName);
        }
    }

    @Override
    public void deletePlayList(String playListName) {
        Optional<PlayList> playListOptional = playListRepository.findFirstByPlaylistName(playListName);
        if (playListOptional.isPresent()) {
            PlayList playList = playListOptional.get();
            playListSongRepository.deleteByPlayListId(playList.getPlayListId());
            playListRepository.delete(playList);
            LOGGER.info("PlayList Deleted with Name {}", playListName);
        }
    }

    @Override
    public void removePlayListSong(String playListName, String songName) {
        Optional<PlayList> playListOptional = playListRepository.findFirstByPlaylistName(playListName);
        if (playListOptional.isPresent()) {
            for (Song song : playListOptional.get().getSongs()) {
                if (song.getSongName().equals(songName)) {
                    playListSongRepository.deleteByPlayListIdAndSongId(playListOptional.get().getPlayListId(), song.getSongId());
                    LOGGER.info("{} Song Removed From PlayList {}", songName, playListName);
                }
            }
        }
    }
}
