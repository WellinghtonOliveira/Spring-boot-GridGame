package com.game.GridGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GridGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(GridGameApplication.class, args);
	}
 
}