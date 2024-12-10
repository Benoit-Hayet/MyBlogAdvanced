/*package org.myblognew.MyBlogNew.mapper;

import org.myblognew.MyBlogNew.dto.ArticleCreateDTO;
import org.myblognew.MyBlogNew.dto.articleCreateDTO;
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

    public articleCreateDTO convertToDTO(Article article) {
        articleCreateDTO articleCreateDTO = new articleCreateDTO();
        articleCreateDTO.setId(article.getId());
        articleCreateDTO.setTitle(article.getTitle());
        articleCreateDTO.setContent(article.getContent());
        articleCreateDTO.setCreatedAt(article.getCreatedAt());
        articleCreateDTO.setUpdatedAt(article.getUpdatedAt());
        if (article.getCategory() != null) {
            articleCreateDTO.setCategoryId(article.getCategory().getId());
        }
        if (article.getTags() != null) {
            articleCreateDTO.setTagIds(article.getTags().stream().map(Tag::getId).collect(Collectors.toList()));
        }
        return articleCreateDTO;
    }

    public Article convertToEntity(ArticleCreateDTO articleCreateDTO) {
        Article article = new Article();
        article.setTitle(articleCreateDTO.getTitle());
        if (articleCreateDTO.getCategoryId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(articleCreateDTO.getCategoryId());
            optionalCategory.ifPresent(article::setCategory);
        }
        if (articleCreateDTO.getTagIds() != null) {
            List<Tag> tags = tagRepository.findAllById(articleCreateDTO.getTagIds());
            article.setTags(tags);
        }
        return article;
    }

}*/

