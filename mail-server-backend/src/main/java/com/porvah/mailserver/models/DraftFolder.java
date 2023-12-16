package com.porvah.mailserver.models;


import java.util.ArrayList;
import java.util.List;

public class DraftFolder{
    private List<Mail> mails;

    public DraftFolder(){
        this.mails = new ArrayList<Mail>();
    }

    public void addMail(Mail mail){
        this.mails.add(mail);
    }
    public void removeMail(int id){
        for(Mail mail : this.mails){
            if(mail.getId() == id){
                this.mails.remove(mail);
            }
        }
    }
    public List<Mail> getMails(){
        return this.mails;
    }
    public Mail getMail(int id){
        for(Mail mail : this.mails){
            if(mail.getId() == id){
                return mail;
            }
        }
        throw new RuntimeException("mail not found");
    }
}
