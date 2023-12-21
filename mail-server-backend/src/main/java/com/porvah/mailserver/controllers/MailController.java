package com.porvah.mailserver.controllers;

import com.porvah.mailserver.enums.SortType;
import com.porvah.mailserver.interfaces.ROMail;
import com.porvah.mailserver.models.*;
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
    @PostMapping("/signup")
    public Boolean signUp(@RequestBody Map<String, Object> body){

        String email = (String) body.get("email");
        String password = (String) body.get("password");
        String name = (String) body.get("name");
            return verificationProxy.signUpUser(name, email, password);
    }
    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody Map<String, Object> body) {
        String email = (String) body.get("email");
        String password = (String) body.get("password");
        try {
            int userId = verificationProxy.loginUser(email, password);
            return ResponseEntity.ok(userId);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to login.");
        }
    }
    @PostMapping("/logout")
    public Boolean logOut(@RequestBody Map<String, Object> body){
        int id = (int) body.get("id");
        verificationProxy.logoutUser(id);
        return true;
    }
    @GetMapping("/getuser/{id}")
    public User getLoggedUser(@PathVariable int id) {
        return UserBase.getInstance().getLoggedUser(id);
    }
    @GetMapping("/inbox/")
    public List<ROMail> getInbox(@RequestParam("token") int token, @RequestParam("sort") int sort){
        return strategy.getInbox(token, SortType.values()[sort]);
    }
    @GetMapping("/trash/")
    public List<ROMail> getTrash(@RequestParam("token") int token, @RequestParam("sort") int sort){
        return strategy.getTrash(token, SortType.values()[sort]);
    }
    @GetMapping("/sent/")
    public List<ROMail> getSent(@RequestParam("token") int token, @RequestParam("sort") int sort){
        return strategy.getSent(token, SortType.values()[sort]);
    }
    @GetMapping("/draft/")
    public List<Mail> getDraft(@RequestParam("token") int token, @RequestParam("sort") int sort){
        return strategy.getDraft(token, SortType.values()[sort]);
    }
    @GetMapping("/folders/")
    public List<MailFolder> getMailFolders(@RequestParam("token") int token){
        return strategy.getFolders(token);
    }
    @GetMapping("/folderemails/")
    public List<ROMail> getFolderEmails(@RequestParam("token") int token, @RequestParam("foldername") String foldername, @RequestParam("sort") int sort){
        return strategy.getFolderEmails(token, foldername, sort);
    }
    @PostMapping("/sendemail")
    public ResponseEntity<?> sendEmail(@RequestBody Map<String, Object> body){
        try {
            int token = (int) body.get("token");
            List<String> receiverEmails = (List<String>) body.get("receiver");
            String subject = (String) body.get("subject");
            String discription = (String) body.get("body");
            int priority = (int) body.get("priority");
            strategy.sendEmail(token, receiverEmails, subject, discription, priority);
            return ResponseEntity.ok().body("Email Sent Successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }



}
