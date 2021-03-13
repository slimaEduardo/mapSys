package br.com.sinart.mapSys.repositories;

import br.com.sinart.mapSys.entities.Destiny;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinyRepository extends JpaRepository<Destiny, Integer> {
}
