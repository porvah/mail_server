package com.porvah.mailserver.models;

import java.util.ArrayList;
import java.util.List;

public class UserBase {
    private List<User> users;
    UserBase(){
        this.users = new ArrayList<User>();
    }
    void addUser(User user){
        this.users.add(user);
    }
    void deleteUser(String email){
        for(int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getEmail().equals(email))
                this.users.remove(i);
        }
    }
    User getUser(String email){
        for(int i = 0; i < this.users.size(); i++){
            if(this.users.get(i).getEmail().equals(email)){
                return this.users.get(i);
            }
        }
        throw new RuntimeException();
    }

}
