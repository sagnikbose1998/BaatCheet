package com.scm.controllers;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helpers.AppConstants;
import com.scm.helpers.Helper;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private Logger logger= LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private ImageService imageService;

    @Autowired
    UserService userService;
    @RequestMapping("add")
    public String addContactView(Model model)
    {
        ContactForm contactForm=new ContactForm();
        model.addAttribute("contactForm",contactForm);
        return "user/add_contact";
    }

    @PostMapping("/add")
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult,
                              Authentication authentication, HttpSession session){
        System.out.println(contactForm);


        if(bindingResult.hasErrors()){
            session.setAttribute("message", Message.builder()
                    .content("Please correct the following errors")
                    .type(MessageType.red)
                    .build());
            return "user/add_contact";
        }

        String username= Helper.getEmailOfLoggedInUser(authentication);
        User user=userService.getUserByEmail(username);
        Contact contact=new Contact();

        //image processing check
        logger.info("Image display : {}",contactForm.getContactImage().getOriginalFilename());

        String fileName= UUID.randomUUID().toString();
        String fileURL=imageService.uploadImage(contactForm.getContactImage(),fileName);


        //converting contactform object to Contact to save to DB
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setFavourite(contactForm.isFavourite());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setPicture(fileURL);
        contact.setCloudinaryImagePublicId(fileName);
        contact.setUser(user);

        contactService.save(contact);
        System.out.println(contactForm);
        session.setAttribute("message",Message.builder()
                .content("Succesfully added a new contact")
                .type(MessageType.green)
                .build());
        return "redirect:/user/contacts/add";
    }

    // view contact
    @GetMapping()
    public String viewContact(@RequestParam(value = "page",defaultValue = "0") int page,
                              @RequestParam(value = "size",defaultValue = AppConstants.PAGE_SIZE+"") int size,
                              @RequestParam(value = "sortBy",defaultValue = "name")String sortBy,
                              @RequestParam(value = "direction",defaultValue = "asc")String direction, Model model, Authentication authentication){
        //load all contacts
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user=userService.getUserByEmail(username);
        Page<Contact> pageContact = contactService.getByUser(user,page,size,sortBy,direction);
        model.addAttribute("pageContact",pageContact);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);
        return "user/contacts";
    }
}
