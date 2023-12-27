
package com.porvah.mailserver.models;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttachmentRepo {

    private static AttachmentRepo instance;
    private Map<Integer, Attachment> attachments;



    private AttachmentRepo(){
        this.attachments = new HashMap<>();
    }

    public static AttachmentRepo getInstance(){
        if(instance == null){
            instance = new AttachmentRepo();
        }
        return instance;
    }

    public void addAttachment(Attachment attachment, int emailId){
        attachments.put(emailId, attachment);
    }

    public Attachment getAttachment(int emailId){
        return attachments.get(emailId);
    }



}



