package com.openclassrooms.mddapi.Controllers;

import com.openclassrooms.mddapi.Dtos.PostDTO.PostDto;
import com.openclassrooms.mddapi.Dtos.PostDTO.PostDtoResponseMessage;
import com.openclassrooms.mddapi.Services.Interfaces.PostService;
import com.openclassrooms.mddapi.exeptions.NotFoundException;
import com.openclassrooms.mddapi.exeptions.ValidationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /*
     * Point de terminaison pour créer un nouveau Post.
     * Prend en entrée un DTO de création du Post et les informations de l'utilisateur.
     * Retourne une réponse contenant le DTO de la réponse de création de Post.
     */
    @PostMapping("/posts")
    public PostDtoResponseMessage createPost(@Valid @ModelAttribute PostDto postDto, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            throw new ValidationException("Validation errors: " + errors);
        }
        return postService.createPost(postDto, principal);
    }

    /*
     * Point de terminaison pour récupérer tous les Posts d'un utilisateur.
     * Prend en entrée les informations de l'utilisateur.
     * Retourne une liste de DTO PostDto.
     */
    @GetMapping("/posts")
    public List<PostDto> getAllPosts(Principal principal) {
        return postService.getPostsByUser(principal);
    }

    /*
     * Point de terminaison pour récupérer un Post par son ID.
     * Prend en entrée l'ID du Post.
     * Retourne un DTO PostDto.
     */
    @GetMapping("/posts/{id}")
    public PostDto getPostById(@PathVariable Integer id, Principal principal) {
        List<PostDto> userPosts = postService.getPostsByUser(principal);
        return userPosts.stream()
                .filter(post -> post.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Post not found"));
    }
}