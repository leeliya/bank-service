# Activité pratique 1 – Micro-service de gestion de comptes bancaires
Spring Boot, Spring Data JPA, H2, REST, Swagger, Spring Data REST, DTO, GraphQL.


## 1. Création du projet
Projet Spring Boot (Web, Data JPA, H2, Lombok), port 8081.
![img.png](captures/img.png)

## 2. Entité Compte, 3. CompteRepository, 4. Test DAO
Insertion de 10 comptes au démarrage avec un CommandLineRunner.
![img_1.png](captures/img_1.png)



![img_2.png](captures/img_2.png)

## 5-7. API REST, Swagger
![img_3.png](captures/img_3.png)

![img_4.png](captures/img_4.png)

## 8. Projections
### 8.1. CompteProjection1
![img_5.png](captures/img_5.png)
#### 8.2. CompteProjection2
![img_6.png](captures/img_6.png)

## 9-10. DTO, Mapper et couche service
Le contrôleur appelle CompteService, qui utilise CompteMapper et CompteRepository.
![img_7.png](captures/img_7.png)
![img_8.png](captures/img_8.png)

## 10. Web service GraphQL
Ajout de `spring-boot-starter-graphql` et de l'interface GraphiQL (`spring.graphql.graphiql.enabled=true`), accessible sur http://localhost:8081/graphiql.
Le schéma est dans `src/main/resources/graphql/schema.graphqls` et le contrôleur est `CompteGraphqlController`.

Requêtes :

```graphql
query {
  listComptes {
    id
    solde
    type
  }
}
```

```graphql
query {
  compteById(id: "ID_D_UN_COMPTE") {
    id
    solde
    devise
    type
  }
}
```

Avec GraphQL le client choisit les attributs qu'il veut recevoir, c'est comme une projection.

![listComptes](captures/graphql-list.png)

![compteById](captures/graphql-byid.png)

## 11. Gestion des exceptions
Quand un compte n'existe pas, `GraphqlExceptionResolver` retourne un message d'erreur lisible (`Compte ... introuvable`) à la place de l'erreur par défaut.

![erreur compte introuvable](captures/graphql-erreur.png)

## 12. Mutations
`addCompte`, `updateCompte` et `deleteCompte` passent par la couche service et le DTO `CompteRequestDTO`.

```graphql
mutation {
  addCompte(compte: {solde: 5000, devise: "MAD", type: "COURANT"}) {
    id
    solde
    type
  }
}
```

![addCompte](captures/graphql-add.png)

![updateCompte](captures/graphql-update.png)

![deleteCompte](captures/graphql-delete.png)

```graphql
mutation {
  updateCompte(id: "ID_D_UN_COMPTE", compte: {solde: 9000}) {
    id
    solde
  }
}
```

```graphql
mutation {
  deleteCompte(id: "ID_D_UN_COMPTE")
}
```

## 13. Entité Client
Un client possède plusieurs comptes (`@OneToMany`) et chaque compte appartient à un client (`@ManyToOne`). Au démarrage, 3 clients sont créés avec 4 comptes chacun.

```graphql
query {
  clients {
    id
    nom
    comptes {
      id
      solde
      type
    }
  }
}
```

![clients](captures/graphql-clients.png)

```graphql
query {
  listComptes {
    id
    solde
    client {
      nom
    }
  }
}
```
