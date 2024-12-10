package org.myblognew.MyBlogNew.Service;

import org.myblognew.MyBlogNew.dto.ArticleCreateDTO;
import org.myblognew.MyBlogNew.dto.ArticleDTO;
import org.myblognew.MyBlogNew.exception.ResourceNotFoundException;
import org.myblognew.MyBlogNew.mapper.ArticleMapper;
import org.myblognew.MyBlogNew.model.Article;
import org.myblognew.MyBlogNew.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public ArticleService(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    public List<ArticleDTO> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream().map(articleMapper::convertToDTO).collect(Collectors.toList());
    }


    public ArticleDTO getArticleById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("L'article avec l'id " + id + " n'a pas été trouvé"));
        return articleMapper.convertToDTO(article);
    }

    // ArticleService
    public ArticleDTO createArticle(ArticleCreateDTO articleCreateDTO) {
        Article article = articleMapper.convertToEntity(articleCreateDTO);
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());

        Article savedArticle = articleRepository.save(article);
        return articleMapper.convertToDTO(savedArticle);
    }

    public Optional<ArticleDTO> updateArticle(Long id, ArticleDTO articleDTO) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (!optionalArticle.isPresent()) {
            return Optional.empty();
        }
        Article article = optionalArticle.get();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setUpdatedAt(LocalDateTime.now());

        if (articleDTO.getCategoryId() != null) {
            articleMapper.convertToEntity(articleDTO).getCategory();
        }

        Article updatedArticle = articleRepository.save(article);
        return Optional.of(articleMapper.convertToDTO(updatedArticle));
    }

    public boolean deleteArticle(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (!optionalArticle.isPresent()) {
            return false;
        }
        articleRepository.delete(optionalArticle.get());
        return true;
    }
}