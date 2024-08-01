package com.openclassrooms.mddapi.Services.Interfaces;

import com.openclassrooms.mddapi.Dtos.CommentDTO.CommentDto;
import com.openclassrooms.mddapi.Dtos.CommentDTO.CommentDtoResponse;
import com.openclassrooms.mddapi.Models.Comment;


import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface CommentService {

    /*
     * Méthode pour récupérer tous les commentaires .
     * Retourne une liste de toutes les entités Comment
     */
     List<Comment> findAllComment();

    /*
     * Méthode pour récupérer un commentaire par son ID.
     * Prend en entrée l'ID du commentaire.
     * Retourne un Optional contenant l'entité Comment si elle est trouvée.
     */
    ; Optional<Comment> findById(Integer id);

    /*
     * Méthode pour enregistrer un commentaire.
     * Prend en entrée une entité Comment et la sauvegarde dans la base de données.
     * Retourne l'entité Comment enregistrée.
     */
     Comment saveComment(Comment comment);

    /*
     * Méthode pour créer un nouveau Commentaire.
     * Prend en entrée un DTO de création de commentaire et les informations de l'utilisateur.
     * Retourne une réponse contenant le DTO de la réponse de création de Commentaire.
     */
     CommentDtoResponse createComment(CommentDto commentDto, Principal principal);

}
