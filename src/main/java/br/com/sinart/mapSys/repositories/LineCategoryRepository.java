package br.com.sinart.mapSys.repositories;

import br.com.sinart.mapSys.entities.LineCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineCategoryRepository extends JpaRepository<LineCategory, Integer> {
}
