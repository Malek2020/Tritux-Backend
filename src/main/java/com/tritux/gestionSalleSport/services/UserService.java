    package com.tritux.gestionSalleSport.services;
import com.tritux.gestionSalleSport.entities.User;
import com.tritux.gestionSalleSport.entities.Role;
import java.util.List;

public interface UserService {

    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    List<User> getUsers();
}


