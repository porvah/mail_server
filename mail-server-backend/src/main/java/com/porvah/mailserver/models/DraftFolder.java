package com.porvah.mailserver.models;


import com.porvah.mailserver.enums.SortType;
import com.porvah.mailserver.interfaces.ROMail;

import java.util.*;

public class DraftFolder{
    private List<Mail> mails;
    private String name;
    private PriorityQueue<Mail> mailsWithsPriority;

    public DraftFolder(String name){
        this.name = name;
        this.mails = new ArrayList<Mail>();
        this.mailsWithsPriority = new PriorityQueue<Mail>(Comparator.comparingInt(Mail::getPriority).reversed());
    }
    String getName(){
        return this.name;
    }
    public void addMail(Mail mail){
        this.mails.add(mail);
        this.mailsWithsPriority.add(mail);
    }

    public List<Mail> getMails() {
        return mails;
    }
    public void removeMail(int id){
        this.mails.removeIf(mail -> mail.getId() == id);
        this.mailsWithsPriority = removeFromQ(this.mailsWithsPriority, id);
    }

    private PriorityQueue<Mail> removeFromQ(PriorityQueue<Mail> Q, int id){
        PriorityQueue<Mail> result = new PriorityQueue<Mail>(Comparator.comparingInt(Mail::getPriority).reversed());
        for( Mail mail : Q){
            if(mail.getId() != id) result.add(mail);
        }
        return result;
    }
    public List<Mail> getMails(SortType sort){
        List<Mail> result = new ArrayList<Mail>(this.mails);
        if(sort == SortType.DESCEND) Collections.reverse(result);
        else if(sort == SortType.PRIORITY) return this.QtoList(this.mailsWithsPriority);
        return result;
    }
    private List<Mail> QtoList(PriorityQueue<Mail> Q){
        PriorityQueue<Mail> clone = new PriorityQueue<>(Q);
        List<Mail> newList = new ArrayList<Mail>();
        while(!clone.isEmpty()) newList.add(clone.poll());
        return newList;
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
