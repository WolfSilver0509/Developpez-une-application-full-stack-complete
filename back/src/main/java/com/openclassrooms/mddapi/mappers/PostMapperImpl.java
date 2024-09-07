package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.Dtos.CommentDTO.CommentDto;
import com.openclassrooms.mddapi.Dtos.PostDTO.PostDto;
import com.openclassrooms.mddapi.Models.Comment;
import com.openclassrooms.mddapi.Models.Post;
import com.openclassrooms.mddapi.mappers.Interfaces.PostMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PostMapperImpl implements PostMapper {


    @Override
    public PostDto convertToPostDto(Post post) {
        PostDto postDto = new PostDto(
                post.getId(),
                post.getTitle(),
                post.getDescription(),
                post.getOwner_id().getId(),
                post.getOwner_id().getName(),
                post.getTopic_id().getId(),
                post.getTopic_id().getTitle(),
                post.getCreated_at(),
                post.getUpdated_at()
        );
        postDto.setComments(post.getComments().stream()
                .map(this::convertToCommentDto)
                .collect(Collectors.toList()));
        return postDto;
    }


    @Override
    public CommentDto convertToCommentDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setMessage(comment.getMessage());
        dto.setCreated_at(comment.getCreated_at());
        dto.setUpdated_at(comment.getUpdated_at());
        dto.setOwner_id(comment.getOwner_id().getId());
        dto.setAuthor(comment.getOwner_id().getName());
        dto.setPost_id(comment.getPost().getId());
        return dto;
    }

}
