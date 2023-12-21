package com.porvah.mailserver.models;

import com.porvah.mailserver.enums.SortType;
import com.porvah.mailserver.interfaces.ROMail;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class TrashFolder extends MailFolder<ROMail>{

    public TrashFolder(String name){
        super(name);
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
    @Override
    public List<ROMail> getMails(SortType sort) {
        this.removeOldMails();
        return super.getMails(sort);
    }
}
