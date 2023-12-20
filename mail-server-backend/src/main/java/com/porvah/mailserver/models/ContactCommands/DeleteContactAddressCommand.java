package com.porvah.mailserver.models.ContactCommands;

import com.porvah.mailserver.models.Contact;

import java.util.Map;

public class DeleteContactAddressCommand implements ContactCommandIF{

    final private String name;
    final private String email;

    public DeleteContactAddressCommand(String name, String email) {
        this.name = name;
        this.email = email;

    }
    @Override
    public boolean execute(Map<String, Contact> contacts) {

        if(contacts.containsKey(name)){
            Contact contact = contacts.get(name);
            contact.removeEmail(email);
            return true;
        }
        return false;
    }

}
