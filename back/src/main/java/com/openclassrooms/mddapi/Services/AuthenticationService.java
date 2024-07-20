package com.openclassrooms.mddapi.Services;

import com.openclassrooms.mddapi.Dtos.LoginUserDto;
import com.openclassrooms.mddapi.Dtos.RegisterUserDto;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Repositorys.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service pour l'authentification des utilisateurs.
 */
@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Inscrit un nouvel utilisateur.
     * @param input Les informations de l'utilisateur à inscrire.
     * @return L'utilisateur inscrit.
     */
    public User signup(RegisterUserDto input) {
        var user = new User()
                .setName(input.getName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    /**
     * Authentifie un utilisateur.
     * @param input Les informations de l'utilisateur à authentifier.
     * @return L'utilisateur authentifié.
     * @throws BadCredentialsException Si les identifiants fournis sont invalides.
     */
    public User authenticate(LoginUserDto input) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getEmail(),
                            input.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email or password");
        }

        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }

    /**
     * Récupère la liste de tous les utilisateurs.
     * @return La liste de tous les utilisateurs.
     */
    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}
