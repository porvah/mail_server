package com.porvah.mailserver.models;

import com.porvah.mailserver.interfaces.ROMail;
import com.porvah.mailserver.models.ContactCommands.ContactCommandInvoker;

import java.util.*;

public class UserData {

    private final MailFolder<ROMail> inbox;
    private final MailFolder<ROMail> sent;
    private final TrashFolder trash;
    private final MailFolder<Mail> draft;

    private final Map<String, MailFolder<ROMail>> customFolders;

    private final Map<Integer, Contact> contacts; // contactId, contact

    private final ContactCommandInvoker contactCommandInvoker;

    public UserData() {
        this.inbox = new MailFolder<ROMail>("inbox");
        this.sent = new MailFolder<ROMail>("sent");
        this.trash = new TrashFolder("trash");
        this.draft = new MailFolder<Mail>("draft");

        this.customFolders = new HashMap<>();

        this.contacts = new TreeMap<>();

        this.contactCommandInvoker = new ContactCommandInvoker(this.contacts);
    }


    public MailFolder<ROMail> getInbox() {
        return inbox;
    }

    public MailFolder<ROMail> getSent() {
        return sent;
    }

    public MailFolder<ROMail> getTrash() {
        return trash;
    }

    public MailFolder<Mail> getDraft() {
        return draft;
    }

    public MailFolder<ROMail> getCustomFolder(String folderName){
        return customFolders.get(folderName);
    }

    public void addCustomFolder(String folderName){
        customFolders.put(folderName, new MailFolder<ROMail>(folderName));
    }

    public void removeCustomFolder(String folderName){
        customFolders.remove(folderName);
    }

    public List<MailFolder<ROMail>> getCustomFolders(){
        return new ArrayList<MailFolder<ROMail>>(this.customFolders.values());
    }

    public Map<Integer, Contact> getContacts() {
        return contacts;
    }
    public ContactCommandInvoker getContactCommandInvoker(){
        return this.contactCommandInvoker;
    }
}
