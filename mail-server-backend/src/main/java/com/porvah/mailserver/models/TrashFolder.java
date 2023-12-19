package com.porvah.mailserver.models;

import com.porvah.mailserver.interfaces.ROMail;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class TrashFolder extends MailFolder{
    private String name;
    private List<ROMail> mails;
    public TrashFolder(String name){
        super(name);
    }
    @Override
    public void addMail(ROMail mail) {
        this.removeOldMails();
        this.mails.add(mail);
    }
    private void removeOldMails(){
        Date currentDate=new Date();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault());
        for(ROMail mail : this.mails){
            if(Duration.between(LocalDateTime.ofInstant(mail.getSentDate().toInstant(), ZoneId.systemDefault())
                    , localDateTime).toDays() > 30){
                this.removeMail(mail.getId());
            }
        }
    }
}
