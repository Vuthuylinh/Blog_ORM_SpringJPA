package linhVu.blog.service;

import linhVu.blog.model.Blog;
import linhVu.blog.model.BlogForm;
import org.springframework.validation.BindingResult;

public interface BlogService extends GeneralService<Blog> {
    String uploadFile(BlogForm blogForm, BindingResult result);
}
