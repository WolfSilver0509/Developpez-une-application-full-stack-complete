-- Veillez à mettre le nom de votre base de donnée crée --

USE `p6-oc-md`;

-- Création de la table "topics" si elle n'existe pas
CREATE TABLE IF NOT EXISTS topics (
                                      id INT PRIMARY KEY,
                                      title VARCHAR(255),
    description TEXT,
    created_at DATE,
    updated_at DATE
    );

-- Insertion ou mise à jour des enregistrements avec des données factices
INSERT INTO topics (id, title, description, created_at, updated_at) VALUES
                                                                        (1, 'Java Spring Boot: Building RESTful APIs',
                                                                         'Java Spring Boot est un framework populaire pour créer des API RESTful. Il permet aux développeurs de concevoir des applications backend robustes et scalables avec une configuration minimale. Cet article explore les concepts fondamentaux du développement d\'API avec Spring Boot, y compris les annotations, les contrôleurs et la gestion des exceptions.',
                                                                         CURDATE(), CURDATE()),

                                                                        (2, 'Angular: Building Dynamic Single-Page Applications',
                                                                         'Angular est un framework JavaScript maintenu par Google pour la création d\'applications web dynamiques. Il facilite le développement d\'applications Single Page (SPA) en utilisant des composants réutilisables et en offrant une gestion efficace de l\'état. Cet article détaille les meilleures pratiques pour développer des applications SPA avec Angular.',
                                                                         CURDATE(), CURDATE()),

                                                                        (3, 'Python Flask: Rapid Web Development',
                                                                         'Flask est un microframework Python léger qui permet un développement rapide d\'applications web. Bien que simple d\'utilisation, Flask est suffisamment flexible pour prendre en charge des applications web complexes. Cet article présente les fonctionnalités clés de Flask et des exemples pratiques de création d\'API REST.',
                                                                         CURDATE(), CURDATE()),

                                                                        (4, 'Node.js & Express: Backend Development Made Easy',
                                                                         'Node.js, combiné avec Express, est une solution puissante pour le développement backend. Express est un framework minimaliste pour Node.js qui facilite la gestion des requêtes HTTP et la création d\'API REST. Cet article explore comment construire un backend avec Node.js et Express, en mettant l\'accent sur la gestion des routes, des middlewares et des bases de données.',
                                                                         CURDATE(), CURDATE()),

                                                                        (5, 'React: Building Interactive User Interfaces',
                                                                         'React est une bibliothèque JavaScript populaire pour la création d\'interfaces utilisateur interactives. Grâce à son approche basée sur les composants, React permet de concevoir des applications web modernes avec une gestion efficace de l\'état et une mise à jour dynamique de l\'interface utilisateur. Cet article couvre les concepts clés de React, tels que les hooks, les états et les propriétés.',
                                                                         CURDATE(), CURDATE()),

                                                                        (6, 'Django: The High-Level Python Web Framework',
                                                                         'Django est un framework web Python de haut niveau qui permet de développer des applications web rapidement avec un minimum de code. Il s\'appuie sur une architecture MVT (Modèle-Vue-Template) et offre une sécurité intégrée, une gestion des bases de données, et des fonctionnalités prêtes à l\'emploi pour des projets web complexes. Cet article décrit comment commencer avec Django et construire des applications web robustes.',
                                                                         CURDATE(), CURDATE())
    AS new_values
ON DUPLICATE KEY UPDATE
                     title = new_values.title,
                     description = new_values.description,
                     updated_at = new_values.updated_at;

