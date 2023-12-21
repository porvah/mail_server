package com.porvah.mailserver.models;

import com.porvah.mailserver.enums.SortType;
import com.porvah.mailserver.interfaces.ROMail;

import java.util.Date;
import java.util.List;

public class MailStrategy {
    private final UserFacade userFacade;
    public MailStrategy(){
        this.userFacade = new UserFacade();
    }
    public List<ROMail> getInbox(int token, SortType sort){
        UserData data = this.userFacade.getUserDataByToken(token);
        MailFolder<ROMail> inbox = data.getInbox();
        return inbox.getMails(sort);
    }
    public List<ROMail> getTrash(int token, SortType sort){
        UserData data = this.userFacade.getUserDataByToken(token);
        MailFolder<ROMail> trash = data.getTrash();
        return trash.getMails(sort);
    }
    public List<ROMail> getSent(int token, SortType sort){
        UserData data = this.userFacade.getUserDataByToken(token);
        MailFolder<ROMail> sent = data.getSent();
        return sent.getMails(sort);
    }
    public List<Mail> getDraft(int token, SortType sort){
        UserData data = this.userFacade.getUserDataByToken(token);
        MailFolder<Mail> draft = data.getDraft();
        return draft.getMails(sort);
    }
    public List<MailFolder<ROMail>> getFolders(int token){
        UserData data = this.userFacade.getUserDataByToken(token);
        return data.getCustomFolders();
    }
    public List<ROMail> getFolderEmails(int token, String name, int sort){
        UserData data = this.userFacade.getUserDataByToken(token);
        return data.getCustomFolder(name).getMails(SortType.values()[sort]);
    }
    public void sendEmail(int token, List<String> receivers, String subject, String body, int priority){
        UserData senderData = this.userFacade.getUserDataByToken(token);
        User sender = UserBase.getInstance().getLoggedUser(token);
        List<UserData> receiversData = this.userFacade.getUserDataByEmail(receivers);
        for(int i = 0; i < receiversData.size(); i++){
            User receiverUser = UserBase.getInstance().getUser(receivers.get(i));
            Mail newEmail = new Mail(sender.getEmail(), receiverUser.getEmail(), subject, body, new Date(), priority);
            senderData.getSent().addMail(newEmail.submit());
            receiversData.get(i).getInbox().addMail(newEmail.submit());
        }
    }

    public void deleteEmail(int token, int id) {
        UserData senderData = this.userFacade.getUserDataByToken(token);
        this.userFacade.deleteEmailById(senderData, id);
    }

    public void draftEmail(int token, List<String> receivers, String subject, String body, int priority) {
        UserData senderData = this.userFacade.getUserDataByToken(token);
        User sender = UserBase.getInstance().getLoggedUser(token);
        for (String receiver : receivers) {
            User receiverUser = UserBase.getInstance().getUser(receiver);
            Mail newEmail = new Mail(sender.getEmail(), receiverUser.getEmail(), subject, body, new Date(), priority);
            senderData.getDraft().addMail(newEmail);
        }
    }
}
