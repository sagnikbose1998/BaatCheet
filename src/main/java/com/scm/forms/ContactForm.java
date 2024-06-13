package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class ContactForm {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @Email(message = "[example@abc.com]")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Phone Number cannot be blank")
    @Pattern(regexp = "^[0-9]{10}$",message = "Invalid Phone Number")
    private String phoneNumber;
    @NotBlank(message = "Address cannot be blank")
    private String address;
    private String description;
    private boolean favourite=false;
    private String websiteLink;
    private String linkedInLink;
    private MultipartFile contactImage;
}
