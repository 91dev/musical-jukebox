package com.moveinsync.musical.jukebox.repository;

import com.moveinsync.musical.jukebox.model.PlayListSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PlayListSongRepository extends JpaRepository<PlayListSong, Long> {
    @Modifying
    @Query("DELETE FROM PlayListSong p WHERE p.songId = :songId")
    int deleteBySongId(@Param("songId") Long songId);

    @Modifying
    @Query("DELETE FROM PlayListSong p WHERE p.playListId = :playListId")
    int deleteByPlayListId(@Param("playListId") Long playListId);

    @Modifying
    @Query("DELETE FROM PlayListSong p WHERE p.playListId = :playListId AND p.songId = :songId")
    int deleteByPlayListIdAndSongId(@Param("playListId") Long playListId, @Param("songId") Long songId);
}
