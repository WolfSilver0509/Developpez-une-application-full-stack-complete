package com.openclassrooms.mddapi.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.util.Date;

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

    // Constructeur pour crée un Théme via postman ( Choix pour éviter d'écrire en bdd directement )
    public Topic(String title, String description) {
    this.title = title;
    this.description = description;
    }
}


