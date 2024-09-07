package com.openclassrooms.mddapi.Services;

import com.openclassrooms.mddapi.Dtos.CommentDTO.CommentDto;
import com.openclassrooms.mddapi.Dtos.PostDTO.PostDto;
import com.openclassrooms.mddapi.Dtos.PostDTO.PostDtoResponseMessage;
import com.openclassrooms.mddapi.Models.Comment;
import com.openclassrooms.mddapi.Models.Post;
import com.openclassrooms.mddapi.Models.Topic;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Repositorys.PostRepository;
import com.openclassrooms.mddapi.Repositorys.TopicRepository;
import com.openclassrooms.mddapi.Services.Interfaces.PostService;
import com.openclassrooms.mddapi.mappers.Interfaces.PostMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service pour les Post.
 */
@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private TopicRepository topicRepository;
    private PostMapper postMapper;

    /**
     * Constructeur.
     *
     * @param postRepository  Le repository des posts.
     * @param topicRepository Le repository des topics.
     * @param postMapper      Le mapper des posts.
     */
    public PostServiceImpl(PostRepository postRepository, TopicRepository topicRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.topicRepository = topicRepository;
        this.postMapper = postMapper;
    }

    /**
     * Méthode pour récupérer tous les posts.
     * Retourne une liste de toutes les entités Post
     */
    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    /**
     * Méthode pour récupérer un post par son ID.
     * Prend en entrée l'ID du post.
     * Retourne un Optional contenant l'entité Post si elle est trouvée.
     */
    @Override
    public Optional<Post> findById(Integer id) {
        return postRepository.findById(id);
    }

    /**
     * Méthode pour enregistrer un post.
     * Prend en entrée une entité Post et la sauvegarde dans la base de données.
     * Retourne l'entité Post enregistrée.
     */
    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    /**
     * Méthode pour créer un nouveau Post.
     * Prend en entrée un DTO de création de post et les informations de l'utilisateur.
     * Retourne une réponse contenant le DTO de la réponse de création de Post.
     */
    @Override
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

/**
     * Méthode pour récupérer tous les Posts d'un utilisateur.
     * Prend en entrée les informations de l'utilisateur.
     * Retourne une liste de DTO PostDto.
     */
    @Override
    public List<PostDto> getPostsByUser(Principal principal) {
        User currentUser = (User) ((Authentication) principal).getPrincipal();
        List<Post> posts = postRepository.findAllByTopic_UserId(currentUser.getId());
        return posts.stream()
                .map(postMapper::convertToPostDto)
                .collect(Collectors.toList());
    }

    /**
     * Méthode pour récupérer tous les Posts.
     * Retourne une liste de DTO PostDtoGetAll.
     */
    @Override
    public List<CommentDto> findCommentsByPostId(Integer postId) {
        return postRepository.findById(postId)
                .map(Post::getComments)
                .orElseThrow(() -> new RuntimeException("Post not found"))
                .stream()
                .map(postMapper::convertToCommentDto)
                .collect(Collectors.toList());
    }


}
