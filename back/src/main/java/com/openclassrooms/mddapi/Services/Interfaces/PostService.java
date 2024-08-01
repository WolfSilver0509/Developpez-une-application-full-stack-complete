package com.openclassrooms.mddapi.Services.Interfaces;

// Classe de service responsable de la gestion des Post.
// Elle inclut des méthodes pour créer, mettre à jour, récupérer et enregistrer des Post.

import com.openclassrooms.mddapi.Dtos.PostDTO.PostDto;
import com.openclassrooms.mddapi.Dtos.PostDTO.PostDtoResponseMessage;
import com.openclassrooms.mddapi.Dtos.PostDTO.PostDtoGetAll;
import com.openclassrooms.mddapi.Models.Post;
import org.springframework.stereotype.Service;
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
     * Méthode pour récupérer toutes les Posts et les convertir en DTO.
     * Retourne une réponse contenant une liste de DTO PostDto.
     */

    PostDtoGetAll getAllPosts();

    /*
     * Méthode pour convertir une entité Post en DTO.
     * Prend en entrée une entité Post.
     * Retourne un DTO PostDto.
     */
     PostDto convertToPostDto(Post post);

    /*
     * Méthode pour récupérer le post par son ID.
     * Prend en entrée l'ID du post.
     * Retourne une réponse contenant le DTO de le post récupérée ou une réponse null si non trouvée.
     */

     PostDto getPostById(Integer id);

    /*
     * Méthode pour mettre à jour un Post existant.
     * Prend en entrée l'ID du Post à mettre à jour et le DTO contenant les nouvelles données.
     * Retourne une réponse contenant le DTO du Post mis à jour ou une réponse 404 si le Post n'est pas trouvé.
     */
    PostDtoResponseMessage updatePost(Integer id, PostDto postDto);


}


