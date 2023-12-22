package com.porvah.mailserver.models;

import java.util.ArrayList;

public class Contact{

    private static int contactCounter = 0;

    private final int contactId;
    private String name;
    private ArrayList<String> emails;

    public Contact(String name, ArrayList<String> emails) {
        this.contactId = contactCounter++;
        this.name = name;
        this.emails = emails;
    }



    public int getContactId() {
        return contactId;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    
    public void update(String name, ArrayList<String> emails){
        this.name = name;
        this.emails = emails;
    }
}