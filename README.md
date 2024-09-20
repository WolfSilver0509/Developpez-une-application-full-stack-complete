# P6-Full-Stack-reseau-dev

![MDD](./front/src/assets/logo_p6.png)
## ✨ Introduction :

ORION souhaite créer le prochain réseau social dédié aux développeurs : MDD (Monde de Dév). Le but du réseau social MDD est d’aider les développeurs qui cherchent un travail, grâce à la mise en relation, en encourageant les liens et la collaboration entre pairs qui ont des intérêts communs. MDD pourrait devenir un vivier pour le recrutement des profils manquant des entreprises.


Avant de lancer MDD auprès d’un large public, l’entreprise veut le tester avec une version minimale déployée en interne (aussi nommé MVP : Minimum Viable Product).


Le MVP permettra aux utilisateurs de s’abonner à des sujets liés à la programmation (comme JavaScript, Java, Python, Web3, etc.). Son fil d’actualité affichera chronologiquement les articles correspondants. L’utilisateur pourra également écrire des articles et poster des commentaires.

## 🛠️ Prérequis

👉 **MySQL** : Assurez-vous d'avoir MySQL installé et en cours d'exécution.

👉 **Java** : Vous aurez besoin de Java 11 ou supérieur.

👉 **Maven** : Maven est requis pour gérer les dépendances du projet.

#### 🚀🚀 Installation des dépendances: 🚀🚀

Clonez le dépôt du projet dans votre répertoire de travail local.


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

server.port=<Numero_de_port_voulu>

security.jwt.secret-key=<votre_token_secret>
# 24h in millisecond pour tester à re adapter ne fonction de !
security.jwt.expiration-time=86400000
```

## 🏃‍♂️ Lancement de l'application Spring Boot ( BACK )

👉  Assurez-vous que votre base de données MySQL est en cours d'exécution.

👉  Lancez votre application Spring Boot. Cette action créera automatiquement les tables nécessaires dans la base de données p6_openclassroom_fullstack.
```
mvn spring-boot:run
```

## 📂 Remplissage automatique des thèmes

👉 Un fichier SQL nommé generateTopic.sql est disponible dans le dossier src/main/resources. Ce fichier contient un script pour remplir automatiquement les thèmes dans la table appropriée.

👉 Pour exécuter ce fichier SQL, connectez-vous à votre base de données MySQL et exécutez le script dans votre console mysql sur la table crée.

### 👉🏻 Documentation de l'API:

La documentation de l'API est disponible via Swagger à l'adresse suivante :

```
http://localhost:5656/swagger-ui/index.html

```

## 🏃‍♂️ Lancement de l'application Angular ( FRONT )

#### Prérequis:

Assurez-vous d'avoir Node.js et npm installés sur votre système. Vous pouvez les télécharger et les installer depuis leurs sites web officiels :

- Node.js: https://nodejs.org/en/download/
- npm: https://www.npmjs.com/

Exécutez la commande suivante pour installer les dépendances du projet :
```
npm install 
```

#### Exécution du projet:

Pour lancer le serveur de développement et exécuter l'application, entrez la commande suivante dans le terminal :
```
ng serve
```

Accédez à l'application dans votre navigateur web à l'adresse suivante :
```
http://localhost:4200/
```



## Documentation Supplémentaire 
<details>
  <summary>🚀 Explorer l'API avec Postman</summary>
  <a href="./front/ressources/MDD-P6-FS.postman_collection.json">
   Vous pouvez importez les différents API endpoints pour tester l'application avec postman.
  </a>
</details>

<details>
  <summary>🔗Lien du repository original GitHub OC </summary>
  <a href="https://github.com/OpenClassrooms-Student-Center/Developpez-une-application-full-stack-complete" target="_blank">
    Lien du Back et Front.
  </a>
</details>
