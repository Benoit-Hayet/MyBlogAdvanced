package org.myblognew.MyBlogNew.repository;

import org.myblognew.MyBlogNew.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
