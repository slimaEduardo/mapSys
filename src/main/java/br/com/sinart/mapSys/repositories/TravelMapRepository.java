package br.com.sinart.mapSys.repositories;

import br.com.sinart.mapSys.entities.TravelMap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelMapRepository extends JpaRepository<TravelMap, Integer> {
}
