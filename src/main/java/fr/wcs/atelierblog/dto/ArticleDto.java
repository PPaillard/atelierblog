package fr.wcs.atelierblog.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ArticleDto {

    // Permet de valider que le title n'est pas envoyé null
    @NotEmpty(message = "Le titre est requis")
    @Size(min=10, max = 150)
    private String title;

    @NotEmpty(message = "Le contenu est requis")
    private String content;

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
}
