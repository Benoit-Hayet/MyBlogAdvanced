package org.myblognew.MyBlogNew.dto;

import org.hibernate.validator.constraints.URL;

public class ImageDTO {

    private Long id;
    @URL(message = "L'URL de l'image doit être valide")
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @URL(message = "L'URL de l'image doit être valide") String getUrl() {
        return url;
    }

    public void setUrl(@URL(message = "L'URL de l'image doit être valide") String url) {
        this.url = url;
    }
}