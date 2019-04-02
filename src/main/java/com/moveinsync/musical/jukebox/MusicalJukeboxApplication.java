package com.moveinsync.musical.jukebox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.moveinsync.musical.jukebox"})
@EnableJpaRepositories(basePackages = {"com.moveinsync.musical.jukebox.repository"})
@EntityScan(basePackages = {"com.moveinsync.musical.jukebox.model"})
public class MusicalJukeboxApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicalJukeboxApplication.class, args);
    }
}
