package com.porvah.mailserver.models.ContactCommands;

import com.porvah.mailserver.models.Contact;

import java.util.ArrayList;
import java.util.Map;

public class AddContactCommand implements ContactCommandIF {

    final private String name;
    final private String email;
    final private String phoneNum;

    public AddContactCommand(String name, String email, String phoneNum){
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public AddContactCommand(String name, String email){
        this.name = name;
        this.email = email;
        this.phoneNum = "";
    }
    @Override
    public boolean execute(Map<String, Contact> contacts) {
        Contact contact = new Contact(name, email, phoneNum);
        contacts.put(name, contact);
        return true;
    }




}
