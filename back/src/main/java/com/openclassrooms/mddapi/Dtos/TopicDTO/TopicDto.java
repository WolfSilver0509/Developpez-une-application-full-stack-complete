package com.openclassrooms.mddapi.Dtos.TopicDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import com.openclassrooms.mddapi.Dtos.UserDto.UserDto;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * Classe représentant le DTO de Topic
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {
        /* Identifiant du théme */
        private Integer id;

        /* Titre de la du Théme */
        private String title;

        /* Description du Théme */
        private String description;

        /* Date de création du Théme */
        private Date created_at;

        /* Date de mise du Théme */
        private Date updated_at;

        @NotEmpty
        private Set<UserDto> subscribers;

        public TopicDto(Integer id, String title, String description, Date createdAt, Date updatedAt) {
                this.id = id;
                this.title = title;
                this.description = description;
                this.created_at = createdAt;
                this.updated_at = updatedAt;
        }
}
