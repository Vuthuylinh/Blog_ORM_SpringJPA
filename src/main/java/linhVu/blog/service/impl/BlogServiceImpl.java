package linhVu.blog.service.impl;


import linhVu.blog.model.Blog;
import linhVu.blog.model.BlogForm;
import linhVu.blog.repository.BlogRepository;
import linhVu.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
@PropertySource("classpath:global_config_app.properties")
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    Environment env;


    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findById(int id) {
        return blogRepository.findById(id);
    }

    @Override
    public List<Blog> findByName(String name) {
        return blogRepository.findByName(name);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);

    }

    public String uploadFile(BlogForm blogForm, BindingResult result){
        if (result.hasErrors()) {
            System.out.println("Result Error Occured" + result.getAllErrors());
        }
        //luu file len server
        MultipartFile multipartFile = blogForm.getPicture();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload");

        // luu file len server

        try {
            FileCopyUtils.copy(blogForm.getPicture().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }


    @Override
    public void remove(int id) {
        blogRepository.remove(id);
    }
}
