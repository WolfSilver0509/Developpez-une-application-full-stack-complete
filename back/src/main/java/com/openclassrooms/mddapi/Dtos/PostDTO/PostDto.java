package com.openclassrooms.mddapi.Dtos.PostDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openclassrooms.mddapi.Dtos.CommentDTO.CommentDto;
import com.openclassrooms.mddapi.Models.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class PostDto {

    /* ID du POST */
    private int id;

    /* Titre POST */
    @NotEmpty(message = "Title cannot be empty")
    private String title;

    /* Description du POST */
    @NotEmpty(message = "Description cannot be empty")
    private String description;

    /* créateur du POST */
    @NotNull(message = "Owner ID cannot be null")
    private int owner_id;

    /* createur de l'article du post */
//    @JsonProperty("owner_name")
    private String author;

    /* théme rattachée au POST */
    @NotNull(message = "Topic ID cannot be null")
    private int topic_id;

    /* Date de création du POST */
    private Date created_at;

    /* Date de mise du POST */
    private Date updated_at;

    /* Liste des commentaires du POST */
    @Getter
    @Setter
    private List<CommentDto> comments;

    // Constructor matching the parameters
    public PostDto(int id, String title, String description, int owner_id, String author, int topic_id, Date created_at, Date updated_at) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner_id = owner_id;
        this.author = author;
        this.topic_id = topic_id;
        this.created_at = created_at;
        this.updated_at = updated_at;

    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", owner_id=" + owner_id +
                ", author='" + author + '\'' +
                ", topic_id=" + topic_id +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", comments=" + comments +
                '}';
    }
}
