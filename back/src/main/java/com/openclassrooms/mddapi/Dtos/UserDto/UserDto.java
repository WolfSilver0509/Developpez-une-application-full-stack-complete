package com.openclassrooms.mddapi.Dtos.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserDto {

    /* ID de l'utilisateur */
    private Integer id;

    /* Nom de l'utilisateur */
    @NotBlank (message = "Le nom de l'utilisateur ne peut pas être vide")
    @NotNull(message = "Le nom de l'utilisateur ne peut pas être vide")
    private String name;

    /* Email de l'utilisateur */
    @Email(message = "L'email de l'utilisateur doit être valide")
    @NotEmpty(message = "L'email de l'utilisateur ne peut pas être vide")
    private String email;

    /* Date de création de l'utilisateur */
    private Date createdAt;

    /* Date de mise à jour de l'utilisateur */
    private Date updatedAt;

    /* Token JWT */
    private String jwtToken;

    public <R> UserDto(Integer id, String name, String email, Date createdAt, Date updatedAt, R collect) {
    }


    /**
     * Constructeur avec les paramètres de la classe UserDto.
     * @param id L'identifiant de l'utilisateur.
     * @param name Le nom de l'utilisateur.
     * @param email L'email de l'utilisateur.
     * @param createdAt La date de création de l'utilisateur.
     * @param updatedAt La date de mise à jour de l'utilisateur.
     */
    public UserDto(Integer id, String name, String email, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
