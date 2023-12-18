package com.porvah.mailserver.models;

import com.porvah.mailserver.enums.SortType;
import com.porvah.mailserver.interfaces.ROMail;

import java.util.*;

public class MailFolder {
    private final List<ROMail> mails;
    private PriorityQueue<ROMail> mailsWithsPriority;

    public MailFolder(){
        this.mails = new ArrayList<ROMail>();
        this.mailsWithsPriority = new PriorityQueue<ROMail>(Comparator.comparingInt(ROMail::getPriority).reversed());
    }
    public void addMail(ROMail mail){
        this.mails.add(mail);
        this.mailsWithsPriority.add(mail);
    }
    public void removeMail(int id){
        for(ROMail mail : this.mails){
            if(mail.getId() == id){
                this.mails.remove(mail);
                break;
            }
        }
        this.mailsWithsPriority = removeFromQ(this.mailsWithsPriority, id);
    }
    public List<ROMail> getMails(SortType sort){
        List<ROMail> result = new ArrayList<ROMail>(this.mails);
        if(sort == SortType.DESCEND) Collections.reverse(result);
        else if(sort == SortType.PRIORITY) return this.mailsWithsPriority.stream().toList();
        return result;
    }

    private PriorityQueue<ROMail> removeFromQ(PriorityQueue<ROMail> Q, int id){
        PriorityQueue<ROMail> result = new PriorityQueue<ROMail>(Comparator.comparingInt(ROMail::getPriority).reversed());
        for( ROMail mail : Q){
            if(mail.getId() != id) result.add(mail);
        }
        return result;
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
