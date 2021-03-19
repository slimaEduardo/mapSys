package br.com.sinart.mapSys.repositories;

import br.com.sinart.mapSys.dto.TravelMapDTO;
import br.com.sinart.mapSys.entities.TravelMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TravelMapRepository extends JpaRepository<TravelMap, Integer> {

    @Transactional(readOnly = true)
    Page<TravelMap> findAllByBoardingDate(LocalDate boardingDate, Pageable pageable);
}
