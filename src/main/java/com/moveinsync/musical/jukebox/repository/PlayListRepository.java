package com.moveinsync.musical.jukebox.repository;

import com.moveinsync.musical.jukebox.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayListRepository extends JpaRepository<PlayList, Long> {
    Optional<PlayList> findFirstByPlaylistName(String playlistName);

}
