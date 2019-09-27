package linhVu.blog.model;

import javax.persistence.*;

@Entity
@Table(name="blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private String picture;

    public Blog() {
    }

    public Blog(String title, String content, String picture) {
        this.title = title;
        this.content = content;
        this.picture = picture;
    }

    public Blog(Integer id, String title, String content, String picture) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.picture = picture;
    }
//
//    @Override
//    public String toString() {
//        return "Blog{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                ", picture='" + picture + '\'' +
//                '}';
//    }

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
