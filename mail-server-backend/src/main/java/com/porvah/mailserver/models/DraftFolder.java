package com.porvah.mailserver.models;


import java.util.*;

public class DraftFolder{
    private List<Mail> mails;

    private PriorityQueue<Mail> mailsWithsPriority;

    public DraftFolder(){
        this.mails = new ArrayList<Mail>();
        this.mailsWithsPriority = new PriorityQueue<Mail>(Comparator.comparingInt(Mail::getPriority).reversed());
    }

    public void addMail(Mail mail){
        this.mails.add(mail);
        this.mailsWithsPriority.add(mail);
    }
    public void removeMail(int id){
        for(Mail mail : this.mails){
            if(mail.getId() == id){
                this.mails.remove(mail);
            }
        }
        this.mailsWithsPriority = removeFromQ(this.mailsWithsPriority, id);
    }

    private PriorityQueue<Mail> removeFromQ(PriorityQueue<Mail> Q, int id){
        PriorityQueue<Mail> result = new PriorityQueue<Mail>(Comparator.comparingInt(Mail::getPriority).reversed());
        for( Mail mail : Q){
            if(mail.getId() != id) result.add(mail);
        }
        return result;
    }
    public List<Mail> getMails(){
        List<Mail> result = new ArrayList<Mail>(this.mails);
        Collections.reverse(result);
        return  result;
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
