package linhVu.blog.model;

import org.springframework.web.multipart.MultipartFile;

public class BlogForm {

    private Integer id;
    private String title;
    private String content;
    private MultipartFile picture;

    public BlogForm() {
    }

    public BlogForm(String title, String content, MultipartFile picture) {
        this.title = title;
        this.content = content;
        this.picture = picture;
    }

    public BlogForm(Integer id, String title, String content, MultipartFile picture) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.picture = picture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }
}
