package br.com.sinart.mapSys.repositories;

import br.com.sinart.mapSys.entities.BusCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusCategoryRepository extends JpaRepository<BusCategory, Integer> {
}
