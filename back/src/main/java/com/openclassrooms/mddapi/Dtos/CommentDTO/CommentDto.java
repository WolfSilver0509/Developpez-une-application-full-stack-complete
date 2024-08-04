package com.openclassrooms.mddapi.Dtos.CommentDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CommentDto {

    /* ID du Comment */
    private int id;
    

    /* Message du Comment */
    @NotBlank (message = "Le message du Comment ne peut pas être vide")
    @NotEmpty(message = "Le message du Comment ne peut pas être vide")
    private String message;

    /* créateur du Comment */
    private int owner_id;

    /* post rattachée au Comment */
    private int post_id;

    /* Date de création du Comment */
    private Date created_at;

    /* Date de mise du Comment */
    private Date updated_at;


}
