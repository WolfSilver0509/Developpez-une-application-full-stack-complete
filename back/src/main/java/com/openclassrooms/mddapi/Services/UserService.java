package com.openclassrooms.mddapi.Services;


import com.openclassrooms.mddapi.Dtos.UserDto.UserDto;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Repositorys.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service pour la gestion des utilisateurs.
 */
@Service
public class UserService {

    /**
     * Récupère l'utilisateur courant.
     * @return L'utilisateur courant.
     */
    public UserDto getCurrentUser(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
    }
}