package com.porvah.mailserver.models.ContactCommands;

import com.porvah.mailserver.models.Contact;

import java.util.ArrayList;
import java.util.Map;

public interface ContactCommandIF {
    boolean execute(Map<String, Contact> contacts);
}
