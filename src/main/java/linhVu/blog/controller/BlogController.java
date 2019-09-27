package linhVu.blog.controller;

import linhVu.blog.model.Blog;
import linhVu.blog.model.BlogForm;
import linhVu.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping("/create-blog")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new BlogForm());
        return modelAndView;
    }

    @PostMapping("/save-blog")
    public ModelAndView createBlog(@ModelAttribute BlogForm blogForm, BindingResult result){
        String fileName=blogService.uploadFile(blogForm,result);
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        Blog blogObject = new Blog(blogForm.getTitle(),blogForm.getContent(),fileName);
        blogService.save(blogObject);
        modelAndView.addObject("blog",new BlogForm());
        modelAndView.addObject("success","create a blog successfully!");
    return modelAndView;

    }
    @GetMapping("/blogs")
    public String listBlogs(Model model){
        List<Blog> blogList = blogService.findAll();
        model.addAttribute("blogList",blogList);
        return "blog/list";
    }

    @GetMapping("/blog/{id}/edit")
    public String editForm(@PathVariable int id, Model model){
        Blog blog = blogService.findById(id);
        model.addAttribute("blog", blog);
        return "blog/edit";
    }

    @PostMapping("/blog/update")
    public ModelAndView updateBlog(@ModelAttribute BlogForm blogForm, BindingResult result){
        Blog blogObject= blogService.findById(blogForm.getId());
        String fileName=blogService.uploadFile(blogForm,result);
        if(!fileName.equals("")){
            blogObject.setPicture(fileName);
        }
        blogObject.setTitle(blogForm.getTitle());
        blogObject.setContent(blogForm.getContent());
        blogService.save(blogObject);
        ModelAndView modelAndView = new ModelAndView("blog/edit");
        modelAndView.addObject("blog",blogObject);
        modelAndView.addObject("success","update blog successfully!");
        return modelAndView;
    }

    @PostMapping("/search-blog")
    public ModelAndView searchProductResult(@RequestParam("search-by-name") String name){
        List<Blog> blogSearchList= blogService.findByName(name);
        ModelAndView modelAndView = new ModelAndView("blog/searchResult");
        modelAndView.addObject("blogSearchList", blogSearchList);
        return modelAndView ;
    }

    @GetMapping("/blog/{id}/view")
    public String viewBlog(@PathVariable int id, Model model){
        Blog blog = blogService.findById(id);
        model.addAttribute("blog", blog);
        return "blog/view";
    }

    @GetMapping("/blog/{id}/delete")
    public  String deleteForm(@PathVariable int id, Model model){
        Blog blog= blogService.findById(id);
        model.addAttribute("blog",blog);
        return "blog/delete";
    }

    @PostMapping ("/blog/delete")
    public String deleteBlog(Blog blog){
        blogService.remove(blog.getId());
        return "redirect:/blogs";

    }

}

