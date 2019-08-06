package com.codeoftheweb.battleship;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class BattleshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(BattleshipApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData (PlayerRepo plRepos, GameRepo gameRepos, GamePlayerRepo gameplayerRepo) {
		return(args) -> {
			Player playerOne = new Player("j.bauer@ctu.gov");
			Player playerTwo = new Player("c.obrian@ctu.gov");
			Player playerThree = new Player("kim_bauer@gmail.com");
			Player playerFour = new Player("t.almeida@ctu.gov");

			Date dateOne = new Date();
			Date dateTwo = Date.from(dateOne.toInstant().plusSeconds(3600));
			Date dateThree = Date.from(dateOne.toInstant().plusSeconds(7200));

			plRepos.save(playerOne);
			plRepos.save(playerTwo);
			plRepos.save(playerThree);
			plRepos.save(playerFour);

			Game gameOne = new Game (dateOne);
			Game gameTwo = new Game (dateTwo);
			Game gameThree = new Game (dateThree);

			gameRepos.save(gameOne);
			gameRepos.save(gameTwo);
			gameRepos.save(gameThree);

			GamePlayer gamePlayerOne = new GamePlayer(gameOne,playerOne);
			GamePlayer gamePlayerTwo = new GamePlayer(gameOne,playerTwo);
			GamePlayer gamePlayerThree = new GamePlayer(gameTwo,playerThree);
			GamePlayer gamePlayerFour = new GamePlayer(gameTwo,playerFour);
			GamePlayer gamePlayerFive = new GamePlayer(gameThree,playerTwo);
			GamePlayer gamePlayerSix = new GamePlayer(gameThree,playerThree);


			gameplayerRepo.save(gamePlayerOne);
			gameplayerRepo.save(gamePlayerTwo);
			gameplayerRepo.save(gamePlayerThree);
			gameplayerRepo.save(gamePlayerFour);
			gameplayerRepo.save(gamePlayerFive);
			gameplayerRepo.save(gamePlayerSix);


		};
	}
}
