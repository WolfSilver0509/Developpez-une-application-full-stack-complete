package com.openclassrooms.mddapi.Services;

import com.openclassrooms.mddapi.Dtos.PostDTO.PostDto;
import com.openclassrooms.mddapi.Dtos.PostDTO.PostDtoGetAll;
import com.openclassrooms.mddapi.Dtos.PostDTO.PostDtoResponseMessage;
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
    public PostDtoGetAll getAllPosts() {
        List<Post> posts = findAllPost();
        List<PostDto> postDtos = posts.stream()
                .map(this::convertToPostDto)
                .collect(Collectors.toList());
        return (new PostDtoGetAll(postDtos));
    }

    @Override
    public PostDto convertToPostDto(Post post) {
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

    @Override
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

    @Override
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
