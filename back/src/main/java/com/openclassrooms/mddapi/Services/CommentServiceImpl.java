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

@Service
public class CommentServiceImpl implements CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }


    @Override
    public List<Comment> findAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Integer id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

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
