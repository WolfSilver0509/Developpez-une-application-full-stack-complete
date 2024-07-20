package com.openclassrooms.mddapi.Controllers;


import com.openclassrooms.mddapi.Dtos.CommentDTO.CommentDto;
import com.openclassrooms.mddapi.Dtos.CommentDTO.CommentDtoResponse;
import com.openclassrooms.mddapi.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api")
public class CommentController {


    @Autowired
    private CommentService commentService;

    /*
     * Point de terminaison pour créer un nouveau Commentaire.
     * Prend en entrée un DTO de création du commentaire et les informations de l'utilisateur.
     * Retourne une réponse contenant le DTO de la réponse de création de commentaire.
     */
    @PostMapping("/comments")
    public ResponseEntity<CommentDtoResponse> createPost(@ModelAttribute CommentDto commentDto, Principal principal) {
        CommentDtoResponse responseMessage = commentService.createComment(commentDto, principal);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

}

