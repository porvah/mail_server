package com.porvah.mailserver.controllers;

import com.porvah.mailserver.models.User;
import com.porvah.mailserver.models.UserBase;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
@CrossOrigin()
public class MailController {

    @PostMapping("/signup")
    public Boolean signup(@RequestBody Map<String, Object> body){

        String email = (String) body.get("email");
        String password = (String) body.get("password");

        User user = new User(email, password);
        UserBase.getInstance().addUser(user);

        return true;
    }


}
