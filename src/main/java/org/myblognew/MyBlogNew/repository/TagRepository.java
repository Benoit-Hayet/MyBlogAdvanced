package org.myblognew.MyBlogNew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.myblognew.MyBlogNew.model.Tag;


public interface TagRepository extends JpaRepository<Tag, Long> {
}
