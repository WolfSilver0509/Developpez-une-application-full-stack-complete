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
@Table(name = "comments")
public class Comment {

    /* Identifiant du Commentaire  */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /* message du Commentaire  */
    @Column(length = 2000)
    private String message;

    /* Propriétaire Commentaire */
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner_id;

    /* Ratachement Post Articles */
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post_id;

    /* Date de création Commentaire */
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date created_at;

    /* Date de mise à jour Commentaire */
    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private Date updated_at;

    public Comment( String message, Post postId, User currentUser) {

        this.message = message;
        this.owner_id = currentUser;
        this.post_id = postId;

    }
}