package com.project.bms.model;

import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.*;
public class BookDTO {
    @NotEmpty()
    private String title;

    @NotEmpty()
    private String author;

    @NotEmpty()
    private String price;

    @Size(min = 10, max = 1000)
    private String description;

    private MultipartFile image;

    public @NotEmpty String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty String title) {
        this.title = title;
    }

    public @NotEmpty String getAuthor() {
        return author;
    }

    public void setAuthor(@NotEmpty String author) {
        this.author = author;
    }

    @Min(0)
    public String getPrice() {
        return price;
    }

    public void setPrice(@NotEmpty String price) {
        this.price = price;
    }

    public @Size(min = 10, max = 1000) String getDescription() {
        return description;
    }

    public void setDescription(@Size(min = 10, max = 1000) String description) {
        this.description = description;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
