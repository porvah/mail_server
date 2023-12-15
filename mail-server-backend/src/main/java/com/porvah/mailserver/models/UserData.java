package com.porvah.mailserver.models;

import java.util.HashMap;
import java.util.Map;

public class UserData {

    private MailFolder inbox;
    private MailFolder sent;
    private MailFolder trash;
    private MailFolder draft;

    private Map<String, MailFolder> folders;

    public UserData() {
        this.inbox = new MailFolder();
        this.sent = new MailFolder();
        this.trash = new MailFolder();
        this.draft = new MailFolder();

        this.folders = new HashMap<>();
    }


    public MailFolder getInbox() {
        return inbox;
    }

    public MailFolder getSent() {
        return sent;
    }

    public MailFolder getTrash() {
        return trash;
    }

    public MailFolder getDraft() {
        return draft;
    }

    public MailFolder getCustomFolder(String folderName){
        return folders.get(folderName);
    }

    public void addCustomFolder(String folderName){
        folders.put(folderName, new MailFolder());
    }

    public void removeCustomFolder(String folderName){
        folders.remove(folderName);
    }



}
