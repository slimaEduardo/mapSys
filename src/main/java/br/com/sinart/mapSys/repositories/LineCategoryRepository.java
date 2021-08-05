package br.com.sinart.mapSys.repositories;

import br.com.sinart.mapSys.entities.Destiny;
import br.com.sinart.mapSys.entities.LineCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineCategoryRepository extends JpaRepository<LineCategory, Integer> {

    @Query("SELECT obj FROM LineCategory obj WHERE obj.isActive = true")
    List<LineCategory> findAllActive();
}
