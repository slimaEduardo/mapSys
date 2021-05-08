package br.com.sinart.mapSys.repositories;

import br.com.sinart.mapSys.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Transactional(readOnly = true)
    User findByUserNameIgnoreCase(String userName);
}
