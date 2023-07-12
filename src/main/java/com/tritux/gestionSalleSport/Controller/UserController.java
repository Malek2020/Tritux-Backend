package com.tritux.gestionSalleSport.Controller;
import java.net.http.HttpHeaders;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tritux.gestionSalleSport.entities.ERole;
import com.tritux.gestionSalleSport.payload.request.*;
import com.tritux.gestionSalleSport.entities.Role;
import com.tritux.gestionSalleSport.entities.User;
import com.tritux.gestionSalleSport.entities.UserInfoResponse;
import com.tritux.gestionSalleSport.repositories.RoleRepository;
import com.tritux.gestionSalleSport.repositories.UserRepository;
import com.tritux.gestionSalleSport.security.JwtUtils;
import com.tritux.gestionSalleSport.services.UserDetailsImpl;
//import com.tritux.gestionSalleSport.config.request.AuthenticationRequest;
//import com.tritux.gestionSalleSport.config.response.JWTTokenResponse;
import com.tritux.gestionSalleSport.services.UserService;

import antlr.collections.List;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600,allowCredentials = "true")
@RestController
@RequestMapping("/user")
public class UserController {

    //private final UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

  @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        log.info("logged in");

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getLastName(),
                        userDetails.getEmail(),
                        roles));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_COACH)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
    log.info("aa");
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        log.info("logged out");
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

    // @Autowired
    // public UserController(UserService userService) {
    //     this.userService = userService;
    // }

    // // Définir les endpoints pour les opérations CRUD sur les utilisateurs
    // // ...
    // @PostMapping("/login")
    // public ResponseEntity<JWTTokenResponse> createCustomer(@RequestBody AuthenticationRequest request) {
    //     return new ResponseEntity<>(userService.generateJWTToken(request.getUsername(), request.getPassword()), HttpStatus.OK);
    // }

    // @ExceptionHandler(EntityNotFoundException.class)
    // public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
    //     return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    // }



}



