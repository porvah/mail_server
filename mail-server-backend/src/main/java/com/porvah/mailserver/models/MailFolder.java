package com.porvah.mailserver.models;

import com.porvah.mailserver.interfaces.ROMail;

import java.util.ArrayList;
import java.util.List;

public class MailFolder {
    private List<ROMail> mails;

    public MailFolder(){
        this.mails = new ArrayList<>();
    }

    public void addMail(ROMail mail){
        mails.add(mail);
    }

    public void removeMail(ROMail mail){
        mails.remove(mail);
    }

}
