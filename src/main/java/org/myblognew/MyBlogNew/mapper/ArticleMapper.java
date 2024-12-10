package org.myblognew.MyBlogNew.mapper;

import org.myblognew.MyBlogNew.dto.ArticleDTO;
import org.myblognew.MyBlogNew.model.Article;
import org.myblognew.MyBlogNew.model.Category;
import org.myblognew.MyBlogNew.model.Tag;
import org.myblognew.MyBlogNew.repository.CategoryRepository;
import org.myblognew.MyBlogNew.repository.TagRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ArticleMapper {
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    public ArticleMapper(CategoryRepository categoryRepository, TagRepository tagRepository) {
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

    public ArticleDTO convertToDTO(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        articleDTO.setCreatedAt(article.getCreatedAt());
        articleDTO.setUpdatedAt(article.getUpdatedAt());
        if (article.getCategory() != null) {
            articleDTO.setCategoryId(article.getCategory().getId());
        }
        if (article.getTags() != null) {
            articleDTO.setTagIds(article.getTags().stream().map(Tag::getId).collect(Collectors.toList()));
        }
        return articleDTO;
    }

    public Article convertToEntity(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setId(articleDTO.getId());
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setCreatedAt(articleDTO.getCreatedAt());
        article.setUpdatedAt(articleDTO.getUpdatedAt());
        if (articleDTO.getCategoryId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(articleDTO.getCategoryId());
            optionalCategory.ifPresent(article::setCategory);
        }
        if (articleDTO.getTagIds() != null) {
            List<Tag> tags = tagRepository.findAllById(articleDTO.getTagIds());
            article.setTags(tags);
        }
        return article;
    }
}