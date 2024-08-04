package com.openclassrooms.mddapi.Controllers;


import com.openclassrooms.mddapi.Dtos.PostDTO.PostDto;
import com.openclassrooms.mddapi.Dtos.PostDTO.PostDtoResponseMessage;
import com.openclassrooms.mddapi.Services.Interfaces.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api")
public class PostController {

        private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /*
         * Point de terminaison pour créer un nouveau Post.
         * Prend en entrée un DTO de création du commentaire et les informations de l'utilisateur.
         * Retourne une réponse contenant le DTO de la réponse de création de Post.
         */
        @PostMapping("/posts")
        public ResponseEntity<PostDtoResponseMessage> createPost(@ModelAttribute PostDto postDto, Principal principal) {
            PostDtoResponseMessage responseMessage = postService.createPost(postDto, principal);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        }

        /*
         * Point de terminaison pour récupérer tous les Posts.
         * Retourne une réponse contenant une liste de DTO PostDto.
         */
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(Principal principal) {
        List<PostDto> posts = postService.getPostsByUser(principal);
        return ResponseEntity.ok(posts);
    }

    /*
     * Point de terminaison pour récupérer un Post par son ID.
     * Prend en entrée l'ID du Post.
     * Retourne une réponse contenant le DTO du Post récupéré ou une réponse null si non trouvée.
     */
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer id, Principal principal) {
        List<PostDto> userPosts = postService.getPostsByUser(principal);
        PostDto postDto = userPosts.stream()
                .filter(post -> post.getId() == id)
                .findFirst()
                .orElse(null);
        if (postDto != null) {
            return ResponseEntity.ok(postDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
