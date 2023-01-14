package com.driver.controller;

import com.driver.repositories.BlogRepository;
import com.driver.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/get-all-blogs")
    public ResponseEntity<Integer> getAllBlogs() {
        int countOfBlogs = blogService.showBlogs().size();
        return new ResponseEntity<>(countOfBlogs, HttpStatus.OK);
    }

    @PostMapping("/create-blog")
    public ResponseEntity createBlog(@RequestParam("userId") Integer userId ,
                                           @RequestParam("title") String title,
                                           @RequestParam("content") String content) {
        blogService.createAndReturnBlog(userId, title, content);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{blogId}/add-image")
    public ResponseEntity<String> addImage(@PathVariable("blogId") int blogId,
                                           @RequestParam("description") String description, @RequestParam("dimensions") String dimensions) {
        blogService.addImage(blogId, description,dimensions);
        return new ResponseEntity<>("Added image successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteBlog(@PathVariable("blogId") int blogId) {
        blogService.deleteBlog(blogId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}




