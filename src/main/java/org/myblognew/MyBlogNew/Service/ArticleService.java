package org.myblognew.MyBlogNew.Service;

import org.myblognew.MyBlogNew.dto.ArticleDTO;
import org.myblognew.MyBlogNew.mapper.ArticleMapper;
import org.myblognew.MyBlogNew.model.Article;
import org.myblognew.MyBlogNew.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleMapper articleMapper;

    public List<ArticleDTO> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(articleMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ArticleDTO> getArticleById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        return article.map(articleMapper::convertToDTO);
    }

    public ArticleDTO createArticle(ArticleDTO articleDTO) {
        Article article = articleMapper.convertToEntity(articleDTO);
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());
        Article savedArticle = articleRepository.save(article);
        return articleMapper.convertToDTO(savedArticle);
    }

    public Optional<ArticleDTO> updateArticle(Long id, ArticleDTO articleDTO) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if (articleOptional.isEmpty()) {
            return Optional.empty();
        }

        Article article = articleOptional.get();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setUpdatedAt(LocalDateTime.now());

        Article updatedArticle = articleRepository.save(article);
        return Optional.of(articleMapper.convertToDTO(updatedArticle));
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    public List<ArticleDTO> getArticlesByTitle(String title) {
        List<Article> articles = articleRepository.findByTitle(title);
        return articles.stream()
                .map(articleMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ArticleDTO> getArticlesByContent(String content) {
        List<Article> articles = articleRepository.findByContentContaining(content);
        return articles.stream()
                .map(articleMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ArticleDTO> getArticlesCreatedAfter(LocalDateTime date) {
        List<Article> articles = articleRepository.findByCreatedAtAfter(date);
        return articles.stream()
                .map(articleMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ArticleDTO> getLatestArticles() {
        List<Article> articles = articleRepository.findTop5ByOrderByCreatedAtDesc();
        return articles.stream()
                .map(articleMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}