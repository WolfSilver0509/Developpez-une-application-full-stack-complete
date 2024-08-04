package com.openclassrooms.mddapi.Dtos.TopicDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import com.openclassrooms.mddapi.Dtos.UserDto.UserDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;





@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {

        /* Identifiant du théme */
        private Integer id;

        /* Titre de la du Théme */
        @NotBlank(message = "Le titre du théme ne peut pas être vide")
        @NotEmpty(message = "Le titre du théme ne peut pas être vide")
        private String title;

        /* Description du Théme */
        @NotBlank(message = "La description du théme ne peut pas être vide")
        @NotEmpty(message = "La description du théme ne peut pas être vide")
        private String description;

        /* Date de création du Théme */
        private Date created_at;

        /* Date de mise du Théme */
        private Date updated_at;


        private Set<UserDto> subscribers;

        /**
         * Constructeur avec les paramètres de la classe TopicDto.
         * @param id L'identifiant du théme.
         * @param title Le titre du théme.
         * @param description La description du théme.
         * @param createdAt La date de création du théme.
         * @param updatedAt La date de mise à jour du théme.
         */
        public TopicDto(Integer id, String title, String description, Date createdAt, Date updatedAt) {
                this.id = id;
                this.title = title;
                this.description = description;
                this.created_at = createdAt;
                this.updated_at = updatedAt;
        }
}
