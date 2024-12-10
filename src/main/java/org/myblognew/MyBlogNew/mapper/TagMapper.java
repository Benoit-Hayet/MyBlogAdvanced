package org.myblognew.MyBlogNew.mapper;

import org.myblognew.MyBlogNew.dto.TagDTO;
import org.myblognew.MyBlogNew.model.Article;
import org.myblognew.MyBlogNew.model.Tag;
import org.myblognew.MyBlogNew.repository.TagRepository;
import org.myblognew.MyBlogNew.repository.ArticleRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagMapper {
    public final TagRepository tagRepository;
    private final ArticleRepository articleRepository;

    public TagMapper(TagRepository tagRepository, ArticleRepository articleRepository) {
        this.tagRepository = tagRepository;
        this.articleRepository = articleRepository;
    }

    public TagDTO convertToDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tag.getId());
        tagDTO.setName(tag.getName());
        if (tag.getArticles() != null) {
            tagDTO.setArticleIds(tag.getArticles().stream().map(Article::getId).collect(Collectors.toList()));
        }
        return tagDTO;
    }

    public Tag convertToEntity(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setId(tagDTO.getId());
        tag.setName(tagDTO.getName());
        if (tagDTO.getArticleIds() != null) {
            List<Article> articles = articleRepository.findAllById(tagDTO.getArticleIds());
            tag.setArticles(articles);
        }
        return tag;
    }
}
