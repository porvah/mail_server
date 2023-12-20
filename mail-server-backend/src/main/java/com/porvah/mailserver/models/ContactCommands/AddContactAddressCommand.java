package com.porvah.mailserver.models.ContactCommands;

import com.porvah.mailserver.models.Contact;

import java.util.Map;

public class AddContactAddressCommand implements ContactCommandIF {

    final private String name;
    final private String email;

    public AddContactAddressCommand(String name, String email) {
        this.name = name;
        this.email = email;
    }
    @Override
    public boolean execute(Map<String, Contact> contacts) {

        Contact contact = contacts.get(name);
        contact.addEmail(email);
        return true;
    }
}
