package com.porvah.mailserver.models.ContactCommands;

import com.porvah.mailserver.models.Contact;

import java.util.Map;

public class ContactCommandInvoker {

    final Map<Integer, Contact> contacts;

    public ContactCommandInvoker(Map<Integer, Contact> contacts) {
        this.contacts = contacts;
    }


    public int executeCommand(ContactCommandIF command) {
        return command.execute(this.contacts);
    }

}
