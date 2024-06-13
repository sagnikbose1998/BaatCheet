package com.scm.services;

import com.scm.entities.Contact;
import com.scm.forms.ContactForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    Contact save(Contact contact);

    Contact update(Contact contact);

    List<Contact> getAll();

    Contact getById(String id);

    List<Contact> search(String name,String email, String phoneNumber);

    List<Contact> getByUserId(String userId);
    void delete(String id);

}
