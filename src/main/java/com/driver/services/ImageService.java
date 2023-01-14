package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    @Autowired
    BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image = new Image();
        image.setBlog(blog);
        image.setDescription(description);
        image.setDimensions(dimensions);
        imageRepository2.save(image);

        List<Image> list = blog.getImageList();
        if(list == null){
            List<Image> images = new ArrayList<>();
            images.add(image);
            blog.setImageList(images);
            return image;
        }
        else{
            list.add(image);
            blog.setImageList(list);
        }
        blogRepository.save(blog);

        return image;
    }



    public void deleteImage(Image image){
        imageRepository2.deleteById(image.getId());
    }

    public Image findById(int id) {
        return imageRepository2.findById(id).get();
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        if(image == null) return 0;

        String dimension = image.getDimensions();
        String[] d = dimension.split("X"), r = screenDimensions.split("X");
        int d1 = Integer.valueOf(d[0]), d2 = Integer.valueOf(d[1]);
        int r1 = Integer.valueOf(r[0]), r2 = Integer.valueOf(r[1]);

        int result = (int) ((r1 / d1) * (r2 / d2));

        return result;
    }


}
