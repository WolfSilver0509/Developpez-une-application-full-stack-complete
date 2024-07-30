package com.openclassrooms.mddapi.Dtos.TopicDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicDtoCreate {
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
}

