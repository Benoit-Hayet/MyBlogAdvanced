package org.myblognew.MyBlogNew.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;

    /*Ajoutr de la bidirectionnalité entre category et articles.

    @OneToMany(mappedBy = "category") indique une relation one-to-many entre Category et Article.
    L'attribut mappedBy spécifie que la relation est mappée par l'attribut category de l'entité Article.

   */
    @OneToMany(mappedBy = "category")
    private List<Article> articles;

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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
