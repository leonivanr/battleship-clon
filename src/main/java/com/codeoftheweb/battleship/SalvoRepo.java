package com.codeoftheweb.battleship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface SalvoRepo extends JpaRepository <Salvo, Long> {

}
