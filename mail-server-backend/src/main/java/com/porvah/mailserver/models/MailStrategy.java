package com.porvah.mailserver.models;

import com.porvah.mailserver.enums.SortType;
import com.porvah.mailserver.interfaces.ROMail;

import java.util.List;

public class MailStrategy {
    private UserAdapter adapter;
    public MailStrategy(){
        this.adapter = new UserAdapter();
    }
    public List<ROMail> getInbox(int token, SortType sort){
        UserData data = this.adapter.getUserDataByToken(token);
        MailFolder inbox = data.getInbox();
        return inbox.getMails(sort);
    }
    public List<ROMail> getTrash(int token, SortType sort){
        UserData data = this.adapter.getUserDataByToken(token);
        MailFolder trash = data.getTrash();
        return trash.getMails(sort);
    }
    public List<ROMail> getSent(int token, SortType sort){
        UserData data = this.adapter.getUserDataByToken(token);
        MailFolder sent = data.getSent();
        return sent.getMails(sort);
    }
    public List<Mail> getDraft(int token, SortType sort){
        UserData data = this.adapter.getUserDataByToken(token);
        DraftFolder draft = data.getDraft();
        return draft.getMails(sort);
    }
    public List<MailFolder> getFolders(int token){
        UserData data = this.adapter.getUserDataByToken(token);
        return data.getCustomFolders();
    }
}
