package com.tritux.gestionSalleSport.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tritux.gestionSalleSport.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByUsername(String username);
    Boolean existsByUsername(String username);
}                   
