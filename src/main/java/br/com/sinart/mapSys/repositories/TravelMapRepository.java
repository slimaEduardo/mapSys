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
    List<TravelMap> findAllByBoardingDate(LocalDate intialBoardingDate, LocalDate finalBoardingDate);
    @Query("SELECT obj FROM TravelMap obj WHERE obj.boardingDate >= ?1 AND obj.boardingDate <= ?2 AND obj.destiny.id = ?3")
    List<TravelMap> findAllByDestinyId(LocalDate initialLocaldate,LocalDate finalLocalDate,Integer destiny);
    @Query("SELECT obj FROM TravelMap obj WHERE obj.boardingDate >= ?1 AND obj.boardingDate <= ?2 AND obj.company.id = ?3")
    List<TravelMap> findAllByCompanyId(LocalDate initialLocaldate,LocalDate finalLocalDate,Integer company);
    @Query("SELECT obj FROM TravelMap obj WHERE obj.boardingDate >= ?1 AND obj.boardingDate <= ?2 AND obj.busCategory.id = ?3")
    List<TravelMap> findAllByBusCategoryId(LocalDate initialLocaldate,LocalDate finalLocalDate,Integer category);
    @Query("SELECT obj FROM TravelMap obj WHERE obj.boardingDate >= ?1 AND obj.boardingDate <= ?2")
    List<TravelMap> findAllinMonth(LocalDate initialLocalDate, LocalDate finalLocalDate);
}
