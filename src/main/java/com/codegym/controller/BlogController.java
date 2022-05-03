package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.BlogForm;
import com.codegym.model.Category;
import com.codegym.service.blog.BlogService;
import com.codegym.service.blog.IBlogService;
import com.codegym.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
//@PropertySource("classpath:upload_file.properties")
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

//    @Value("${file-upload}")
//    private String fileUpload;

    @ModelAttribute("category")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }


//    @GetMapping("/create-blog")
//    public ModelAndView showCreateBlogForm(){
//        ModelAndView modelAndView = new ModelAndView("/blog/create");
//        modelAndView.addObject("blog",new Blog());
//        return modelAndView;
//    }
//
//    @PostMapping("/create-blog")
//    public ModelAndView saveBlog(@ModelAttribute("blog")BlogForm blogForm) {
//
//        //Lay File anh
//        MultipartFile file = blogForm.getImage();
//
//        //Lay ten File
//        String fileName = file.getOriginalFilename();
//
//        //Lay Thong tin Book
//        String name = blogForm.getName();
//
//        String author = blogForm.getAuthor();
//        Category category = blogForm.getCategory();
//
//        //Copy File
//        try {
//            FileCopyUtils.copy(file.getBytes(),new File(fileUpload + fileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Blog blog = new Blog(name,author,fileName,category);
//
//        blogService.save(blog);
//        ModelAndView modelAndView = new ModelAndView("/blog/create");
//        modelAndView.addObject("blog", new Blog());
//        modelAndView.addObject("message", "New book created successfully");
//        return modelAndView;
//    }
//
//    @GetMapping("/blogs")
//    public ModelAndView listBook(Pageable pageable, @RequestParam("search") Optional<String> search) {
//        Page<Blog> blogs ;
//        if (search.isPresent()){
//            blogs = blogService.findAllByNameContaining(search.get(),pageable);
//        }
//        else {
//            blogs = blogService.findAll(pageable);
//        }
//        ModelAndView modelAndView = new ModelAndView("/blog/list");
//        modelAndView.addObject("blogs", blogs);
//        return modelAndView;
//    }
//
//    @GetMapping("/edit-book/{id}")
//    public ModelAndView showEditForm(@PathVariable Long id) {
//        Optional<Blog> blog = blogService.findById(id);
//        if (blog.isPresent()) {
//            ModelAndView modelAndView = new ModelAndView("/blog/edit");
//            modelAndView.addObject("blog", blog.get());
//            return modelAndView;
//        } else {
//            ModelAndView modelAndView = new ModelAndView("/error.404");
//            return modelAndView;
//        }
//    }
//
//    @PostMapping("/edit-blog")
//    public ModelAndView updateCustomer(@ModelAttribute("blog") Blog blog) {
//        blogService.save(blog);
//        ModelAndView modelAndView = new ModelAndView("/blog/edit");
//        modelAndView.addObject("blog", blog);
//        modelAndView.addObject("message", "Book updated successfully");
//        return modelAndView;
//    }
//
//    @GetMapping("/delete-blog/{id}")
//    public ModelAndView showDeleteForm(@PathVariable Long id) {
//        Optional<Blog> blog = blogService.findById(id);
//        if (blog.isPresent()) {
//            ModelAndView modelAndView = new ModelAndView("/blog/delete");
//            modelAndView.addObject("blog", blog.get());
//            return modelAndView;
//
//        } else {
//            ModelAndView modelAndView = new ModelAndView("/error.404");
//            return modelAndView;
//        }
//    }
//
//    @PostMapping("/delete-blog")
//    public String deleteCustomer(@ModelAttribute("blog") Blog blog) {
//        blogService.remove(blog.getId());
//        return "redirect:blogs";
//    }

    @GetMapping("")
    public ResponseEntity<Iterable<Blog>> findAllBlog(){
        List<Blog> blogs = (List<Blog>) blogService.findAll();
        if (blogs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogs,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Blog> findBlogById(@PathVariable Long id){
        Optional<Blog> blogOptional = blogService.findById(id);
        if (!blogOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogOptional.get(),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Blog> saveBlog(@RequestBody Blog blog){
        blogService.save(blog);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody Blog blog) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (!blogOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blog.setId(blogOptional.get().getId());
        blogService.save(blog);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable Long id) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (!blogOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blogService.remove(id);
        return new ResponseEntity<>(blogOptional.get(), HttpStatus.NO_CONTENT);
    }

}

