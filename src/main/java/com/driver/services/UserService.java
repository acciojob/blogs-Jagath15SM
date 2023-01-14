package com.driver.services;

import com.driver.models.User;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;


    public void createUser(User user){
        userRepository3.save(user);
    }

    public void deleteUser(int userId){
        userRepository3.deleteById(userId);
    }

    public void updateUser(User user){
        int userID = user.getId();
        User user1 = userRepository3.findById(userID).get();
        user1.setBlogList(user.getBlogList());
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());

        userRepository3.save(user1);
    }

    public User findUserByUsername(String username){
        return userRepository3.findByUsername(username);
    }
}
