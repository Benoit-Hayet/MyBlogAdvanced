package org.myblognew.MyBlogNew.dto;

import java.util.List;

public class TagDTO {

        private Long id;
        private String name;
        private List<Long> articleIds;

        // Getters et setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Long> getArticleIds() {
            return articleIds;
        }

        public void setArticleIds(List<Long> articleIds) {
            this.articleIds = articleIds;
        }
    }

