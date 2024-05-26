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


    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }
    // Home route
    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home handler");
        model.addAttribute("name", "Sagnik Bose");
        model.addAttribute("profession", "SE");
        return "home"; // Renders the home page template
    }

    @GetMapping("/about")
    public String aboutPage() {
        System.out.println("About page loading");
        return "about"; // Renders the about page template
    }

    @GetMapping("/services")
    public String servicesPage() {
        System.out.println("Services page loading");
        return "services"; // Renders the services page template
    }

    @GetMapping("/contact")
    public String contactPage() {
        System.out.println("Contact page loading");
        return "contact"; // Renders the contact page template
    }

    @GetMapping("/login")
    public String loginPage() {
        System.out.println("Login page loading");
        return "login"; // Renders the login page template
    }

    @GetMapping("/register")
    public String register(Model model) {
        // Prepares a new UserForm object to capture registration details
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register"; // Renders the registration page template
    }

    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute UserForm userForm, HttpSession session,
            BindingResult bindingResult) {
        // Validates the user registration form
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "register"; // If errors, reloads the registration page with error messages
        }
        // If no errors, proceeds with registration
        System.out.println("Processing registration");

        // Creates a new User object and populates it with form data
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setAbout(userForm.getAbout());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://www.pngall.com/wp-content/uploads/5/User-Profile-PNG-Image.png");

        // Saves the user details to the database
        User savedUser = userService.saveUser(user);

        // Creates a success message and stores it in the session
        Message message = Message.builder().content("Registration successful").type(MessageType.green).build();
        session.setAttribute("message", message);
        System.out.println("User saved");
        
        // Redirects the user back to the registration page
        return "redirect:/register";
    }
}
