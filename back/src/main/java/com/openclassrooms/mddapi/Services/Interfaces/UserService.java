package com.openclassrooms.mddapi.Services.Interfaces;


import com.openclassrooms.mddapi.Dtos.UserDto.UserDto;
import com.openclassrooms.mddapi.Dtos.UserDto.UserUpdateDto;
import com.openclassrooms.mddapi.Models.User;

import java.util.Optional;


public interface UserService {


    /**
     * Récupérer le Use via l'ID findbyID
     * @param id
     * @return
     */
     Optional<User> findById(Integer id);

    /**
     * Récupère l'utilisateur courant.
     * @return L'utilisateur courant.
     */
     UserDto getCurrentUser(User user);

    /**
     * Récupérer le user par l'ID
     * @param id
     * @return
     */
     UserDto getUserById(Integer id);

    /**
     * Mise à jour du User  par l'Id et le DTO
     * @param id
     * @param updateDto
     * @return
     */
     UserDto updateUser(Integer id, UserUpdateDto updateDto);

}