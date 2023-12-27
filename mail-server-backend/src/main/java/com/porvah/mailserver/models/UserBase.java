package com.porvah.mailserver.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserBase {

    //Singleton
    private static UserBase instance;

    private HashMap<String, User> users;
    private Map<Integer, User> loggedUsers;

    private UserBase() {
        this.users = new HashMap<>();
        this.loggedUsers = new HashMap<>();
    }

    public static UserBase getInstance() {
        if (instance == null) {
            instance = new UserBase();
        }
        return instance;
    }

    public void addUser(User user) {
        this.users.put(user.getEmail(), user);
    }

    public User getUser(String email) {

        if(this.containsUser(email)) {
            return this.users.get(email);
        }
        else
        throw new RuntimeException("Could not find user with email: " + email + " in database");
    }

    public void addLoggedUser(int userId, User user) {
        this.loggedUsers.put(userId, user);
    }



    public User getLoggedUser(int userId) {
        return loggedUsers.get(userId);
    }

    public void removeLoggedUser(int userId){
        this.loggedUsers.remove(userId);
    }



    public boolean containsUser(String email){
        if(this.users.containsKey(email)) {
            return true;
        }
        return false;
    }

    public boolean containsLoggedUser(int userId){
        return this.loggedUsers.containsKey(userId);
    }

}
