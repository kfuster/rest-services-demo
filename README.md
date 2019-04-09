# Formation Spring Boot / Hibernate / services REST

# Table des matières
 
 * [Spring Boot](#spring-boot)
 * [H2](#h2)
 * [Hibernate](#hibernate)
 * [REST](#rest)
 * [Hands On](#hands-on)
 * [Sources](#sources)

# Spring Boot

Spring Boot est un micro framework qui a pour but de faciliter la configuration d’un projet Spring et de réduire le temps alloué au démarrage d’un projet. 

Spring Boot se base sur plusieurs éléments :

* Un site web (https://start.spring.io/) qui vous permet de générer rapidement la structure de votre projet en y incluant toutes les dépendances Maven nécessaires à votre application.

* L’utilisation de « Starters » pour gérer les dépendances. Spring a regroupé les dépendances Maven de Spring dans des « méga dépendances » afin de faciliter la gestion de celles-ci. Par exemple si vous voulez ajouter toutes les dépendances pour gérer la sécurité il suffit d’ajouter le starter « spring-boot-starter-security ».

### Comment ça marche ?

Spring boot commence par un système d'auto-configuration, qui applique une configuration par défaut au démarrage de votre application pour toutes dépendances présentes dans celle-ci. Cette configuration s’active à partir du moment où vous avez annoté votre application avec **« @EnableAutoConfiguration »** ou **« @SpringBootApplication »**. Bien entendu cette configuration peut-être surchargée via des propriétés Spring prédéfinie ou via une configuration Java. L’auto-configuration simplifie la configuration sans pour autant vous restreindre dans les fonctionnalités de Spring. Par exemple, si vous utilisez le starter « spring-boot-starter-security », Spring Boot vous configurera la sécurité dans votre application avec notamment un utilisateur par défaut et un mot de passe généré aléatoirement au démarrage de votre application.

En plus de ces premiers éléments qui facilitent la configuration d’un projet, Spring Boot offre d’autres avantages notamment en termes de déploiement applicatif. Habituellement, le déploiement d’une application Spring nécessite la génération d’un fichier .war qui doit être déployé  sur un serveur comme un Apache Tomcat. Spring Boot simplifie ce mécanisme en offrant la possibilité d’**intégrer directement  un serveur Tomcat** dans votre exécutable. Au lancement de celui-ci, un Tomcat embarqué sera démarré afin de faire tourner votre application.

Enfin, Spring Boot met à disposition des opérationnels, des métriques qu’ils peuvent suivre une fois l’application déployée en production. Pour cela Spring Boot utilise **« Actuator »** qui est un système qui permet de monitorer une application via des URLs spécifiques ou des commandes disponibles via SSH. Sachez, qu’il est possible de définir vos propres indicateurs très facilement.

Voici une liste non exhaustive des indicateurs disponibles par défaut :

* **metrics**: métriques de l’application (CPU, mémoire, …)
* **beans**: liste des BEANs Spring
* **trace**: liste des requêtes HTTP envoyées à l’application
* **dump**: liste des threads en cours
* **health**: état de santé de l’application
* **env**: liste des profils, des propriétés et des variables d’environnement

# H2

H2 est une **base de données relationnelle** écrite en **Java**.

Cette base de données ultra-légère n'est pas conçue pour supporter de gros volumes de données ni un nombre important d'utilisateurs, mais elle est extrêmement pratique pour les développeurs Java qui ont besoin de tester des applications ou des composants nécessitants des requêtes SQL via JDBC.

H2 présente de nombreux avantages :

* **légèreté**: elle tient dans un fichier "zip" de 7 mo (y compris les sources et la documentation) 
* **portabilité**: elle est constituée d'un seul fichier "jar" qui peut être exécuté sur tout système d'exploitation disposant de Java
* **fonctionnalités**:  H2 dispose des mêmes possibilités que les "grandes bases de données" (schémas multiples, contrôle d'intégrité référentielle, triggers, fonctions, procédures stockés, colonnes auto-incrémentées, séquences, etc)
* **différents modes de fonctionnement**: client/serveur ou embarqué

H2 permet de stocker les données d'une base soit en **mémoire**, soit sur **disque**.
L'emplacement est déterminé lors de la connexion par la dernière partie de l'URL JDBC:

![H2 load types](http://2.bp.blogspot.com/-Lu2ED6Dz-H4/VDb8OcEb0fI/AAAAAAAAAjE/kR1A2iRwYCU/s1600/h2-img1-data.jpg)

La dépendance H2 est la suivante :

```
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

# Hibernate
#### Qu’est ce que c’est ?

Hibernate est une solution open source de type ORM (Object Relational Mapping) qui permet de faciliter le développement de la couche persistance d'une application. Hibernate permet donc de représenter une base de données en objets Java et vice versa.

Hibernate facilite la persistance et la recherche de données dans une base de données en réalisant lui-même la création des objets et les traitements de remplissage de ceux-ci en accédant à la base de données. La quantité de code ainsi épargnée est très importante d'autant que ce code est généralement fastidieux et redondant.

Hibernate est très populaire notamment à cause de ses bonnes performances et de son ouverture à de nombreuses bases de données.

#### Architecture (SessionFactory / Session / Connection)

![Hibernate structure](http://www.dil.univ-mrs.fr/~massat/docs/hibernate-3.1/reference/fr/shared/images/lite.gif)


#### Implémentations (HQL, Spring Data JPA, Criteria, QueryDSL

##### HQL :

Hibernate fournit un langage d'interrogation extrêmement puissant qui ressemble (et c'est voulu) au SQL. Mais ne soyez pas dupe de la syntaxe ; HQL est totalement orienté objet, cernant des notions comme l'héritage, le polymorphisme et les associations.

HQL respecte les paramètres lazy des mappings et garantit que ce que vous voulez charger est chargé. Cela signifie qu'une requête HQL peut entraîner plusieurs instructions SQL SELECT immédiates pour extraire le sous-graphe avec toutes les associations et collections mappées non lazy.

#####  Criteria :

L'interface Criteria représente une requête sur une classe persistante donnée. La Session fournit les instances de Criteria.

Criteria respecte les paramètres lazy des mappings et garantit que ce que vous voulez charger est chargé. Cela signifie qu'une requête Criteria peut générer plusieurs instructions SELECT SQL immédiates pour extraire le sous-graphe avec toutes les associations et collections mappées non Lazy.

Les requêtes de Criteria respectent également complètement la stratégie de récupération (join vs select vs subselect).

#####  QueryDSL :

Querydsl est né de la nécessité de maintenir les requêtes HQL typesafe. La construction incrémentielle de requêtes HQL nécessite la concaténation de chaînes et génère un code difficile à lire. Les références non sûres aux types de domaine et aux propriétés via des chaînes simples constituaient un autre problème avec la construction HQL basée sur String.

Avec un domaine en évolution, des types de modèles typesafe apportent d’énormes avantages au développement logiciel. Les modifications de domaine sont directement reflétées dans les requêtes et la saisie semi-automatique dans la construction de la requête accélère et sécurise la construction de la requête.

HQL pour Hibernate était le premier langage cible de Querydsl, mais de nos jours, il prend en charge JPA, JDO, JDBC, Lucene, Hibernate Search, MongoDB, Collections et RDFBean.

#####  Spring Data JPA :

Spring Data JPA, qui fait partie de la plus grande famille Spring Data, facilite la mise en œuvre de référentiels basés sur JPA. Ce module traite de la prise en charge améliorée des couches d’accès aux données basées sur JPA. Cela facilite la création d'applications utilisant Spring, qui utilisent les technologies d'accès aux données.

L'implémentation d'une couche d'accès aux données d'une application est une tâche fastidieuse. Trop de code boilerplate doit être écrit pour exécuter des requêtes simples ainsi que pour effectuer la pagination et l'audit. Spring Data JPA vise à améliorer de manière significative la mise en œuvre des couches d’accès aux données en réduisant l’effort au niveau réellement requis. En tant que développeur, vous écrivez vos interfaces de référentiel, y compris des méthodes de recherche personnalisées, et Spring assurera automatiquement la mise en œuvre. Notamment avec la mise en place « automatique » d’un CRUD et de pagination.

#### Object Mapping

Vous trouverez ci-dessous les annotations qui sont couramment utilisées pour mapper des entités du modèle aux entités de la base de données.

| Annotation | Rôle
| -          | -     
| @javax.persistence.Table | Préciser le nom de la table concernée par le mapping
| @javax.persistence.Column | Associer un champ de la table à la propriété (à utiliser sur un getter)
| @javax.persistence.Id | Associer un champ de la table à la propriété en tant que clé primaire (à utiliser sur un getter)
| @javax.persistence.GeneratedValue | Demander la génération automatique de  la clé primaire au besoin
| @javax.persistence.Basic | Représenter la forme de mapping la plus simple. Cette annotation est utilisée par défaut

#### States

Les objets Hibernate peuvent posséder à un instant donné l’un des trois états suivants :

-   **transient :** Une nouvelle instance d’une classe persistante, qui n’est pas associée à une Session et n’a ni d’identifiant, ni de réprésentation dans la base de données.
-   **persistent :** Vous pouvez rendre persitente une instance transient en l'associant à une session. Une instance persistante a une représentation dans la base de données, un identifiant et est associée à une Session.
-   **detached :** Une fois la session Hibernate fermée, l’instance persistante deviendra une instance détachée.

#### Get VS Load

| Get() | Load()
| -     | -
| Si objet non trouvé: Retourne null | Si objet non trouvé : Throws ObjectNotFoundException 
| Fait appel à la base de données | Ne fait pas appel à la base de données
Retourne l’objet réel | Retourne un Proxy
| A utiliser dans le cas où l’on n’est pas sûr de l’existence d’un objet | A utiliser dans le cas où l’on est sûr de l’existence d’un objet


#### Lazy / Eager

**Eager** : On effectue la jointure sql, dès que l'on récupère l'objet et donc initialise la collection.

**Lazy** : On n'effectue la jointure sql que à la demande, c'est à dire dès que l'on aura besoin de la collection.

#### Cache

**Mise en cache au niveau de la session (premier niveau)**: la mise en cache au niveau de la session est mise en œuvre par Hibernate en tant que niveau de mise en cache par défaut. Cette mise en cache permet de mettre en cache des objets dans la session. Ces objets mis en cache résident dans la mémoire de session elle-même.

**Cache de second niveau**: le cache de second niveau est responsable de la mise en cache des objets entre les sessions. Lorsque la mise en cache de deuxième niveau est activée, les objets sont d'abord recherchés dans la mémoire cache, puis s'ils ne sont pas trouvés, une requête de base de données est exécutée pour la rechercher. Le cache de second niveau sera utilisé chaque fois que les objets sont chargés à l'aide de leur clé primaire. Les objets de cache de second niveau extraient également leurs associations respectives et résident dans une pile de mémoire distincte de la session.

**Cache de requêtes**: Le cache de requête sert à stocker les effets consécutifs d'une requête. Au moment où la mise en cache de la requête est activée, les résultats de la requête sont mis de côté par rapport à la requête et aux paramètres déclenchés. Chaque fois que la requête est relâchée, l'infrastructure du magasin vérifie la combinaison des paramètres et de la requête. Si les résultats sont trouvés dans le cache, ils sont renvoyés. En règle générale, une transaction de base de données est lancée. C’est évident, c’est tout sauf une pensée intelligente de réserver une enquête dans le cas où elle possède divers paramètres, puisqu’un paramètre isolé peut revêtir diverses qualités. Pour chacun de ces mélanges, les résultats sont conservés dans la mémoire. Cela peut entraîner une utilisation plus importante de la mémoire.

# REST

REST, ou REpresentational State Transfer, est un style architectural permettant de fournir des normes entre les systèmes informatiques sur le Web, ce qui facilite la communication entre les systèmes. Les systèmes conformes à REST, souvent appelés systèmes RESTful, sont caractérisés par la façon dont ils sont Stateless et séparent les préoccupations du client et du serveur.

### Les verbes REST et quand les utiliser

| HTTP Verb |	CRUD         | Entire Collection (e.g. /customers)                                                                  | Specific Item (e.g. /customers/{id})                                       |
|-----------|----------------|------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------|
| POST 	    | Create 	     | 201 (Created), 'Location' header with link to /customers/{id} containing new ID. 	                | 404 (Not Found), 409 (Conflict) if resource already exists..               |
| GET 	    | Read 	         | 200 (OK), list of customers. Use pagination, sorting and filtering to navigate big lists. 	        | 200 (OK), single customer. 404 (Not Found), if ID not found or invalid.    |
| PUT 	    | Update/Replace | 405 (Method Not Allowed), unless you want to update/replace every resource in the entire collection. | 200 (OK) or 204 (No Content). 404 (Not Found), if ID not found or invalid. |
| PATCH 	| Update/Modify  | 405 (Method Not Allowed), unless you want to modify the collection itself. 	                        | 200 (OK) or 204 (No Content). 404 (Not Found), if ID not found or invalid. |
| DELETE 	| Delete 	     | 405 (Method Not Allowed), unless you want to delete the whole collection—not often desirable. 	    | 200 (OK). 404 (Not Found), if ID not found or invalid.                     |

### Swagger, une belle UI pour voir et tester ses services

Framework qui offre des outils permettant de générer la documentation pour son API Web. Il offre également une interface permettant d’explorer et tester les différentes méthodes offertes par le service.

Exemple d'interface que peut montrer Swagger:

![Swagger UI](http://wso2-oxygen-tank.10903.n7.nabble.com/attachment/148476/4/Screen%20Shot%202017-04-25%20at%207.21.51%20PM.png)

Imports nécessaires à Swagger :

```
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.8.0</version>
    <scope>compile</scope>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.8.0</version>
    <scope>compile</scope>
</dependency>
```

# Hands On

* ###### Création du projet avec Spring Boot

* ###### Ajout de la base H2

* ###### Création du modèle de données

* ###### Ajout de Swagger

* ###### Ajout du mapping Hibernate

* ###### Ajout du CRUD pour les ingrédients

* ###### Ajout du CRUD pour les recettes

# Sources

http://www.groupeafg.com/spring-boot-kesako/

http://javablabla.blogspot.fr/2014/10/h2-la-base-de-donnees-de-poche-au.html

https://www.jmdoudoux.fr/java/dej/chap-hibernate.htm

https://www.developpez.com/actu/178434/Comprendre-la-specification-OpenAPI-Swagger-et-apprendre-a-utiliser-Swagger-Editor-par-Hinault-Romaric/

https://blog.octo.com/designer-une-api-rest/

https://www.mkyong.com/spring-boot/spring-boot-deploy-war-file-to-tomcat/
