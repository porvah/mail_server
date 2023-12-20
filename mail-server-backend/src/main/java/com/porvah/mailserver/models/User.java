package com.porvah.mailserver.models;

import org.springframework.stereotype.Component;

public class User {
    private String name;
    private String email;
    private String password;

    private int userDataId;



    public User(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;

        userDataId = UserDataRepository.getInstance().addUserData(new UserData());
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getUserDataId(){
        return userDataId;
    }



}
