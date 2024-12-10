package org.myblognew.MyBlogNew.repository;

import org.myblognew.MyBlogNew.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
