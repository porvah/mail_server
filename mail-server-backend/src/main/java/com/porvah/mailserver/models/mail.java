package com.porvah.mailserver.models;

import com.porvah.mailserver.interfaces.ROMail;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Mail implements ROMail{
    private static int counter = 0;
    private int id;
    private User sender;
    private User receiver;
    private String subject;
    private String body;
    private Date sentDate;
    private int priority;
    private List<File> attachments;
    public Mail(User sender, User receiver){
        this.sender = sender;
        this.receiver = receiver;
        this.attachments = new ArrayList<>();
        this.id = counter;
        counter++;
    }
    public Mail(User sender, User receiver, String subject, String body, Date sentDate, List<File> attachments, int priority){
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.body = body;
        this.sentDate = sentDate;
        this.attachments = attachments;
        this.priority = priority;
        this.id = counter;
        counter++;
    }
    public int getId(){
        return this.id;
    }
    public User getSender(){
        return this.sender;
    }
    public int getPriority(){
        return this.priority;
    }
    public void setPriority(int priority){
        this.priority = priority;
    }
    public void setSender(User sender){
        this.sender = sender;
    }
    public User getReceiver(){
        return this.receiver;
    }
    public void setReceiver(User receiver){
        this.receiver = receiver;
    }
    public String getSubject(){
        return this.subject;
    }
    public void setSubject(String subject){
        this.subject = subject;
    }
    public String getBody(){
        return this.body;
    }
    public void setBody(String body){
        this.body = body;
    }
    public Date getSentDate(){
        return this.sentDate;
    }
    public void setSentDate(Date date){
        this.sentDate = date;
    }
    public List<File> getAttachments(){
        return this.attachments;
    }
    public void setAttachments(List<File> files){
        this.attachments = files;
    }
    public ROMail submit(){
        ROMail submitted = new Mail(this.sender, this.receiver, this.subject, this.body, this.sentDate,
                this.attachments, this.priority);
        return submitted;
    }
}
