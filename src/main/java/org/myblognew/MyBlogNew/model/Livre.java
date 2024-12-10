package org.myblognew.MyBlogNew.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
        private String titre;
        private String auteur;
        private int anneePublication;

        // Getter pour l'ID
        public Long getId() {
            return id;
        }

        // Setter pour l'ID
        public void setId(Long id) {
            this.id = id;
        }

        // Getter pour le titre
        public String getTitre() {
            return titre;
        }

        // Setter pour le titre
        public void setTitre(String titre) {
            this.titre = titre;
        }
}
