/*Un repository est une interface qui permet de définir les méthodes d'accès aux données pour une entité.
Ici nous appelons l'entité article*/

package org.myblognew.MyBlogNew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.myblognew.MyBlogNew.model.Article;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    /*Création de requêtes personnalisées*/
    List<Article> findByTitle(String title);
    List<Article> findByContentContaining(String keyword);
    List<Article> findByCreatedAt(LocalDateTime createdAt);
    List<Article> findByCreatedAtAfter(LocalDateTime createdAt);
    List<Article> findTop5ByOrderByCreatedAtDesc();
/*Le nom de la méthode suit le schéma findBy + NomDeLaPropriété (dans ce cas, Title).
La méthode retourne une liste d'articles List<Article>.
La méthode prend un paramètre title de type String.
Nous venons de créer une méthode qui va retourner tous les articles de la base de données dont le titre est
strictement égal au paramètre fourni.
________________________________________________________________________________________________________________________
Utilisation des méthodes dans le Controller

Pour appeler cette méthode dans la classe ArticleController, il faut créer un endpoint qui permet de chercher des articles par leur titre.

Méthode :
"@GetMapping("/search-title")
public ResponseEntity<List<Article>> getArticlesByTitle(@RequestParam String searchTerms) {
    List<Article> articles = articleRepository.findByTitle(searchTerms);
    if (articles.isEmpty()) {
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(articles);"
}
@GetMapping("/search-title") : Indique que cette méthode doit gérer les requêtes HTTP GET envoyées à l'URL /articles/search-title.
@RequestParam String title : Extrait searchTerms en tant que paramètre de requête.
Les @RequestParam se situent après le point d'interrogation ? dans l'URL, sous forme de paires clé-valeur séparées par des esperluettes &.

articleRepository.findByTitle(searchTerms) : Appelle la méthode findByTitle de l'interface ArticleRepository pour récupérer les articles correspondant au titre donné.
Si le champ recherché contient des caractères spéciaux, assure-toi que l'URL est bien encodée. Par exemple, les espaces doivent être encodés en %20. Pour tester ce code sur Postman, utilise l'URL http://localhost:8080/articles/search-title?searchTerms=Title%201.
*/
}


/*Comme l'interface JpaRepository hérite de PagingAndSortingRepository, lui-même enfant de CrudRepository, cela signifie simplement
 que ArticleRepository hérite des méthodes CRUD de base et d'autres fonctionnalités avancées par défaut :

List<Article> findAll() : Récupère tous les articles.
<Article> findById(Long id) : Récupère un article par son identifiant.
<Article> save(article) : Sauvegarde un article (crée ou met à jour).
void deleteById(Long id) : Supprime un article par son identifiant.

Ces méthodes nous permettent de réaliser les opérations CRUD sans avoir à les implémenter nous-mêmes.


Spring Data JPA offre d'autres méthodes de requête directement dans les interfaces de repository.
Ces méthodes suivent une convention de nommage spécifique qui commence généralement par un verbe comme find, read, get,
puis est suivie par des mots clés décrivant les critères de la requête. */