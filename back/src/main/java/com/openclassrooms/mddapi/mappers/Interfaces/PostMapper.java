package com.openclassrooms.mddapi.mappers.Interfaces;

// Classe de service responsable de la gestion des Post.
// Elle inclut des méthodes pour créer, mettre à jour, récupérer et enregistrer des Post.

import com.openclassrooms.mddapi.Dtos.CommentDTO.CommentDto;
import com.openclassrooms.mddapi.Dtos.PostDTO.PostDto;
import com.openclassrooms.mddapi.Models.Comment;
import com.openclassrooms.mddapi.Models.Post;


public interface PostMapper {


    /*
     * Méthode pour convertir une entité Post en DTO.
     * Prend en entrée une entité Post.
     * Retourne un DTO PostDto.
     */
    PostDto convertToPostDto(Post post);


    CommentDto convertToCommentDto(Comment comment);


}