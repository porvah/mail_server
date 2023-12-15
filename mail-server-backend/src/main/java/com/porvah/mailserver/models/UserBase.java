package com.porvah.mailserver.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserBase {

    //Singleton
    private static UserBase instance;

    private List<User> users;
    private Map<Integer, User> loggedUsers;

    private UserBase() {
        this.users = new ArrayList<>();
        this.loggedUsers = new HashMap<>();
    }

    public static UserBase getInstance() {
        if (instance == null) {
            instance = new UserBase();
        }
        return instance;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUser(String email) {


        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        throw new RuntimeException("Could not find user with email: " + email + " in database");
    }

    public void addLoggedUser(int userId, User user) {
        loggedUsers.put(userId, user);
    }



    public User getLoggedUser(int userId) {
        return loggedUsers.get(userId);
    }

    public void removeLoggedUser(int userId){
        loggedUsers.remove(userId);
    }



    public boolean containsUser(String email){
        for(User user : users){
            if(user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public boolean containsLoggedUser(int userId){
        return loggedUsers.containsKey(userId);
    }

}
