package com.porvah.mailserver.models;

import com.porvah.mailserver.enums.SortType;
import com.porvah.mailserver.interfaces.ROMail;

import java.util.ArrayList;
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
    public List<String> getFolders(int token){
        UserData senderData = this.userFacade.getUserDataByToken(token);
        List<String> folderNames = new ArrayList<String>();
        for( MailFolder<ROMail> folder : senderData.getCustomFolders()){
            folderNames.add(folder.getName());
        }
        return folderNames;
    }
    public List<ROMail> getFolderEmails(int token, String name, SortType sort){
        UserData data = this.userFacade.getUserDataByToken(token);
        return data.getCustomFolder(name).getMails(sort);
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

    public void deleteEmail(int token, List<Integer> id) {
        UserData senderData = this.userFacade.getUserDataByToken(token);
        this.userFacade.moveEmailById(senderData, id, senderData.getTrash(), true);
    }

    public void draftEmail(int token, String subject, String body, int priority) {
        UserData senderData = this.userFacade.getUserDataByToken(token);
        User sender = UserBase.getInstance().getLoggedUser(token);
        Mail newEmail = new Mail(sender.getEmail(), "", subject, body, new Date(), priority);
        senderData.getDraft().addMail(newEmail);
    }

    public void updateDraft(int id, int token, String subject, String description, int priority) {
        UserData senderData = this.userFacade.getUserDataByToken(token);
        Mail drafted = senderData.getDraft().getMail(id);
        drafted.setSubject(subject);
        drafted.setBody(description);
        drafted.setPriority(priority);
    }

    public void submitDraft(int id, int token, List<String> receivers, String subject, String body, int priority) {
        UserData senderData = this.userFacade.getUserDataByToken(token);
        User sender = UserBase.getInstance().getLoggedUser(token);
        List<UserData> receiversData = this.userFacade.getUserDataByEmail(receivers);
        for(int i = 0; i < receiversData.size(); i++){
            User receiverUser = UserBase.getInstance().getUser(receivers.get(i));
            Mail newEmail = new Mail(sender.getEmail(), receiverUser.getEmail(), subject, body, new Date(), priority);
            senderData.getSent().addMail(newEmail.submit());
            receiversData.get(i).getInbox().addMail(newEmail.submit());
        }
        senderData.getDraft().removeMail(id);
    }


    public void moveMails(int token, List<Integer> ids, String folderName) {
        UserData senderData = this.userFacade.getUserDataByToken(token);
        MailFolder<ROMail> folder = senderData.getCustomFolder(folderName);
        this.userFacade.moveEmailById(senderData, ids, folder, false);
    }

    public void createFolder(int token, String folderName) {
        UserData senderData = this.userFacade.getUserDataByToken(token);
        senderData.addCustomFolder(folderName);
    }
}
