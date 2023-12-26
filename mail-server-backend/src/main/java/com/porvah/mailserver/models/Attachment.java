package com.porvah.mailserver.models;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class Attachment {


    private List<MultipartFile> files;

    public Attachment(List<MultipartFile> files){
        this.files = files;
    }

    public List<MultipartFile> getFiles(){
        return files;
    }
}
