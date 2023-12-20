package com.porvah.mailserver.models.ContactCommands;

import com.porvah.mailserver.models.Contact;

import java.util.ArrayList;
import java.util.Map;

public class AddContactAddress implements ContactCommandIF {

    final private String name;
    final private String email;

    public AddContactAddress(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public boolean execute(Map<String, Contact> contacts) {

        Contact contact = contacts.get(name);
        contact.addEmail(email);
        return true;
    }
}
