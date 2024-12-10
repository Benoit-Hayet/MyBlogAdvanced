/*Résumé : Création d'une class Java qui va créer une table lors de son execution. C'est le @Entity
qui permettra de "mapper" (relier) à la base de donnée.*/

package org.myblognew.MyBlogNew.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity //Cette annotation indique que cette classe est une entité JPA et sera mappée à une table de la base de données.
public class Article {

    @Id //Cette annotation indique que la propriété annotée est la clé primaire de l'entité.
    @GeneratedValue(strategy = GenerationType.IDENTITY) /*Spécifie que la valeur de la clé primaire sera générée automatiquement par
    la base de données.

    Chaque champ de la table Article est représenté par une propriété dans l'entité Article. Les types des propriétés dans la classe
    déterminent les types de champs correspondants dans la base de données. Hibernate, le framework ORM utilisé par Spring Data JPA,
    effectue ce mappage automatiquement.*/



    private Long id; //La propriété id est de type Long, ce qui correspond à un champ de type BIGINT en SQL.

    @Column(nullable = false, length = 50)
    private String title; //La propriété title est de type String, ce qui correspond à un champ de type VARCHAR en SQL.

    @Column(columnDefinition = "TEXT")
    private String content; /*La propriété content est également de type String. En SQL, Hibernate peut le mapper à TEXT ou
    // VARCHAR en fonction de la longueur du contenu.

    Les propriétés createdAt et updatedAt sont de type LocalDateTime, ce qui correspond à un champ de type DATETIME ou TIMESTAMP en SQL.
    Ce type de champ est utilisé pour stocker des informations de date et d'heure.*/
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
/*L'annotation @Column permet de spécifier les détails du champ dans la base de données qu'il n'est pas possible de
définir directement avec le type de données. Voici quelques attributs courants de cette annotation :

L'attribut nullable indique si le champ peut contenir des valeurs NULL. Par défaut, cette valeur est true.
L'attribut length précise le nombre de caractères maximal pour les champs de type VARCHAR.
L'attribut columnDefinition permet de spécifier le type exact du champ dans la base de données. Par exemple,
@Column(columnDefinition = "TEXT") sur la propriété content indique que le champ content doit être de type TEXT.

L'attribut updatable sur la propriété createdAt avec comme valeur false empêche la mise à jour du champ.
Tu peux également utiliser l'attribut name qui spécifie le nom du champ dans la base de données. Par défaut,
le nom de la propriété est utilisé. Si ce nom est écrit en camelCase, comme les propriétés createdAt et updatedAt,
il est convertit en snake case (created_at et updated_at) dans la base de données MySQL, conformément à la la convention de nommage
que suit Spring Data JPA.

D'autres attributs te seront utiles, tels que unique qui ajoute une contrainte d'unicité (très utile s'assurer que
chaque utilisateur possède un email unique par exemple) ou precision et scale pour définir le format des propriétés liées à des tarifs*/

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    /*L'annotation @ManyToOne est utilisée pour définir une relation Many-to-One entre 2 entités.
    Dans notre cas, plusieurs articles peuvent appartenir à une seule catégorie.

L'annotation @JoinColumn(name = "category_id") spécifie le nom de la colonne de jointure à ajouter dans la table Article,
category_id faisant référence à la clé primaire de la table Category.*/

    @ManyToMany
    @JoinTable(
            name = "article_tag",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    /*L'annotation @JoinTable est utilisée pour définir la table de jointure dans une relation many-to-many.
    name = "article_tag" spécifie le nom de la table de jointure. Cette table contiendra les colonnes qui
    référencent les IDs des entités Article et Tag.
*/
    private List<Tag> tags;

    @OneToMany(mappedBy = "article")
    private List<ArticleAuthor> articleAuthors;

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}