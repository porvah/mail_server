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
    public Mail(String sender, String receiver, String subject, String body, Date sentDate, int priority){
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.body = body;
        this.sentDate = sentDate;
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
    public ROMail submit(){
        ROMail submitted = this;
        return submitted;
    }
}
