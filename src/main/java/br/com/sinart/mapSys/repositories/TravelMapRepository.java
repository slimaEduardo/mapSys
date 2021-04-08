package br.com.sinart.mapSys.repositories;

import br.com.sinart.mapSys.dto.TravelMapDTO;
import br.com.sinart.mapSys.entities.TravelMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Repository
public interface TravelMapRepository extends JpaRepository<TravelMap, Integer> {

    @Query("SELECT obj FROM TravelMap obj WHERE obj.boardingDate >= ?1 AND obj.boardingDate <= ?2")
    Page<TravelMap> findAllByBoardingDate(LocalDate intialBoardingDate, LocalDate finalBoardingDate, Pageable pageable);
    @Transactional(readOnly = true)
    Page<TravelMap> findAllByDestinyId(Integer destiny, Pageable pageable);
    @Transactional(readOnly = true)
    Page<TravelMap> findAllByCompanyId(Integer company, Pageable pageable);
    @Transactional(readOnly = true)
    Page<TravelMap> findAllByBusCategoryId(Integer category, Pageable pageable);
    @Query("SELECT obj FROM TravelMap obj WHERE obj.boardingDate >= ?1 AND obj.boardingDate <= ?2")
    List<TravelMap> findAllinMonth(LocalDate initialLocalDate, LocalDate finalLocalDate);
}
