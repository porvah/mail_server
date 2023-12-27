package com.porvah.mailserver.interfaces;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;
import java.util.List;
public interface ROMail {
    public int getId();
    public String getSender();
    public String getReceiver();
    public String getSubject();
    public String getBody();
    public Date getSentDate();
    public int getPriority();
}
