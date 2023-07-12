package com.tritux.gestionSalleSport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tritux.gestionSalleSport.entities.Role;
import com.tritux.gestionSalleSport.entities.ERole;
import com.tritux.gestionSalleSport.entities.User;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(ERole name);    
}
