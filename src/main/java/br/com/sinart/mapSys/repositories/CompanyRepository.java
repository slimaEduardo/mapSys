package br.com.sinart.mapSys.repositories;

import br.com.sinart.mapSys.entities.Company;
import br.com.sinart.mapSys.entities.Destiny;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("SELECT obj FROM Company obj WHERE obj.name LIKE %?1%")
    List<Company> findAllByName(String name);
}
