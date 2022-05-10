package com.footballmanager;

import com.footballmanager.model.Player;
import com.footballmanager.service.PlayerService;
import com.footballmanager.service.PlayerServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FootballManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballManagerApplication.class, args);
    }

}
