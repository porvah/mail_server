package com.porvah.mailserver.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserBase {

    //Singleton
    private static UserBase instance;

    private int curId = 0;
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
        this.users.add(user);
    }

    public void deleteUser(String email) {
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getEmail().equals(email))
                this.users.remove(i);
        }
    }

    public User getUser(String email) {
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getEmail().equals(email)) {
                return this.users.get(i);
            }
        }
        throw new RuntimeException("Couldn't find user with email: " + email + " in the user base.");
    }

    public int logUser(String email, String password){
        User user = this.getUser(email);
        if (user.getPassword().equals(password)) {
            this.loggedUsers.put(curId, user);
            return curId++;
        }
        return -1;
    }

    public void logoutUser(int id) {
        this.loggedUsers.remove(id);
    }
    public User getLoggedUser(int id) {
        return this.loggedUsers.get(id);

    }
}
