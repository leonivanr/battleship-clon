package com.codeoftheweb.battleship;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BattleshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(BattleshipApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData (PlayerRepo plRepos, GameRepo gameRepos, GamePlayerRepo gameplayerRepo, ShipRepo shipRepo) {
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

			List<String> location1 = new ArrayList<>();
			location1.add("H2");
			location1.add("H3");
			location1.add("H4");

			List<String> location2 = new ArrayList<>();
			location2.add("E1");
			location2.add("F1");
			location2.add("G1");

			List<String> location3 = new ArrayList<>();
			location3.add("B4");
			location3.add("B5");

			// Asignación de ShipTypes
			String shipType1 = "Submarine";
			String shipType2 = "Destroyer";
			String shipType3 = "Patrol Boat";

			// Instancias de Ship
			Ship ship1 = new Ship(location1, shipType1);
			Ship ship2 = new Ship(location2, shipType2);
			Ship ship3 = new Ship(location3, shipType3);
			Ship ship4 = new Ship(location1, shipType2);

			shipRepo.save(ship1);
			shipRepo.save(ship2);
			shipRepo.save(ship3);
			shipRepo.save(ship4);

			// Asignación de Ship para cada gamePlayer
			gamePlayerOne.addShip(ship1);
			gamePlayerOne.addShip(ship2);
			gamePlayerTwo.addShip(ship3);
			gamePlayerTwo.addShip(ship4);

			gameplayerRepo.save(gamePlayerOne);
			gameplayerRepo.save(gamePlayerTwo);


		};
	}
}
