package com.porvah.mailserver.models.ContactCommands;

import com.porvah.mailserver.models.Contact;

import java.util.ArrayList;
import java.util.Map;

public class AddContactCommand implements ContactCommandIF {

    final private String name;
    final private String email;

    public AddContactCommand(String name, String email){
        this.name = name;
        this.email = email;
    }


    @Override
    public boolean execute(Map<String, Contact> contacts) {
        Contact contact = new Contact(name, email);
        contacts.put(name, contact);
        return true;
    }




}
