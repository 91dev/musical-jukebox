package com.moveinsync.musical.jukebox.repository;

import com.moveinsync.musical.jukebox.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<Song> findOneBySongName(String songName);
}
