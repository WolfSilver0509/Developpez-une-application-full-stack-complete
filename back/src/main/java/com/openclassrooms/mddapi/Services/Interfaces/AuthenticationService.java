package com.openclassrooms.mddapi.Services.Interfaces;

import com.openclassrooms.mddapi.Dtos.LoginUserDto;
import com.openclassrooms.mddapi.Dtos.RegisterUserDto;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Repositorys.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * Service pour l'authentification des utilisateurs.
 */

public interface AuthenticationService {


    /**
     * Inscrit un nouvel utilisateur.
     *
     * @param input Les informations de l'utilisateur à inscrire.
     * @return L'utilisateur inscrit.
     */
    User signup(RegisterUserDto input);

    /**
     * Authentifie un utilisateur.
     *
     * @param input Les informations de l'utilisateur à authentifier.
     * @return L'utilisateur authentifié.
     * @throws BadCredentialsException Si les identifiants fournis sont invalides.
     */
    User authenticate(LoginUserDto input);

    /**
     * Récupère la liste de tous les utilisateurs.
     *
     * @return La liste de tous les utilisateurs.
     */
    List<User> allUsers();

}