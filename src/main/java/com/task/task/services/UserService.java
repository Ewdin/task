package com.task.task.services;

import com.task.task.entities.User;
import com.task.task.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository){this.repository = repository;}

    public User createUser(User newUser){
        return this.repository.save(newUser);
    }

    public User findUserByEmail(String email){
        return this.repository.findByEmail(email);
    }

    public User getOrCreateUser(Map<String, Object> userData){
        String email = (String) userData.get("email");

        User user = findUserByEmail(email);
        if(user == null){
            String name = (String) userData.get("nickname");
            String image = (String) userData.get("picture");
            String auth0Id = (String) userData.get("sub");

            User newUser = new User(email=email, image=image, auth0Id=auth0Id);
            return  createUser(newUser);
        }
        System.out.println(user.getEmail());
        return user;


    }
}
