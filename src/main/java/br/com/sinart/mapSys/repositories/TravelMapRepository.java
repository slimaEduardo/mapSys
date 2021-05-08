package br.com.sinart.mapSys.repositories;

import br.com.sinart.mapSys.entities.TravelMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TravelMapRepository extends JpaRepository<TravelMap, Integer> {

    @Query("SELECT obj FROM TravelMap obj WHERE obj.boardingDate BETWEEN ?1 AND ?2")
    List<TravelMap> findAllByBoardingDate(LocalDate intialBoardingDate, LocalDate finalBoardingDate);
    @Query("SELECT obj FROM TravelMap obj WHERE obj.boardingDate BETWEEN ?1 AND ?2 AND obj.destiny.id = ?3")
    List<TravelMap> findAllByDestinyId(LocalDate initialLocaldate,LocalDate finalLocalDate,Integer destiny);
    @Query("SELECT obj FROM TravelMap obj WHERE obj.boardingDate BETWEEN ?1 AND ?2 AND obj.company.id = ?3")
    List<TravelMap> findAllByCompanyId(LocalDate initialLocaldate,LocalDate finalLocalDate,Integer company);
    @Query("SELECT obj FROM TravelMap obj WHERE obj.boardingDate BETWEEN ?1 AND ?2 AND obj.busCategory.id = ?3")
    List<TravelMap> findAllByBusCategoryId(LocalDate initialLocaldate,LocalDate finalLocalDate,Integer category);

}
