package com.openclassrooms.mddapi.Dtos.CommentDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CommentDto {

    /* ID du Comment */
    private int id;
    

    /* Message du Comment */
    private String message;

    /* créateur du Comment */
    private int owner_id;

    /* nom du créateur du Comment */
    private String author;

    /* post rattachée au Comment */
    private int post_id;

    /* Date de création du Comment */
    private Date created_at;

    /* Date de mise du Comment */
    private Date updated_at;


}
