package com.porvah.mailserver.models;

import com.porvah.mailserver.enums.SortType;
import com.porvah.mailserver.interfaces.ROMail;

import java.util.List;

public class MailStrategy {
    public List<ROMail> getInbox(int token, SortType sort){
        UserBase base = UserBase.getInstance();
        UserDataRepository repo = UserDataRepository.getInstance();
        User user = base.getLoggedUser(token);
        UserData data = repo.getUserData(user.getUserDataId());
        MailFolder inbox = data.getInbox();
        return inbox.getMails(sort);
    }
    public List<ROMail> getTrash(int token, SortType sort){
        UserBase base = UserBase.getInstance();
        UserDataRepository repo = UserDataRepository.getInstance();
        User user = base.getLoggedUser(token);
        UserData data = repo.getUserData(user.getUserDataId());
        MailFolder trash = data.getTrash();
        return trash.getMails(sort);
    }
    public List<ROMail> getSent(int token, SortType sort){
        UserBase base = UserBase.getInstance();
        UserDataRepository repo = UserDataRepository.getInstance();
        User user = base.getLoggedUser(token);
        UserData data = repo.getUserData(user.getUserDataId());
        MailFolder sent = data.getSent();
        return sent.getMails(sort);
    }
    public List<Mail> getDraft(int token, SortType sort){
        UserBase base = UserBase.getInstance();
        UserDataRepository repo = UserDataRepository.getInstance();
        User user = base.getLoggedUser(token);
        UserData data = repo.getUserData(user.getUserDataId());
        DraftFolder draft = data.getDraft();
        return draft.getMails(sort);
    }
}
