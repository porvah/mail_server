package com.porvah.mailserver.models.ContactCommands;

import com.porvah.mailserver.models.Contact;

import java.util.Map;

public class DeleteContactCommand implements ContactCommandIF {

    final private String name;

    public DeleteContactCommand(String name) {
        this.name = name;
    }
    @Override
    public boolean execute(Map<String, Contact> contacts) {
        if(contacts.containsKey(name)){
            contacts.remove(name);
            return true;
        }
        return false;
    }
}
