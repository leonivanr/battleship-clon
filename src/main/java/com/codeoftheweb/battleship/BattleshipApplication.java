package com.codeoftheweb.battleship;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BattleshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(BattleshipApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData (PlayerRepo plyRepo,GameRepo gameRepos,GamePlayerRepo gameplayerRepo,ShipRepo shipRepo,SalvoRepo salvoRepo) {
		return(args) -> {
			Player player1 = new Player("j.bauer@ctu.gov");
			Player player2 = new Player("c.obrian@ctu.gov");
			Player player3 = new Player("kim_bauer@gmail.com");
			Player player4 = new Player("t.almeida@ctu.gov");

			Date date1 = new Date();
			Date date2 = Date.from(date1.toInstant().plusSeconds(3600));
			Date date3 = Date.from(date1.toInstant().plusSeconds(7200));
			Date date4 = Date.from(date1.toInstant().plusSeconds(8506));

			plyRepo.save(player1);
			plyRepo.save(player2);
			plyRepo.save(player3);
			plyRepo.save(player4);

			Game game1 = new Game (date1);
			Game game2 = new Game (date2);
			Game game3 = new Game (date3);
			Game game4 = new Game (date4);

			gameRepos.save(game1);
			gameRepos.save(game2);
			gameRepos.save(game3);
			gameRepos.save(game4);

			// GamePlayer creation.
			GamePlayer gamePlayer1 = new GamePlayer(game1,player1);
			GamePlayer gamePlayer2 = new GamePlayer(game1,player2);

			GamePlayer gamePlayer3 = new GamePlayer(game2,player3);
			GamePlayer gamePlayer4 = new GamePlayer(game2,player4);


			gameplayerRepo.save(gamePlayer1);
			gameplayerRepo.save(gamePlayer2);
			gameplayerRepo.save(gamePlayer3);
			gameplayerRepo.save(gamePlayer4);

			//Location creation.
			List<String> location1 = new ArrayList<>(Arrays.asList("H2","H3","H4"));
			List<String> location2 = new ArrayList<>(Arrays.asList("A2","B2","C2"));
			List<String> location3 = new ArrayList<>(Arrays.asList("E1","E2","E3"));
			List<String> location4 = new ArrayList<>(Arrays.asList("J5","J6","J7","J8"));
			List<String> location5 = new ArrayList<>(Arrays.asList("F10","E10","D10","C10","B10"));
			List<String> location6 = new ArrayList<>(Arrays.asList("C5","D5","E5"));
			List<String> location7 = new ArrayList<>(Arrays.asList("H6","H7","H8","H9"));
			List<String> location8 = new ArrayList<>(Arrays.asList("I2","I3","I4"));
			List<String> location9 = new ArrayList<>(Arrays.asList("D8","E8","F8","G8"));
			List<String> location10 = new ArrayList<>(Arrays.asList("G5","H5","I5","J5"));

			// Shiptypes.
			String shipTypeSubmarine = "Submarine";
			String shipTypeDestroyer = "Destroyer";
			String shipTypePatrolBoat = "Patrol Boat";
			String shipTypeBattleship = "Battleship";
			String shipTypeCarrier = "Carrier";

			// Instanciate ships.
			Ship ship1 = new Ship(location1, shipTypeSubmarine);
			Ship ship2 = new Ship(location2, shipTypeDestroyer);
			Ship ship3 = new Ship(location3, shipTypePatrolBoat);
			Ship ship4 = new Ship(location4, shipTypeBattleship);
			Ship ship5 = new Ship(location5, shipTypeCarrier);
			Ship ship6 = new Ship(location6, shipTypeSubmarine);
			Ship ship7 = new Ship(location7, shipTypeDestroyer);
			Ship ship8 = new Ship(location8, shipTypePatrolBoat);
			Ship ship9 = new Ship(location9, shipTypeBattleship);
			Ship ship10 = new Ship(location10, shipTypePatrolBoat);
			Ship ship11 = new Ship(location8, shipTypeDestroyer);
			Ship ship12 = new Ship(location5, shipTypeCarrier);

			shipRepo.save(ship1);
			shipRepo.save(ship2);
			shipRepo.save(ship3);
			shipRepo.save(ship4);
			shipRepo.save(ship5);
			shipRepo.save(ship6);
			shipRepo.save(ship7);
			shipRepo.save(ship8);
			shipRepo.save(ship9);
			shipRepo.save(ship10);
			shipRepo.save(ship11);
			shipRepo.save(ship12);

			List<String> salvoLocation1 = new ArrayList<>(Arrays.asList("A6","B9"));
			List<String> salvoLocation2 = new ArrayList<>(Arrays.asList("G1","H4"));
			List<String> salvoLocation3 = new ArrayList<>(Arrays.asList("J5","F3"));
			List<String> salvoLocation4= new ArrayList<>(Arrays.asList("B3","B7"));
			List<String> salvoLocation5 = new ArrayList<>(Arrays.asList("D1","E3"));
			List<String> salvoLocation6 = new ArrayList<>(Arrays.asList("C3","F6"));

			Salvo salvo1 = new Salvo(salvoLocation1,1);
			Salvo salvo2 = new Salvo(salvoLocation2,1);
			Salvo salvo3 = new Salvo(salvoLocation3,2);
			Salvo salvo4 = new Salvo(salvoLocation4,2);
			Salvo salvo5 = new Salvo(salvoLocation5,3);
			Salvo salvo6 = new Salvo(salvoLocation6,3);

			salvoRepo.save(salvo1);
			salvoRepo.save(salvo2);
			salvoRepo.save(salvo3);
			salvoRepo.save(salvo4);
			salvoRepo.save(salvo5);
			salvoRepo.save(salvo6);

			// Add ships to gameplayer.
			gamePlayer1.addShip(ship1);
			gamePlayer1.addShip(ship2);
			gamePlayer1.addShip(ship3);
			gamePlayer1.addSalvo(salvo1);
			gamePlayer1.addSalvo(salvo3);
			gamePlayer1.addSalvo(salvo5);
			gameplayerRepo.save(gamePlayer1);

			gamePlayer2.addShip(ship4);
			gamePlayer2.addShip(ship5);
			gamePlayer2.addShip(ship6);
			gamePlayer2.addSalvo(salvo2);
			gamePlayer2.addSalvo(salvo4);
			gamePlayer2.addSalvo(salvo6);
			gameplayerRepo.save(gamePlayer2);

			gamePlayer3.addShip(ship7);
			gamePlayer3.addShip(ship8);
			gamePlayer3.addShip(ship9);
			gameplayerRepo.save(gamePlayer3);

			gamePlayer4.addShip(ship10);
			gamePlayer4.addShip(ship11);
			gamePlayer4.addShip(ship12);
			gameplayerRepo.save(gamePlayer4);

		};
	}
}
