package com.porvah.mailserver.models;

import java.util.ArrayList;
import java.util.List;

public class UserBase {
    private List<User> users;
    private List<User> loggedUsers;

    public UserBase(){
        this.users = new ArrayList<User>();
        this.loggedUsers = new ArrayList<User>();
    }
    public void addUser(User user){
        this.users.add(user);
    }
    public void deleteUser(String email){
        for(int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getEmail().equals(email))
                this.users.remove(i);
        }
    }
    public User getUser(String email){
        for(int i = 0; i < this.users.size(); i++){
            if(this.users.get(i).getEmail().equals(email)){
                return this.users.get(i);
            }
        }
        throw new RuntimeException();
    }

    public int logUser(String email){
        User logged = this.getUser(email);
        int id = this.loggedUsers.size();
        this.loggedUsers.add(logged);
        return id;
    }

    public User getLoggedUser(int id){
        return this.loggedUsers.get(id);
    }


}
