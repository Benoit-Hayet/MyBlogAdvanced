//Création de la table et l'entity Tag et jointure dans articles.Relation many to many.

package org.myblognew.MyBlogNew.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Article> articles;

    /*L'annotation @ManyToMany est utilisée pour définir une relation many-to-many entre deux entités.
    Dans ce cas, elle indique que la relation many-to-many entre l'entité Tag et l'entité Article est gérée
    par la collection tags dans l'entité Article.

mappedBy = "tags" signifie que l'entité Article possède une collection tags qui gère la relation many-to-many.
En d'autres termes, l'entité Tag n'a pas la responsabilité de gérer la table de jointure :
cette responsabilité revient à l'entité Article.*/

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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}