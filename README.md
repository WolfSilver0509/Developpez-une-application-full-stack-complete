# P6-Full-Stack-reseau-dev

![MDD](./front/src/assets/png/mdd-logo.png)


## 🛠️ Prérequis

👉 **MySQL** : Assurez-vous d'avoir MySQL installé et en cours d'exécution.

👉 **Java** : Vous aurez besoin de Java 11 ou supérieur.

👉 **Maven** : Maven est requis pour gérer les dépendances du projet.

## 🚀 Configuration de la base de données

👉 Créez une base de données MySQL appelée `p6_openclassroom_fullstack`.

```sql
CREATE DATABASE p6_openclassroom_fullstack;
```

👉 Assurez-vous que les paramètres de connexion à la base de données sont correctement configurés dans un fichier database.properties ( qui se crée au même endroit que application.properties) qui sera désservie dans application.properties de notre projet Spring Boot.

databse.properties :
```
spring.datasource.url=jdbc:mysql://localhost:3306/p6_openclassroom_fullstack
spring.datasource.username=<votre_nom_utilisateur>
spring.datasource.password=<votre_mot_de_passe>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```

## 🏃‍♂️ Lancement de l'application Spring Boot

👉  Assurez-vous que votre base de données MySQL est en cours d'exécution.

👉  Lancez votre application Spring Boot. Cette action créera automatiquement les tables nécessaires dans la base de données p6_openclassroom_fullstack.
```
mvn spring-boot:run
```

## 📂 Remplissage automatique des thèmes

👉 Un fichier SQL nommé generateTopic.sql est disponible dans le dossier src/main/resources. Ce fichier contient un script pour remplir automatiquement les thèmes dans la table appropriée.

👉 Pour exécuter ce fichier SQL, connectez-vous à votre base de données MySQL et exécutez le script dans votre console mysql sur la table crée.


## Documentation Supplémentaire 
<details>
  <summary>🚀 Explorer l'API avec Postman</summary>
  <a href="./front/resources/p6.postman_collection.json">
   Vous pouvez importez les différents API endpoints pour tester l'application avec postman.
  </a>
</details>

<details>
  <summary>🗺️ Draw.io schema de relation </summary>
  <a href="./back/p6-table-relationships.drawio">
    Check the draw.io schema for the table relationships !
  </a>
</details>

<details>
  <summary>🔗Lien du repository original GitHub OC </summary>
  <a href="https://github.com/OpenClassrooms-Student-Center/Developpez-une-application-full-stack-complete" target="_blank">
    Lien du Back et Front.
  </a>
</details>
