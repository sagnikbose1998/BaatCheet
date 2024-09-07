package com.scm.services.impl;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repositories.ContactRepo;
import com.scm.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepo contactRepo;


    @Override
    public Contact save(Contact contact) {
        String contactId= UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepo.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        Contact contactprev = contactRepo.findById(contact.getId()).orElseThrow(() -> new ResourceNotFoundException("cannot update"));
        contactprev.setName(contact.getName());
        contactprev.setEmail(contact.getEmail());
        contactprev.setPhoneNumber(contact.getPhoneNumber());
        contactprev.setAddress(contact.getAddress());
        contactprev.setDescription(contact.getDescription());
        contactprev.setPicture(contact.getPicture());
        contactprev.setFavourite(contact.isFavourite());
        contactprev.setWebsiteLink(contact.getWebsiteLink());
        contactprev.setLinkedInLink(contact.getLinkedInLink());
        contactprev.setCloudinaryImagePublicId(contact.getCloudinaryImagePublicId());
        return contactRepo.save(contactprev);

    }

    @Override
    public List<Contact> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public Contact getById(String id) {
        return contactRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Contact not found with given id::" +id));
    }

    @Override
    public Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order,User user) {
        Sort sort= order.equals("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        return contactRepo.findByUserAndNameContaining(user,nameKeyword,pageable);
    }

    @Override
    public Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order,User user) {
        Sort sort= order.equals("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        return contactRepo.findByUserAndEmailContaining(user,emailKeyword,pageable);
    }

    @Override
    public Page <Contact> searchByPhone(String phoneKeyword, int size, int page, String sortBy, String order,User user) {
        Sort sort= order.equals("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        return contactRepo.findByUserAndPhoneNumberContaining(user,phoneKeyword,pageable);
    }


    @Override
    public List<Contact> getByUserId(String userId) {

        return contactRepo.findByUserId(userId);
    }

    @Override
    public void delete(String id) {
        var contact= contactRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Contact not found with given id::" +id));
        contactRepo.delete(contact);
    }

    @Override
    public Page<Contact> getByUser(User user, int page, int size,String sortBy,String direction) {
        Sort sort=direction.equals("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();

        var pageable= PageRequest.of(page,size,sort);
        return contactRepo.findByUser(user,pageable);
    }


}
