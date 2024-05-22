package com.openclassrooms.mddapi.Controllers;


import com.openclassrooms.mddapi.Dtos.PostDTO.PostDto;
import com.openclassrooms.mddapi.Dtos.PostDTO.PostDtoResponseMessage;
import com.openclassrooms.mddapi.Dtos.PostDTO.PostDtoGetAll;
import com.openclassrooms.mddapi.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api")
public class PostController {


        @Autowired
        private PostService postService;

        /*
         * Point de terminaison pour créer un nouveau Post.
         * Prend en entrée un DTO de création du théme et les informations de l'utilisateur.
         * Retourne une réponse contenant le DTO de la réponse de création de Post.
         */
        @PostMapping("/posts")
        public ResponseEntity<PostDtoResponseMessage> createPost(@ModelAttribute PostDto postDto, Principal principal) {
            PostDtoResponseMessage responseMessage = postService.createPost(postDto, principal);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        }

        /*
         * Point de terminaison pour récupérer tous les Post.
         * Retourne une liste de toutes les entités Topic.
         */
        @GetMapping("/posts")
        public ResponseEntity<PostDtoGetAll> getAllPosts() {
            return ResponseEntity.ok(postService.getAllPosts());
        }



}