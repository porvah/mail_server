package com.porvah.mailserver.models;

import com.porvah.mailserver.enums.RequiredPage;
import com.porvah.mailserver.enums.SortType;
import com.porvah.mailserver.interfaces.ROMail;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public class MailMediator {
    private final UserFacade userFacade;
    public MailMediator(){
        this.userFacade = new UserFacade();
    }
    public Map<String, Object> getInbox(int token, SortType sort, RequiredPage req){
        UserData data = this.userFacade.getUserDataByToken(token);
        MailFolder<ROMail> inbox = data.getInbox();
        Map<String, Object> responseObject = new HashMap<>();
        responseObject.put("list", inbox.getPage(req, sort));
        responseObject.put("current", inbox.iterator.getCurrentPage());
        responseObject.put("total", inbox.iterator.getTotalPages());
        return responseObject;
    }
    public Map<String, Object> getTrash(int token, SortType sort, RequiredPage req){
        UserData data = this.userFacade.getUserDataByToken(token);
        MailFolder<ROMail> folder = data.getTrash();
        Map<String, Object> responseObject = new HashMap<>();
        responseObject.put("list", folder.getPage(req, sort));
        responseObject.put("current", folder.iterator.getCurrentPage());
        responseObject.put("total", folder.iterator.getTotalPages());
        return responseObject;
    }
    public Map<String, Object> getSent(int token, SortType sort, RequiredPage req){
        UserData data = this.userFacade.getUserDataByToken(token);
        MailFolder<ROMail> folder = data.getSent();
        Map<String, Object> responseObject = new HashMap<>();
        responseObject.put("list", folder.getPage(req, sort));
        responseObject.put("current", folder.iterator.getCurrentPage());
        responseObject.put("total", folder.iterator.getTotalPages());
        return responseObject;
    }
    public Map<String, Object> getDraft(int token, SortType sort, RequiredPage req){
        UserData data = this.userFacade.getUserDataByToken(token);
        MailFolder<Mail> folder = data.getDraft();
        Map<String, Object> responseObject = new HashMap<>();
        responseObject.put("list", folder.getPage(req, sort));
        responseObject.put("current", folder.iterator.getCurrentPage());
        responseObject.put("total", folder.iterator.getTotalPages());
        return responseObject;
    }
    public List<String> getFolders(int token){
        UserData senderData = this.userFacade.getUserDataByToken(token);
        List<String> folderNames = new ArrayList<>();
        for( MailFolder<ROMail> folder : senderData.getCustomFolders()){
            folderNames.add(folder.getName());
        }
        return folderNames;
    }
    public Map<String, Object> getFolderEmails(int token, String name, SortType sort, RequiredPage req){
        UserData data = this.userFacade.getUserDataByToken(token);
        MailFolder<ROMail> folder = data.getCustomFolder(name);
        Map<String, Object> responseObject = new HashMap<>();
        responseObject.put("list", folder.getPage(req, sort));
        responseObject.put("current", folder.iterator.getCurrentPage());
        responseObject.put("total", folder.iterator.getTotalPages());
        return responseObject;

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
        MailFolder<ROMail> folder;
        if(folderName.equals("sent")) folder = senderData.getSent();
        else if(folderName.equals("inbox")) folder = senderData.getInbox();
        else folder = senderData.getCustomFolder(folderName);
        this.userFacade.moveEmailById(senderData, ids, folder, false);
    }

    public void createFolder(int token, String folderName) {
        UserData senderData = this.userFacade.getUserDataByToken(token);
        senderData.addCustomFolder(folderName);
    }

    public void deleteFolder(int token, String folderName) {
        UserData senderData = this.userFacade.getUserDataByToken(token);
        senderData.removeCustomFolder(folderName);
    }

    public void sendEmail(int token, List<String> receivers, String subject, String body, int priority,
                          List<MultipartFile> files) throws IOException {
        UserData senderData = this.userFacade.getUserDataByToken(token);
        User sender = UserBase.getInstance().getLoggedUser(token);
        List<UserData> receiversData = this.userFacade.getUserDataByEmail(receivers);
        AttachmentRepo attachmentRepo = AttachmentRepo.getInstance();
        for(int i = 0; i < receiversData.size(); i++){
            User receiverUser = UserBase.getInstance().getUser(receivers.get(i));
            Mail newEmail = new Mail(sender.getEmail(), receiverUser.getEmail(), subject, body, new Date(), priority);
            List<Map<String, Object>> filesList = new ArrayList<>();
            for(MultipartFile file:files){
                String name = file.getOriginalFilename();
                String type = file.getContentType();
                byte[] bytes = file.getBytes();
                Map<String, Object> att = new HashMap<>();
                att.put("name", name);
                att.put("type", type);
                att.put("bytes", bytes);
                filesList.add(att);
            }
            attachmentRepo.addAttachment(new Attachment(filesList), newEmail.getId());
            senderData.getSent().addMail(newEmail.submit());
            receiversData.get(i).getInbox().addMail(newEmail.submit());
        }
    }

    public List<Map<String, Object>> getAttachment(int token, int id) throws IOException {
        if(!UserBase.getInstance().containsLoggedUser(token))
            throw new RuntimeException("User is not logged in");
        AttachmentRepo attachmentRepo = AttachmentRepo.getInstance();
        Attachment att = attachmentRepo.getAttachment(id);
        List<Map<String, Object>> files = att.getFiles();
//        Map<String, Object> response = new HashMap<>();
//        List<Map<String, Object>> attachments = new ArrayList<>();
//
//        for(MultipartFile file : files){
//            Map<String, Object> attObject = new HashMap<>();
//            byte[] bytes = file.getBytes();
//            attObject.put("name", file.getOriginalFilename());
//            attObject.put("type", file.getContentType());
//            attObject.put("bytes", bytes);
//            System.out.println(Arrays.toString(bytes));
//            attachments.add(attObject);
//        }
        //response.put("attachments", attachments);
        return files;
    }
}
