package com.porvah.mailserver.models;

import com.porvah.mailserver.enums.SortType;
import com.porvah.mailserver.interfaces.ROMail;

import java.util.*;

public class MailFolder {
    private final String name;
    private final List<ROMail> mails;
    private PriorityQueue<ROMail> mailsWithsPriority;

    public MailFolder(String name){
        this.name = name;
        this.mails = new ArrayList<ROMail>();
        this.mailsWithsPriority = new PriorityQueue<ROMail>((Comparator.comparingInt(ROMail::getPriority)).reversed());
    }
    String getName(){
        return this.name;
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
        else if(sort == SortType.PRIORITY) return this.QtoList(this.mailsWithsPriority);
        return result;
    }
    private List<ROMail> QtoList(PriorityQueue<ROMail> Q){
        PriorityQueue<ROMail> clone = new PriorityQueue<>(Q);
        List<ROMail> newList = new ArrayList<ROMail>();
        while(!clone.isEmpty()) newList.add(clone.poll());
        return newList;
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

    public List<ROMail> getMails() {
        return mails;
    }
}
