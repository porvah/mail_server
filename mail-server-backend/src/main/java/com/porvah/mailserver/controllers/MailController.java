package com.porvah.mailserver.controllers;

import com.porvah.mailserver.enums.SortType;
import com.porvah.mailserver.interfaces.ROMail;
import com.porvah.mailserver.models.MailStrategy;
import com.porvah.mailserver.models.User;
import com.porvah.mailserver.models.UserBase;
import com.porvah.mailserver.models.VerificationProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@CrossOrigin()
public class MailController {

    final VerificationProxy verificationProxy = new VerificationProxy();

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

        User user = UserBase.getInstance().getLoggedUser(id);
        System.out.println(user.getEmail());
        return user;
    }
    @GetMapping("/inbox/")
    public List<ROMail> getInbox(@RequestParam("token") int token, @RequestParam("sort") int sort){
        MailStrategy strategy = new MailStrategy();
        return strategy.getInbox(token, SortType.values()[sort]);
    }
    @GetMapping("/trash/")
    public List<ROMail> getTrash(@RequestParam("token") int token, @RequestParam("sort") int sort){
        MailStrategy strategy = new MailStrategy();
        return strategy.getTrash(token, SortType.values()[sort]);
    }
    @GetMapping("/sent/")
    public List<ROMail> getSent(@RequestParam("token") int token, @RequestParam("sort") int sort){
        MailStrategy strategy = new MailStrategy();
        return strategy.getSent(token, SortType.values()[sort]);
    }
    @GetMapping("/draft/")
    public List<ROMail> getDraft(@RequestParam("token") int token, @RequestParam("sort") int sort){
        MailStrategy strategy = new MailStrategy();
        return strategy.getSent(token, SortType.values()[sort]);
    }
}
