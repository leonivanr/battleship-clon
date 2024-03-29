package com.codeoftheweb.battleship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

	@Bean
	public CommandLineRunner initData (PlayerRepo plyRepo,GameRepo gameRepos,GamePlayerRepo gameplayerRepo,ShipRepo shipRepo,SalvoRepo salvoRepo,ScoreRepo scoreRepo) {
		return(args) -> {
			Player player1 = new Player("bauer", passwordEncoder().encode("bauer"));
			Player player2 = new Player("brian",passwordEncoder().encode("brian"));
			Player player3 = new Player("kim",passwordEncoder().encode("kim"));
			Player player4 = new Player("almeida",passwordEncoder().encode("almeida"));

			plyRepo.save(player1);
			plyRepo.save(player2);
			plyRepo.save(player3);
			plyRepo.save(player4);

			Game game1 = new Game ();
			Game game2 = new Game ();

			gameRepos.save(game1);
			gameRepos.save(game2);

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

			// Instancias de Score
			Score score1 = new Score(player1,game1,1,new Date());
			Score score2 = new Score(player1,game1,0.5,new Date());
			Score score3 = new Score(player1,game2,0,new Date());
			Score score4 = new Score(player2,game1,1,new Date());
			Score score7 = new Score(player3,game2,1,new Date());

			scoreRepo.save(score1);
			scoreRepo.save(score2);
			scoreRepo.save(score3);
			scoreRepo.save(score4);
			scoreRepo.save(score7);


		};
	}
}

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    PlayerRepo playerRepo;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputName-> {
            Player player = playerRepo.findByEmail(inputName);
            if (player != null) {
                return new User(player.getEmail(), player.getPassword(),
                        AuthorityUtils.createAuthorityList("USER"));
            } else {
                throw new UsernameNotFoundException("Unknown user: " + inputName);
            }
        });
    }
}

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
				.antMatchers("/web/games.html").permitAll()
                .antMatchers("/web/**").permitAll()
				.antMatchers("/api/games").permitAll()
				.antMatchers("/api/players").permitAll()
				.antMatchers("/api/game_view/*").hasAuthority("USER")
				.antMatchers("/rest").denyAll()
				.anyRequest().permitAll();
        http.formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/api/login");

        http.logout().logoutUrl("/api/logout");

        // turn off checking for CSRF tokens
        http.csrf().disable();

        // if user is not authenticated, just send an authentication failure response
        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if login is successful, just clear the flags asking for authentication
        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        // if login fails, just send an authentication failure response
        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if logout is successful, just send a success response
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
    }
    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}