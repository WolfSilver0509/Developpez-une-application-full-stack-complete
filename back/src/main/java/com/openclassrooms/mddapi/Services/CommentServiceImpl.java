package com.openclassrooms.mddapi.Services;

import com.openclassrooms.mddapi.Dtos.CommentDTO.CommentDto;
import com.openclassrooms.mddapi.Dtos.CommentDTO.CommentDtoResponse;
import com.openclassrooms.mddapi.Models.Comment;
import com.openclassrooms.mddapi.Models.Post;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Repositorys.CommentRepository;
import com.openclassrooms.mddapi.Repositorys.PostRepository;
import com.openclassrooms.mddapi.Services.Interfaces.CommentService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * Service pour les commentaires.
 */
@Service
public class CommentServiceImpl implements CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    /**
     * Constructeur.
     *
     * @param postRepository    Le repository des posts.
     * @param commentRepository Le repository des commentaires.
     */
    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

/**
     * Méthode pour récupérer tous les commentaires.
     * Retourne une liste de toutes les entités Comment
     */
    @Override
    public List<Comment> findAllComment() {
        return commentRepository.findAll();
    }

    /**
     * Méthode pour récupérer un commentaire par son ID.
     * Prend en entrée l'ID du commentaire.
     * Retourne un Optional contenant l'entité Comment si elle est trouvée.
     */
    @Override
    public Optional<Comment> findById(Integer id) {
        return commentRepository.findById(id);
    }

    /**
     * Méthode pour enregistrer un commentaire.
     * Prend en entrée une entité Comment et la sauvegarde dans la base de données.
     * Retourne l'entité Comment enregistrée.
     */
    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    /**
     * Méthode pour créer un nouveau Commentaire.
     * Prend en entrée un DTO de création de commentaire et les informations de l'utilisateur.
     * Retourne une réponse contenant le DTO de la réponse de création de Commentaire.
     */
    @Override
    public CommentDtoResponse createComment(CommentDto commentDto, Principal principal) {
        User currentUser = (User) ((Authentication) principal).getPrincipal();
        Optional<Post> optionalPost = postRepository.findById(commentDto.getPost_id());
        if (!optionalPost.isPresent()) {
            throw new RuntimeException("Comment not found");
        }
        Post post = optionalPost.get();
        Comment comment = new Comment(
                commentDto.getMessage(),
                post,
                currentUser
        );
        saveComment(comment);
        return new CommentDtoResponse("Comment created !");
    }

}
