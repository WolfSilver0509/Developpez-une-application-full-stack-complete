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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private TopicRepository topicRepository;

    public PostServiceImpl(PostRepository postRepository, TopicRepository topicRepository) {
        this.postRepository = postRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Integer id) {
        return postRepository.findById(id);
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

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


    @Override
    public PostDto convertToPostDto(Post post) {
        PostDto postDto = new PostDto(
                post.getId(),
                post.getTitle(),
                post.getDescription(),
                post.getOwner_id().getId(),
                post.getTopic_id().getId(),
                post.getCreated_at(),
                post.getUpdated_at()
        );
        postDto.setComments(post.getComments().stream()
                .map(this::convertToCommentDto)
                .collect(Collectors.toList()));
        return postDto;
    }

    @Override
    public List<PostDto> getPostsByUser(Principal principal) {
        User currentUser = (User) ((Authentication) principal).getPrincipal();
        List<Post> posts = postRepository.findAllByTopic_UserId(currentUser.getId());
        return posts.stream()
                .map(this::convertToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> findCommentsByPostId(Integer postId) {
        return postRepository.findById(postId)
                .map(Post::getComments)
                .orElseThrow(() -> new RuntimeException("Post not found"))
                .stream()
                .map(this::convertToCommentDto)
                .collect(Collectors.toList());
    }

    private CommentDto convertToCommentDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setMessage(comment.getMessage());
        dto.setCreated_at(comment.getCreated_at());
        dto.setUpdated_at(comment.getUpdated_at());
        dto.setOwner_id(comment.getOwner_id().getId());
        dto.setPost_id(comment.getPost().getId());
        return dto;
    }

}
