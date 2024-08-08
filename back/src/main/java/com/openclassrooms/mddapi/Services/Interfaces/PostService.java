package com.openclassrooms.mddapi.Services.Interfaces;

// Classe de service responsable de la gestion des Post.
// Elle inclut des méthodes pour créer, mettre à jour, récupérer et enregistrer des Post.

import com.openclassrooms.mddapi.Dtos.CommentDTO.CommentDto;
import com.openclassrooms.mddapi.Dtos.PostDTO.PostDto;
import com.openclassrooms.mddapi.Dtos.PostDTO.PostDtoResponseMessage;

import com.openclassrooms.mddapi.Models.Post;
import java.security.Principal;
import java.util.List;
import java.util.Optional;



public interface PostService {

    /*
     * Méthode pour récupérer tous les thémes.
     * Retourne une liste de toutes les entités Post.
     */
    List<Post> findAllPost();

    /*
     * Méthode pour récupérer un post par son ID.
     * Prend en entrée l'ID du théme.
     * Retourne un Optional contenant l'entité Post si elle est trouvée.
     */
     Optional<Post> findById(Integer id);

    /*
     * Méthode pour enregistrer un théme.
     * Prend en entrée une entité Post et la sauvegarde dans la base de données.
     * Retourne l'entité Post enregistrée.
     */
     Post savePost(Post post);

    /*
     * Méthode pour créer un nouveau Post.
     * Prend en entrée un DTO de création de théme et les informations de l'utilisateur.
     * Retourne une réponse contenant le DTO de la réponse de création de Post.
     */
    PostDtoResponseMessage createPost(PostDto postDto, Principal principal);


/*
     * Méthode pour récupérer tous les Posts d'un utilisateur.
     * Prend en entrée les informations de l'utilisateur.
     * Retourne une liste de DTO PostDto.
     */
    List<PostDto> getPostsByUser(Principal principal);

    /*
     * Méthode pour récupérer tous les Posts.
     * Retourne une liste de DTO PostDtoGetAll.
     */
    List<CommentDto> findCommentsByPostId(Integer postId);
}


