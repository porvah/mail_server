package com.porvah.mailserver.models;

import java.util.ArrayList;
import java.util.List;

public class Contact {

    private String name;

    private String phoneNum = "";
    private final ArrayList<String> emails;

    public Contact(String name, String email){
        this.name = name;
        this.emails = new ArrayList<>();
        this.emails.add(email); // To create a contact we need at least one emails.

    }

    public Contact(String name, String email, String phoneNum){
        this(name, email);
        this.phoneNum = phoneNum;
    }


    public String getName() {
        return name;
    }
    public String getPhoneNum() {
        return phoneNum;
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

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void updateName(String name) {
        this.name = name;
    }





}
