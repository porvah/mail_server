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
    public Boolean signUp(@RequestBody Map<String, Object> body){

        String email = (String) body.get("email");
        String password = (String) body.get("password");
        String name = (String) body.get("name");
        User user = new User(email, password, name);
        UserBase.getInstance().addUser(user);

        return true;
    }

    @PostMapping("/login")
    public int logIn(@RequestBody Map<String, Object> body){
        String email = (String) body.get("email");
        String password = (String) body.get("password");

        return UserBase.getInstance().logUser(email, password);
    }

    @PostMapping("/logout")
    public Boolean logOut(@RequestBody Map<String, Object> body){
        int id = (int) body.get("id");
        UserBase.getInstance().logoutUser(id);
        return true;
    }

}
