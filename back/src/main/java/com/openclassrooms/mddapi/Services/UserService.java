package com.openclassrooms.mddapi.Services;


import com.openclassrooms.mddapi.Dtos.UserDto.UserDto;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Service pour la gestion des utilisateurs.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    /**
     * Récupère l'utilisateur courant.
     * @return L'utilisateur courant.
     */
    public UserDto getCurrentUser(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
    }

    public UserDto getUserById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return getCurrentUser(user);
        } else {
            throw new NoSuchElementException("User not found with id: " + id);
        }

    }



}