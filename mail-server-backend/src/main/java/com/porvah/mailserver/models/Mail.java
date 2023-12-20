package com.porvah.mailserver.models;

import com.porvah.mailserver.interfaces.ROMail;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Mail implements ROMail{
    private static int counter = 0;
    private int id;
    private String sender;
    private String receiver;
    private String subject;
    private String body;
    private Date sentDate;
    private int priority;
    private List<File> attachments;
    private Mail(String sender, String receiver, String subject, String body, Date sentDate, int priority, int id){
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.body = body;
        this.sentDate = sentDate;
//        this.attachments = attachments;
        this.priority = priority;
        this.id = id;
    }
    public Mail(String sender, String receiver, String subject, String body, Date sentDate, int priority){
//                List<File> attachments, int priority){
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.body = body;
        this.sentDate = sentDate;
//        this.attachments = attachments;
        this.priority = priority;
        this.id = counter;
        counter++;
    }
    public int getId(){
        return this.id;
    }
    public String getSender(){
        return this.sender;
    }
    public int getPriority(){
        return this.priority;
    }
    public void setPriority(int priority){
        this.priority = priority;
    }
    public void setSender(String sender){
        this.sender = sender;
    }
    public String getReceiver(){
        return this.receiver;
    }
    public void setReceiver(String receiver){
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
//    public List<File> getAttachments(){
//        return this.attachments;
//    }
//    public void setAttachments(List<File> files){
//        this.attachments = files;
//    }
    public ROMail submit(){
        ROMail submitted = new Mail(this.sender, this.receiver, this.subject, this.body, this.sentDate, this.priority, this.id);
//                this.attachments, this.priority);
        return submitted;
    }
}
