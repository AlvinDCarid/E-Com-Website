package com.ecommerce.ecom_website.DTO;

import com.ecommerce.ecom_website.model.enums.Role;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class UserResponse {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Role role;
    private LocalDateTime createdAt;

}
