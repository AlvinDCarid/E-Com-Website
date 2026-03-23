package com.ecommerce.ecom_website.service;

import com.ecommerce.ecom_website.DTO.LoginRequest;
import com.ecommerce.ecom_website.DTO.RegisterRequest;
import com.ecommerce.ecom_website.DTO.UserResponse;
import com.ecommerce.ecom_website.model.User;
import com.ecommerce.ecom_website.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserResponse registerUser(RegisterRequest registerRequest){
            if (userRepo.existsByEmail(registerRequest.getEmail())) {
                throw new RuntimeException("Email already exists");
            }

        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());

        User savedUser = userRepo.save(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setPhoneNumber(savedUser.getPhoneNumber());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setRole(savedUser.getRole());
        userResponse.setCreatedAt(savedUser.getCreatedAt());

        return userResponse;
    }

    public UserResponse LoginUser(LoginRequest loginRequest){
        User user = userRepo.findByEmail(loginRequest.getEmail());
        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole());
        userResponse.setCreatedAt(user.getCreatedAt());

        return userResponse;
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User findUserbyId(int id){
        return userRepo.findById(id).orElse(null);
    }

    public User updateUser(int id, RegisterRequest registerRequest){
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found");}

        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        return userRepo.save(user);
    }

        public void deleteUser(int id){
            userRepo.deleteById(id);
        }

}
