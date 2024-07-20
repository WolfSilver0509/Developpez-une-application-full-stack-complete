package com.openclassrooms.mddapi.Models;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

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

    public Post(String title, String description, Topic topicId, User currentUser) {
        this.title = title;
        this.description = description;
        this.owner_id = currentUser;
        this.topic_id = topicId;

    }
}