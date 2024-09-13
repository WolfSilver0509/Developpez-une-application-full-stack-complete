package com.openclassrooms.mddapi.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * Modèle de données Thémes
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "topics")
public class Topic {
    /* Identifiant du théme */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /* Titre de la du Théme */
    private String title;

    /* Description du Théme */
    @Column(length = 2000)
    private String description;

    /* Date de création du Théme */
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date created_at;

    /* Date de mise du Théme */
    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private Date updated_at;

    @ManyToMany(mappedBy = "topics")
    private List<User> users;

    // Constructeur pour crée un Théme via postman ( Choix pour éviter d'écrire en bdd directement )
    public Topic(String title, String description) {
    this.title = title;
    this.description = description;
    }

}


