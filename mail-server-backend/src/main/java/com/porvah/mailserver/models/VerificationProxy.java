package com.porvah.mailserver.models;


public class VerificationProxy {

    private UserBase userBase;
    private int curId = 0;

    public VerificationProxy() {
        this.userBase = new UserBase();
    }

    public boolean signUpUser(String name, String email, String password) {

        if(userBase.containsUser(email)){

            User user = userBase.getUser(email);
            if (user.getPassword().equals(password)) {
                userBase.addLoggedUser(curId++, user);
                return true;
            } else {
                return false;
            }
        }

        User user = new User(email, password, name);
        userBase.addUser(user);
        return true;

    }

    public int loginUser(String email, String password) {


        if(!userBase.containsUser(email)){
            throw new RuntimeException("Could not find user with email: " + email + " in UserBase");
        }


        User user = userBase.getUser(email);
        if (user.getPassword().equals(password)) {
            userBase.addLoggedUser(curId, user);
            return curId++;
        } else {
            throw new RuntimeException("Wrong password");
        }

    }

    public void logoutUser(int userId) {
        userBase.removeLoggedUser(userId);
    }

    public User getLoggedUser(int userId) {
        return userBase.getLoggedUser(userId);
    }

}