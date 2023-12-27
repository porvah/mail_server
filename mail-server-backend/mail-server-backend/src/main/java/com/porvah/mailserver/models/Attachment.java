package com.porvah.mailserver.models;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public class Attachment {


    private final List<Map<String, Object>> files;

    public Attachment(List<Map<String, Object>> files){
        this.files = files;
    }

    public List<Map<String, Object>> getFiles(){
        return files;
    }
}
