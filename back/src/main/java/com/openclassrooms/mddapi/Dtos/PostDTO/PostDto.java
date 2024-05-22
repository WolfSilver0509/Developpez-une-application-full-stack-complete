package com.openclassrooms.mddapi.Dtos.PostDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class PostDto {

    /* ID du POST */
    private int id;

    /* Titre POST */
    private String title;

    /* Description du POST */
    private String description;

    /* créateur du POST */
    private int owner_id;

    /* théme rattachée au POST */
    private int topic_id;

    /* Date de création du POST */
    private Date created_at;

    /* Date de mise du POST */
    private Date updated_at;

//    public PostDto(Integer id, String title, String description, int ownerId, int topicId, Date created_at, Date updated_at) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.owner_id = ownerId;
//        this.topic_id = topicId;
//        this.created_at = created_at;
//        this.updated_at = updated_at;
//    }

//    public PostDto(Integer id, String title, String description, Integer owner_id, Integer topic_id, Date createdAt, Date updatedAt) {
//    }
}
