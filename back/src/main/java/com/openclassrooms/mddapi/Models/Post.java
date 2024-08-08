package com.openclassrooms.mddapi.Models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    /* Identifiant de l'articles */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /* Nom de l'articles */
    private String title;

    /* Description de l'articles  */
    @Column(length = 2000)
    private String description;

    /* Propriétaire de l'articles */
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner_id;

    /* Rattachement au Topic Thémes */
    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic_id;

    /* Date de création de l'articles */
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date created_at;

    /* Date de mise à jour de l'articles */
    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private Date updated_at;

    /* Liste des commentaires de l'articles */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    /* Constructeur avec les paramètres */
    public Post(String title, String description, Topic topicId, User currentUser) {
        this.title = title;
        this.description = description;
        this.owner_id = currentUser;
        this.topic_id = topicId;

    }
}