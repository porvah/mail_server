package com.porvah.mailserver.models.ContactCommands;

import com.porvah.mailserver.models.Contact;

import java.util.Map;

public class ContactCommandInvoker {

    final Map<String, Contact> contacts;

    public ContactCommandInvoker(Map<String, Contact> contacts) {
        this.contacts = contacts;
    }


    public void executeCommand(ContactCommandIF command) {
        command.execute(this.contacts);
    }

}
