package com.openclassrooms.mddapi.Services;

// Classe de service responsable de la gestion des Post.
// Elle inclut des méthodes pour créer, mettre à jour, récupérer et enregistrer des Post.

import com.openclassrooms.mddapi.Dtos.PostDTO.PostDto;
import com.openclassrooms.mddapi.Dtos.PostDTO.PostDtoResponseMessage;
import com.openclassrooms.mddapi.Dtos.PostDTO.PostDtoGetAll;
import com.openclassrooms.mddapi.Models.Post;
import com.openclassrooms.mddapi.Models.Topic;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Repositorys.PostRepository;
import com.openclassrooms.mddapi.Repositorys.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Value("${app.base-url}")
    private String baseUrl;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TopicRepository topicRepository;

    /*
     * Méthode pour récupérer tous les thémes.
     * Retourne une liste de toutes les entités Post.
     */
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    /*
     * Méthode pour récupérer un post par son ID.
     * Prend en entrée l'ID du théme.
     * Retourne un Optional contenant l'entité Post si elle est trouvée.
     */
    public Optional<Post> findById(Integer id) {
        return postRepository.findById(id);
    }

    /*
     * Méthode pour enregistrer un théme.
     * Prend en entrée une entité Post et la sauvegarde dans la base de données.
     * Retourne l'entité Post enregistrée.
     */
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    /*
     * Méthode pour créer un nouveau Post.
     * Prend en entrée un DTO de création de théme et les informations de l'utilisateur.
     * Retourne une réponse contenant le DTO de la réponse de création de Post.
     */
    public PostDtoResponseMessage createPost(PostDto postDto, Principal principal) {
        User currentUser = (User) ((Authentication) principal).getPrincipal();
        Optional<Topic> optionalTopic = topicRepository.findById(postDto.getTopic_id());
        if (!optionalTopic.isPresent()) {
            throw new RuntimeException("Post not found");
        }
        Topic topic = optionalTopic.get();
        Post post = new Post(
                postDto.getTitle(),
                postDto.getDescription(),
                topic,
                currentUser
        );
        savePost(post);
        return new PostDtoResponseMessage("Post created !");
    }

    /*
     * Méthode pour récupérer toutes les Posts et les convertir en DTO.
     * Retourne une réponse contenant une liste de DTO PostDto.
     */

    public PostDtoGetAll getAllPosts(){
        List<Post> posts = findAllPost();
        List<PostDto> postDtos = posts.stream()
                .map(this::convertToPostDto)
                .collect(Collectors.toList());
        return (new PostDtoGetAll(postDtos));
    }

    /*
     * Méthode pour convertir une entité Post en DTO.
     * Prend en entrée une entité Post.
     * Retourne un DTO PostDto.
     */
    private PostDto convertToPostDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getDescription(),
                post.getOwner_id().getId(),
                post.getTopic_id().getId(),
                post.getCreated_at(),
                post.getUpdated_at()
        );
    }

    /*
     * Méthode pour récupérer le post par son ID.
     * Prend en entrée l'ID du post.
     * Retourne une réponse contenant le DTO de le post récupérée ou une réponse null si non trouvée.
     */

    public PostDto getPostById(Integer id) {
        Optional<Post> postOptional = findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            PostDto postDto = convertToPostDto(post);
            return postDto;
        } else {
            return null;
        }
    }
    /*
     * Méthode pour mettre à jour un Post existant.
     * Prend en entrée l'ID du Post à mettre à jour et le DTO contenant les nouvelles données.
     * Retourne une réponse contenant le DTO du Post mis à jour ou une réponse 404 si le Post n'est pas trouvé.
     */
    public PostDtoResponseMessage updatePost(Integer id, PostDto postDto) {
        Optional<Post> optionalPost = findById(id);
        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            existingPost.setTitle(postDto.getTitle());
            if (postDto.getDescription() != null && !postDto.getDescription().isEmpty()) {
                existingPost.setDescription(postDto.getDescription());
            }
            savePost(existingPost);
            return new PostDtoResponseMessage("Post updated!");
        } else {
            throw new RuntimeException("Post not found");
        }
    }



}

