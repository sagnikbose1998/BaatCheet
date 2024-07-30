package com.scm.services;

import com.scm.entities.Contact;
import com.scm.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    Contact save(Contact contact);

    Contact update(Contact contact);

    List<Contact> getAll();

    Contact getById(String id);

    Page<Contact> searchByName(String nameKeyword,int size, int page,String sortBy, String order,User user);

    Page<Contact> searchByEmail(String emailKeyword,int size, int page,String sortBy, String order,User user);

    Page<Contact> searchByPhone(String phoneKeyword,int size, int page,String sortBy, String order,User user);

    List<Contact> getByUserId(String userId);

    void delete(String id);

    Page<Contact> getByUser(User user, int page, int size, String sortField,String sortDirection);


}
