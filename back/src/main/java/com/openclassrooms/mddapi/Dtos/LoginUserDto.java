package com.openclassrooms.mddapi.Dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * DTO (Data Transfer Object) représentant les informations de connexion d'un utilisateur.
 */
@Getter @Setter
public class LoginUserDto {

    /**
     * Nom ou email de l'utilisateur.
     */
    @Email (message = "L'email de l'utilisateur doit être valide")
    @NotEmpty(message = "L'email ne peut pas être vide")
    private String nameOrEmail;

    /**
     * Mot de passe de l'utilisateur.
     */
    @NotBlank (message = "Le mot de passe ne peut pas être vide")
    @NotEmpty(message = "Le mot de passe ne peut pas être vide")
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
