package com.porvah.mailserver.models.ContactCommands;

import com.porvah.mailserver.models.Contact;
import com.porvah.mailserver.models.UserBase;

import java.util.ArrayList;
import java.util.Map;

public class AddContactCommand implements ContactCommandIF {

    final private String name;
    final private ArrayList<String> emails;

    public AddContactCommand(String name, ArrayList<String> emails){
        this.name = name;
        for(String email : emails){
            if(!UserBase.getInstance().containsUser(email)){
                throw new IllegalArgumentException("Invalid email address");
            }
        }
        this.emails = emails;
    }


    @Override
    public int execute(Map<Integer, Contact> contacts) {
        Contact contact = new Contact(name, emails);
        contacts.put(contact.getContactId(), contact);
        return contact.getContactId();
    }




}
