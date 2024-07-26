package com.openclassrooms.mddapi.Dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) représentant les informations de connexion d'un utilisateur.
 */
@Getter @Setter
public class LoginUserDto {
    private String nameOrEmail;
    private String password;



    /**
     * Renvoie une représentation textuelle de l'objet LoginUserDto.
     * @return Une chaîne représentant l'objet LoginUserDto.
     */
    @Override
    public String toString() {
        return "LoginUserDto{" +
                "nameOrEmail='" + nameOrEmail + '\'' +
                '}';
    }
}
