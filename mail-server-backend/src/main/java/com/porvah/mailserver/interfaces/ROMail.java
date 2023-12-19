package com.porvah.mailserver.interfaces;

import com.porvah.mailserver.models.User;

import java.io.File;
import java.util.Date;
import java.util.List;

public interface ROMail {
    public int getId();
    public User getSender();
    public User getReceiver();
    public String getSubject();
    public String getBody();
    public Date getSentDate();
//    public List<File> getAttachments();
    public int getPriority();
}
