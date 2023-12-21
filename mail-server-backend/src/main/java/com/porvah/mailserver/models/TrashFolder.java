package com.porvah.mailserver.models;

import com.porvah.mailserver.enums.SortType;
import com.porvah.mailserver.interfaces.ROMail;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class TrashFolder extends MailFolder<ROMail>{
    private final List<Date> addDates;
    public TrashFolder(String name){
        super(name);
        this.addDates = new ArrayList<Date>();
    }
    private void removeOldMails(){
        Date currentDate=new Date();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault());
        for(int i = 0; i < this.mails.size(); i++){
            if(Duration.between(LocalDateTime.ofInstant(this.addDates.get(i).toInstant(), ZoneId.systemDefault())
                    , localDateTime).toDays() > 30){
                this.removeMail(this.mails.get(i).getId());
            }
        }
    }
    @Override
    public List<ROMail> getMails(SortType sort) {
        this.removeOldMails();
        return super.getMails(sort);
    }

    @Override
    public void addMail(ROMail mail) {
        super.addMail(mail);
        this.addDates.add(new Date());
    }
}
