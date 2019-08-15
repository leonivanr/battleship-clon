package com.codeoftheweb.battleship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ShipRepo extends JpaRepository <Ship, Long> {
}
