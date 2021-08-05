package br.com.sinart.mapSys.repositories;

import br.com.sinart.mapSys.entities.Destiny;

import br.com.sinart.mapSys.entities.TravelMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface DestinyRepository extends JpaRepository<Destiny, Integer> {


    @Query("SELECT obj FROM Destiny obj WHERE obj.name LIKE %?1% AND obj.isActive = true ")
    List<Destiny> findAllByName(String name);
}
