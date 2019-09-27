package com.codeoftheweb.battleship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource
public interface PlayerRepo extends JpaRepository<Player, Long> {
     Player findByEmail(@Param("email") String email);
}
