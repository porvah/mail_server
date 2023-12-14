package com.porvah.mailserver.controllers;

import com.porvah.mailserver.models.User;
import com.porvah.mailserver.models.UserBase;
import com.porvah.mailserver.models.VerificationProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Proxy;
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

        verificationProxy.signUpUser(name, email, password);
        return true;
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

@GetMapping("/getUser")
    public User getLoggedUser(int userId) {
        return UserBase.getInstance().getLoggedUser(userId);
    }

}
