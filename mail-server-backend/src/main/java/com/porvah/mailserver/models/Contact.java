package com.porvah.mailserver.models;

import java.util.ArrayList;
import java.util.List;

public class Contact {

    private String name;

    private final ArrayList<String> emails;

    public Contact(String name, String email){
        this.name = name;
        this.emails = new ArrayList<>();
        this.emails.add(email); // To create a contact we need at least one emails.

    }



    public String getName() {
        return name;
    }

    public ArrayList<String> getEmail() {
        return emails;
    }

    public void addEmail(String emails){
        this.emails.add(emails);
    }

    public void removeEmail(String emails){
        if(this.emails.size() > 1)
            this.emails.remove(emails);
    }


    public void updateName(String name) {
        this.name = name;
    }





}
