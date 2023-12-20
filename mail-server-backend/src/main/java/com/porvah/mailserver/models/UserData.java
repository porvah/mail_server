package com.porvah.mailserver.models;

import com.porvah.mailserver.models.ContactCommands.ContactCommandInvoker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserData {

    private MailFolder inbox;
    private MailFolder sent;
    private TrashFolder trash;
    private DraftFolder draft;

    private Map<String, MailFolder> customFolders;

    private Map<String, Contact> contacts; // name, contact

    private final ContactCommandInvoker contactCommandInvoker;

    public UserData() {
        this.inbox = new MailFolder("inbox");
        this.sent = new MailFolder("sent");
        this.trash = new TrashFolder("trash");
        this.draft = new DraftFolder("draft");

        this.customFolders = new HashMap<>();

        this.contacts = new HashMap<>();

        this.contactCommandInvoker = new ContactCommandInvoker(this.contacts);
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

    public DraftFolder getDraft() {
        return draft;
    }

    public MailFolder getCustomFolder(String folderName){
        return customFolders.get(folderName);
    }

    public void addCustomFolder(String folderName){
        customFolders.put(folderName, new MailFolder(folderName));
    }

    public void removeCustomFolder(String folderName){
        customFolders.remove(folderName);
    }

    public List<MailFolder> getCustomFolders(){
        return new ArrayList<MailFolder>(this.customFolders.values());
    }

    public Map<String, Contact> getContacts() {
        return contacts;
    }
}
