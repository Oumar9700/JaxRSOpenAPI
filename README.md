## Compte rendu de l'évolution du projet

# TP1 : Introduction à la gestion de dépendances, de versions, et de l'intégration continue.
    Réalisés:  
- Partie 1 : Utilisation de Maven
- Partie 2 : Gestion des dépendances
- Partie 3 : Spécialisation du processus de build
- Partie 5 : Utilisation de Git


    Non Réalisés :
- Partie 4 : Génération de rapports
- Partie 6 : Intégration avec l'outil Sonar
- Partie 7 : Intégration avec Jenkins
- Partie 8 : Intégration avec GitLab CI


# TP2 - 4: Introduction à la gestion de dépendances, de versions, et de l'intégration continue.
- Comprendre les mécanismes de JPA 
- Réaliser une application en utilisant JPA en se plaçant dans un cadre classique de développement sans serveur d’application au départ.
- Lien : https://github.com/Oumar9700/tpjpa2024

    
    Réalisés :
- Question 0 : Regardez rapidement le pom.xml
- Question 1 - 2: Transformez  une première classe en entité.
- Question 3 : Finir le modèle métier du projet de TP
- Question 5 : Portez votre application et gérer au minimum une relation d’héritage, les requêtes, une requête nommée.

    
    Non Réalisés :
- Question 6.  Mise en évidence du problème de n+1 select.

# TP5: Des Servlets à la notion de service Web

- Comprendre les mécanismes des Servlet
- Réaliser une application  Web en utilisant Combinant JPA et les Servlet
- Comprendre les principes d’une architecture Rest
- Comprendre les bénéfices d’un framework comme Jersey


    
- Partie 1 : Servlet -  https://github.com/Oumar9700/tpjpa2024
  
        Réalisés :
    - Question 1
    - Question 2. Insertion de ressources statiques
    - Question 3.  Création de votre première Servlet
    - Question 4. Création de votre première Servlet qui consomme les données d’un formulaire.

-       Non Réalisés : 
    - Question 5. Retour sur l’application de Gestion de RDV


- Partie 2 : JaxRS et OpenAPI - https://github.com/Oumar9700/JaxRSOpenAPI

  -     Réalisés :
        Question 6. En avant pour les architectures Rest.


-       Non Réalisés et A Faire :
  - Question 7. Créez la couche de service pour votre application. 
  - Question 8. Comprendre openAPI.


## Comment démarrer le projet Backend 
1. Cloner le projet : git clone https://github.com/Oumar9700/tpjpa2024 puis l'ouvrir sous Intellij idea ou Eclipse, Intellig Idea de preference
2. Lancer le script : ./run-hsqldb-server.sh
3. Afficher la Base de données : ./show-hsqldb.sh
4. Lancer le serveur Rest au niveau du fichier 
5. Par défaut le serveur s'ouvrira sur le port 8082, soit : http://localhost:8082
6. La branche par défaut est master et contient la version finale. si il y a probleme, la branche oumar est la plus a jour


## Comment démarrer le projet frontend 
2. Cloner le projet : git clone https://github.com/Oumar9700/concert_app/ puis l'ouvrir sous un éditeur, VsCode de preference
2. Installer les dependances : npm install
3. Lancer le serveur : npm run dev 
4. Par défaut le serveur s'ouvrira sur le port 8082, soit : http://localhost:8082
5. La branche par défaut est main et contient la version finale. si il y a probleme, la branche oumar est la plus a jour





