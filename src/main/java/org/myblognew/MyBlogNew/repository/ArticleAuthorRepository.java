package org.myblognew.MyBlogNew.repository;


import org.myblognew.MyBlogNew.model.ArticleAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleAuthorRepository extends JpaRepository<ArticleAuthor, Long> {
}
