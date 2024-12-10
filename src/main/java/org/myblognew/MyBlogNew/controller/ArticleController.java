/* Résumé : Permet à l'application de gérer les requêtes HTTP et d'effectuer des opérations CRUD sur l'entité Article,
Création d'un contrôleur REST.*/

package org.myblognew.MyBlogNew.controller;

import jakarta.validation.Valid;
import org.myblognew.MyBlogNew.Service.ArticleService;
import org.myblognew.MyBlogNew.dto.ArticleCreateDTO;
import org.myblognew.MyBlogNew.dto.ArticleDTO;
import org.myblognew.MyBlogNew.exception.ResourceNotFoundException;
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



    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        List<ArticleDTO> articles = articleService.getAllArticles();
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }



   @GetMapping("/{id}")
   public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("L'article avec l'id " + id + " n'a pas été trouvé"));
    }


    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@Valid @RequestBody ArticleCreateDTO articleCreateDTO) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setTitle(articleCreateDTO.getTitle());
        articleDTO.setContent(articleCreateDTO.getContent());

        ArticleDTO savedArticleDTO = articleService.createArticle(articleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticleDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable Long id, @RequestBody ArticleDTO articleDTO) {
        Optional<ArticleDTO> updatedArticleDTO = articleService.updateArticle(id, articleDTO);
        if (updatedArticleDTO.isEmpty()) { // Utilisez isEmpty() au lieu de la négation
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedArticleDTO.get());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
    }



