package fr.wcs.atelierblog.dto;

import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.validation.constraints.NotEmpty;

public class CommentDto {
    @NotEmpty
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
