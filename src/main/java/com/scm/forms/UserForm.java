package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3,message = "Min 3 characters required")
    private String name;
    
    @NotBlank(message = "password is required. Cannot be blank")
    @Size(min=6,message = "Min 6 characters required")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Address")
    private String email;
    
    @NotBlank(message = "Contact Number cannot be blank")
    @Size(min=8,max=12,message = "Invalid contact No.")
    private String phoneNumber;
    
    @NotBlank(message = "About is required")
    @Size(min=6, max=100)
    private String about;
    
}
