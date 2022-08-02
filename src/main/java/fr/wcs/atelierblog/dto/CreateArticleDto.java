package fr.wcs.atelierblog.dto;


import javax.validation.constraints.NotNull;

public class CreateArticleDto {
    // Permet de valider que le title n'est pas envoyé null
    @NotNull(message = "Le titre est requis")
    private String title;

    @NotNull(message = "Le contenu est requis")
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
