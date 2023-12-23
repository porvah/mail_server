package com.porvah.mailserver.models.ContactCommands;

import com.porvah.mailserver.models.Contact;

import java.util.ArrayList;
import java.util.Map;

public class DeleteContactsCommand implements ContactCommandIF {

    final private ArrayList<Integer> contactIds;

    public DeleteContactsCommand(ArrayList<Integer> contactIds) {
        this.contactIds = contactIds;
    }

    @Override
    public int execute(Map<Integer, Contact> contacts) {

        for(int contactId : contactIds){
            contacts.remove(contactId);
        }
        return 0;

    }
}