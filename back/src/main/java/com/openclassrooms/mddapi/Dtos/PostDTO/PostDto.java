package com.openclassrooms.mddapi.Dtos.PostDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class PostDto {

    /* ID du POST */
    private int id;

    /* Titre POST */
    @NotEmpty(message = "Title cannot be empty")
    private String title;

    /* Description du POST */
    @NotEmpty(message = "Description cannot be empty")
    private String description;

    /* créateur du POST */
    @NotNull(message = "Owner ID cannot be null")
    private int owner_id;

    /* théme rattachée au POST */
    @NotNull(message = "Topic ID cannot be null")
    private int topic_id;

    /* Date de création du POST */
    private Date created_at;

    /* Date de mise du POST */
    private Date updated_at;

}
