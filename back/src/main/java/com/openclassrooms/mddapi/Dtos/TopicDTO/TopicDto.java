package com.openclassrooms.mddapi.Dtos.TopicDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import com.openclassrooms.mddapi.Dtos.UserDto.UserDto;
import javax.validation.constraints.NotEmpty;
import java.util.Set;





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
}
