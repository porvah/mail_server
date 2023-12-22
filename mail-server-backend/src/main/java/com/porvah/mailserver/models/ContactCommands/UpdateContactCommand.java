package com.porvah.mailserver.models.ContactCommands;

import com.porvah.mailserver.models.Contact;
import com.porvah.mailserver.models.UserBase;

import java.util.ArrayList;
import java.util.Map;

public class UpdateContactCommand implements ContactCommandIF{
    
    final private int contactId;
    final private String name;
    final private ArrayList<String> emails;
    
    public UpdateContactCommand(int contactId, String name, ArrayList<String> emails){
        this.contactId = contactId;
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

            Contact contact = contacts.get(contactId);
            contact.update(name, emails);
            return contactId;

    }
    
    
    
}
