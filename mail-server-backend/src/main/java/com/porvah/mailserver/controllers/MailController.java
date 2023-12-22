package com.porvah.mailserver.controllers;

import com.porvah.mailserver.enums.SortType;
import com.porvah.mailserver.interfaces.ROMail;
import com.porvah.mailserver.models.*;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
@CrossOrigin()
public class MailController {

    final VerificationProxy verificationProxy = new VerificationProxy();
    final MailStrategy strategy = new MailStrategy();
    @PostMapping("signup")
    public ResponseEntity<?> signUp(@RequestBody Map<String, Object> body){
        try {
            String email = (String) body.get("email");
            String password = (String) body.get("password");
            String name = (String) body.get("name");
            verificationProxy.signUpUser(name, email, password);
            return ResponseEntity.ok("{\"mgs\" : \"Account created successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("{\"mgs\" : \"Account is already created\"}");
        }
    }
    @PostMapping("login")
    public ResponseEntity<?> logIn(@RequestBody Map<String, Object> body) {
        String email = (String) body.get("email");
        String password = (String) body.get("password");
        try {
            int userId = verificationProxy.loginUser(email, password);
            return ResponseEntity.ok(userId);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"mgs\" : \"Failed to login.\"}");
        }
    }
    @PostMapping("logout")
    public ResponseEntity<?> logOut(@RequestBody Map<String, Object> body){
        try {
            int id = (int) body.get("id");
            verificationProxy.logoutUser(id);
            return ResponseEntity.ok("{\"mgs\" : \"Logout is successful\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("{\"mgs\" : \"Account is already logged out\"}");
        }
    }
    @GetMapping("getuser")
    public ResponseEntity<?> getLoggedUser(@RequestParam("id") int id) {
        try {
            return ResponseEntity.ok(UserBase.getInstance().getLoggedUser(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"mgs\" : \"User is not logged in\"}");
        }
    }
    @GetMapping("inbox")
    public ResponseEntity<?> getInbox(@RequestParam("token") int token, @RequestParam("sort") int sort){
        try {
            return ResponseEntity.ok(strategy.getInbox(token, SortType.values()[sort]));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"mgs\" : \"Token not authorized\"}");
        }
    }
    @GetMapping("trash")
    public ResponseEntity<?> getTrash(@RequestParam("token") int token, @RequestParam("sort") int sort){
        try {
            return ResponseEntity.ok(strategy.getTrash(token, SortType.values()[sort]));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"mgs\" : \"Token not authorized\"}");
        }
    }
    @GetMapping("sent")
    public ResponseEntity<?> getSent(@RequestParam("token") int token, @RequestParam("sort") int sort){
        try {
            return ResponseEntity.ok(strategy.getSent(token, SortType.values()[sort]));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"mgs\" : \"Token not authorized\"}");
        }
    }
    @GetMapping("draft")
    public ResponseEntity<?> getDraft(@RequestParam("token") int token, @RequestParam("sort") int sort){
        try {
            return ResponseEntity.ok(strategy.getDraft(token, SortType.values()[sort]));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"mgs\" : \"Token not authorized\"}");
        }
    }
    @GetMapping("folders")
    public ResponseEntity<?> getMailFolders(@RequestParam("token") int token){
        try {
            return ResponseEntity.ok(strategy.getFolders(token));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"mgs\" : \"Token not authorized\"}");
        }
    }
    @GetMapping("folderemails")
    public ResponseEntity<?> getFolderEmails(@RequestParam("token") int token, @RequestParam("foldername") String foldername, @RequestParam("sort") int sort){
        try {
            return ResponseEntity.ok(strategy.getFolderEmails(token, foldername, SortType.values()[sort]));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"mgs\" : \"Token not authorized\"}");
        }
    }
    @PostMapping("createfolder")
    public ResponseEntity<?> createFolder(@RequestBody Map<String, Object> body){
        try {
            int token = (int) body.get("token");
            String folderName = (String) body.get("foldername");
            strategy.createFolder(token, folderName);
            return ResponseEntity.ok("{\"mgs\" : \"Folder is created successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"mgs\" : \"Unauthorized token error\"}");
        }
    }
    @PostMapping("sendemail")
    public ResponseEntity<?> sendEmail(@RequestBody Map<String, Object> body){
        try {
            int token = (int) body.get("token");
            List<String> receiverEmails = (List<String>) body.get("receiver");
            String subject = (String) body.get("subject");
            String description = (String) body.get("body");
            int priority = (int) body.get("priority");
            strategy.sendEmail(token, receiverEmails, subject, description, priority);
            return ResponseEntity.ok().body("{\"mgs\" : \"Email Sent Successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"mgs\" : \"User not found\"}");
        }
    }
    @DeleteMapping("delete")
    public ResponseEntity<?> deleteEmail(@RequestBody Map<String, Object> body){
        try{
            int token = (int) body.get("token");
            List<Integer> id = (List<Integer>) body.get("id");
            strategy.deleteEmail(token, id);
            return ResponseEntity.ok().body("{\"mgs\" : \"Email deleted successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("{\"mgs\" : \"Unexpected error\"}");
        }

    }
    @PostMapping("draftemail")
    public ResponseEntity<?> draftEmail(@RequestBody Map<String, Object> body){
        try {
            int token = (int) body.get("token");
            String subject = (String) body.get("subject");
            String description = (String) body.get("body");
            int priority = (int) body.get("priority");
            strategy.draftEmail(token, subject, description, priority);
            return ResponseEntity.ok().body("{\"mgs\" : \"Email drafted successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("{\"mgs\" : \"Unexpected error\"}");
        }
    }
    @PutMapping("updatedraft")
    public ResponseEntity<?> updateDraft(@RequestBody Map<String, Object> body){
        try {
            int id = (int) body.get("id");
            int token = (int) body.get("token");
            String subject = (String) body.get("subject");
            String description = (String) body.get("body");
            int priority = (int) body.get("priority");
            strategy.updateDraft(id, token, subject, description, priority);
            return ResponseEntity.ok().body("{\"mgs\" : \"Email updated successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("{\"mgs\" : \"Unexpected error\"}");
        }
    }
    @PostMapping("submitdraft")
    public ResponseEntity<?> submitDraft(@RequestBody Map<String, Object> body){
        try {
            int id = (int) body.get("id");
            int token = (int) body.get("token");
            String subject = (String) body.get("subject");
            List<String> receiverEmails = (List<String>) body.get("receiver");
            String description = (String) body.get("body");
            int priority = (int) body.get("priority");
            strategy.submitDraft(id, token, receiverEmails,subject, description, priority);
            return ResponseEntity.ok().body("{\"mgs\" : \"Drafted Email submitted successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("{\"mgs\" : \"Unexpected error\"}");
        }
    }
    @PutMapping("movemails")
    public ResponseEntity<?>  moveMails(@RequestBody Map<String, Object> body){
        try{
            List<Integer> ids = (List<Integer>) body.get("id");
            int token = (int) body.get("token");
            String folderName = (String) body.get("foldername");
            strategy.moveMails(token, ids, folderName);
            return ResponseEntity.ok("{\"mgs\" : \"Mails were moved successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("{\"mgs\" : \"Unexpected error\"}");
        }
    }

}
