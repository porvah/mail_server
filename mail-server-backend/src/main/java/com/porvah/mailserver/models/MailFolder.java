package com.porvah.mailserver.models;

import com.porvah.mailserver.interfaces.ROMail;

import java.util.ArrayList;
import java.util.List;

public class MailFolder {
    private List<ROMail> mails;

    public MailFolder(){
        this.mails = new ArrayList<ROMail>();
    }
    public void addMail(ROMail mail){
        this.mails.add(mail);
    }
    public void removeMail(int id){
        for(ROMail mail : this.mails){
            if(mail.getId() == id){
                this.mails.remove(mail);
            }
        }
    }
    public List<ROMail> getMails(){
        return this.mails;
    }
    public ROMail getMail(int id){
        for(ROMail mail : this.mails){
            if(mail.getId() == id){
                return mail;
            }
        }
        throw new RuntimeException("mail not found");
    }
}
