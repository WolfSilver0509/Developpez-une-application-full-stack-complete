package com.openclassrooms.mddapi.Dtos.UserDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * UserUpdateDto est une classe qui permet de représenter un utilisateur
 * Elle contient les attributs suivants :
 * - name : le nom de l'utilisateur
 * - email : l'email de l'utilisateur
 * - password : le mot de passe de l'utilisateur
 * Cette classe contient des constructeurs, des getters et des setters pour accéder et modifier les attributs
 */
@Getter@Setter
@AllArgsConstructor @NoArgsConstructor
public class UserUpdateDto {
    private String name;
    private String email;
    private String password;

}
