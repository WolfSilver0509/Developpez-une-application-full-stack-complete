package com.openclassrooms.mddapi.Controllers;


import com.openclassrooms.mddapi.Models.User;
//import com.openclassrooms.mddapi.Services.UserService;
import com.openclassrooms.mddapi.Dtos.LoginUserDto;
import com.openclassrooms.mddapi.Dtos.RegisterUserDto;
import com.openclassrooms.mddapi.Responses.LoginResponse;
import com.openclassrooms.mddapi.Services.AuthenticationService;
import com.openclassrooms.mddapi.Services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur pour les opérations d'authentification des utilisateurs.
 */
@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    /**
     * Endpoint pour l'inscription d'un nouvel utilisateur.
     * @param registerUserDto Les informations d'inscription de l'utilisateur.
     * @return La réponse HTTP contenant les informations de l'utilisateur inscrit.
     */
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    /**
     * Endpoint pour l'authentification d'un utilisateur existant.
     * @param loginUserDto Les informations de connexion de l'utilisateur.
     * @return La réponse HTTP contenant le token JWT d'authentification.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
