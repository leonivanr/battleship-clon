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

			plRepos.save(new Player("jackbauer@gmail.com"));
			plRepos.save(new Player("chloebrian@gmail.com"));
			plRepos.save(new Player("qwerty@gmail.com"));

			Date date = new Date();
			Date dateOne = Date.from(date.toInstant().plusSeconds(3600));
			Date dateTwo = Date.from(date.toInstant().plusSeconds(7200));
			Date dateTree = Date.from(date.toInstant().plusSeconds(10800));

//			gameRepos.save(new Game(date));
//			gameRepos.save(new Game(dateOne));
//			gameRepos.save(new Game(dateTwo));

			gameplayerRepo.save(new GamePlayer(new Game(date), new Player("qwerty@gmail.com"),date));
			gameplayerRepo.save(new GamePlayer(new Game(dateOne), new Player("asd@gmail.com"),dateOne));
			gameplayerRepo.save(new GamePlayer(new Game(dateTwo), new Player("qwey@gmail.com"),dateTwo));
			gameplayerRepo.save(new GamePlayer(new Game(dateTree), new Player("asdf@gmail.com"),dateTree));


		};
	}
}
