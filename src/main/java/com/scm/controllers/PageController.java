package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entites.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class PageController {

    @Autowired
    private UserService userService;

    //home route
    @RequestMapping("/home")
    public String home(Model model)
    {
        System.out.println("Home handler");
        model.addAttribute("name","Sagnik Bose");
        model.addAttribute("profession","SE");
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage() {
        System.out.println("About page loading");
        return "about";
    }

    @GetMapping("/services")
    public String servicesPage() {
        System.out.println("Services page loading");
        return "services";
    }

    @GetMapping("/contact")
    public String contactPage() {
        System.out.println("contact page loading");
        return "contact";
    }


    @GetMapping("/login")
    public String loginPage() {
        System.out.println("contact page loading");
        return "login";
    }
    

    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm=new UserForm();
        model.addAttribute("userForm",userForm);
        return "register";
    }

    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute UserForm userForm, HttpSession session,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return "register";
        }
        System.out.println(userForm);
        System.out.println("Processign registration");

        // User user=User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .about(userForm.getAbout())
        // .password(userForm.getPassword())
        // .phoneNumber(userForm.getPhoneNumber())
        // .build();

        System.out.println(bindingResult);

       
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setAbout(userForm.getAbout());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://www.pngall.com/wp-content/uploads/5/User-Profile-PNG-Image.png");
    
        User savedUser=userService.saveUser(user);
        Message message=Message.builder().content("Registration successful").type(MessageType.green).build();
        session.setAttribute("message", message);
        System.out.println("user saved");
        return "redirect:/register";
    }
    

}
