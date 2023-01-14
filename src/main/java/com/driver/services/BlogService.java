package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    UserRepository userRepository1;

    public List<Blog> showBlogs(){
        //find all blogs
        return new ArrayList<>();
    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);

        //updating the blog details
        blogRepository1.save(blog);

        //Updating the userInformation and changing its blogs
        User user = userRepository1.findById(userId).get();
        user.getBlogList().add(blog);
        userRepository1.save(user);
    }

    public Blog findBlogById(int blogId){
        Blog blog = blogRepository1.findById(blogId).get();
        return blog;
    }

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog after creating it
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        imageRepository.save(image);


        Blog blog = blogRepository1.findById(blogId).get();
        blog.getImageList().add(image);
        blogRepository1.save(blog);

    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        Blog blog = blogRepository1.findById(blogId).get();

        List<Image> images = blog.getImageList();
        for(Image image : images){
            imageRepository.deleteById(image.getId());
        }
        blog.getImageList().clear();

        blogRepository1.deleteById(blogId);
    }
}
