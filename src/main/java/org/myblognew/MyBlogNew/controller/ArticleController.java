/* Résumé : Permet à l'application de gérer les requêtes HTTP et d'effectuer des opérations CRUD sur l'entité Article,
Création d'un contrôleur REST.*/

package org.myblognew.MyBlogNew.controller;

import jakarta.validation.Valid;
import org.myblognew.MyBlogNew.Service.ArticleService;
import org.myblognew.MyBlogNew.dto.ArticleCreateDTO;
import org.myblognew.MyBlogNew.dto.ArticleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //@RestController : Indique que cette classe servira de contrôleur REST, capable de gérer les requêtes HTTP.
@RequestMapping("/articles") //@RequestMapping("/articles") : Spécifie que toutes les requêtes à ce contrôleur seront mappées à l'URL /articles.
public class ArticleController {

    private final ArticleService articleService;


    public ArticleController(
            ArticleService articleService) {
        this.articleService = articleService;

    }

    /* Ancienne méthode avant les services :

    @GetMapping

        public ResponseEntity<List<ArticleDTO>> getAllArticles() {
            List<ArticleDTO> articles = articleRepository.findAll();
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ArticleDTO> articleDTOs = articles.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(articleDTOs);
    }*/

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        List<ArticleDTO> articles = articleService.getAllArticles();
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }


   /* @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (!optionalArticle.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Article article = optionalArticle.get();
        return ResponseEntity.ok(convertToDTO(article));
    }
*/
   @GetMapping("/{id}")
   public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id) {
       ArticleDTO articleDTO = articleService.getArticleById(id);
       return ResponseEntity.ok(articleDTO);
   }


/*http://localhost:8080/articles/search-title?searchTerms=First%20Post*/

    /*
    Avant les services :
    @GetMapping("/search-title")
    public ResponseEntity<List<Article>> getArticlesByTitle(@RequestParam String searchTerms) {
        List<Article> articles = articleRepository.findByTitle(searchTerms);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }*/

    /*
    @GetMapping("/articles-date/{createdAt}")
    public ResponseEntity<List<Article>> getArticlesByCreatedAt(@PathVariable LocalDateTime createdAt) {
        List<Article> articles = articleRepository.findByCreatedAt(createdAt);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }
    */




    /*
    @GetMapping("/latest")
    public ResponseEntity<List<Article>> getLatestArticles() {
        List<Article> articles = articleRepository.findTop5ByOrderByCreatedAtDesc();
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }
    */

   /* @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Article>> getArticlesByContent(@PathVariable String keyword) {
        List<Article> articles = articleRepository.findByContentContaining(keyword);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }*/

    /*@GetMapping("/created-after/{createdAt}")
    public ResponseEntity<List<Article>> getArticlesCreatedAfter(@PathVariable String createdAt) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(createdAt);
            List<Article> articles = articleRepository.findByCreatedAtAfter(dateTime);
            if (articles.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(articles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }*/

    /* Requete avant les services :

    @PostMapping
       public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO) {
        Article article = convertToEntity(articleDTO);
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());
        Article savedArticle = articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedArticle));
    }*/
    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@Valid @RequestBody ArticleCreateDTO articleCreateDTO) {
        ArticleDTO savedArticleDTO = articleService.createArticle(articleCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticleDTO);
    }


    /*
    Requete avant les services :

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable Long id, @RequestBody ArticleDTO articleDTO) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (!optionalArticle.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Article article = optionalArticle.get();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setUpdatedAt(LocalDateTime.now());

        // Mise à jour de la catégorie
        if (articleDTO.getCategoryId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(articleDTO.getCategoryId());
            if (!optionalCategory.isPresent()) {
                return ResponseEntity.badRequest().build();
            }
            Category category = optionalCategory.get();
            article.setCategory(category);
        }

        Article updatedArticle = articleRepository.save(article);
        return ResponseEntity.ok(convertToDTO(updatedArticle));
    }*/
    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable Long id, @RequestBody ArticleDTO articleDTO) {
        Optional<ArticleDTO> updatedArticleDTO = articleService.updateArticle(id, articleDTO);
        if (!updatedArticleDTO.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedArticleDTO.get());
    }

    /*
    Requete avant les services :

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {

        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (!optionalArticle.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Article article = optionalArticle.get();
        articleRepository.delete(article);
        return ResponseEntity.noContent().build();
    }


}*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        if (articleService.deleteArticle(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

