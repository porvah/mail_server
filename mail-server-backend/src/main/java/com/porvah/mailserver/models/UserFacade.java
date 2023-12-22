package com.porvah.mailserver.models;

import com.porvah.mailserver.interfaces.ROMail;

import java.util.ArrayList;
import java.util.List;

public class UserFacade {
    public UserData getUserDataByToken(int token){
        UserBase base = UserBase.getInstance();
        UserDataRepository repo = UserDataRepository.getInstance();
        User user = base.getLoggedUser(token);
        return repo.getUserData(user.getUserDataId());
    }
    public List<UserData> getUserDataByEmail(List<String> emails){
        UserBase base = UserBase.getInstance();
        UserDataRepository repo = UserDataRepository.getInstance();
        List<UserData> userList = new ArrayList<UserData>();
        for(String email : emails){
            User user = base.getUser(email);
            userList.add(repo.getUserData(user.getUserDataId()));
        }
        return userList;
    }

    public void moveEmailById(UserData senderData, List<Integer> ids, MailFolder<ROMail> toFolder, boolean trashing) {
        MailFolder<ROMail> inbox = senderData.getInbox();
        MailFolder<ROMail> trash = senderData.getTrash();
        MailFolder<ROMail> sent = senderData.getSent();
        MailFolder<Mail> draft = senderData.getDraft();
        List<MailFolder<ROMail>> folders = senderData.getCustomFolders();
        for(Integer id : ids){
            if(inbox.contains(id)){
                ROMail mail = inbox.getMail(id);
                inbox.removeMail(id);
                if(trashing) trash.addMail(mail);
                else toFolder.addMail(mail);
            }else if(trash.contains(id)){
                ROMail mail = trash.getMail(id);
                trash.removeMail(id);
                if(!trashing) toFolder.addMail(mail);
            }else if(sent.contains(id)){
                ROMail mail = sent.getMail(id);
                sent.removeMail(id);
                if(trashing) trash.addMail(mail);
                else toFolder.addMail(mail);
            }else if(draft.contains(id)){
                draft.removeMail(id);
            }else{
                for(MailFolder<ROMail> folder : folders){
                    if(folder.contains(id)) {
                        ROMail mail = folder.getMail(id);
                        folder.removeMail(id);
                        if(trashing) trash.addMail(mail);
                        else toFolder.addMail(mail);
                    }
                }
            }
        }
    }
}
