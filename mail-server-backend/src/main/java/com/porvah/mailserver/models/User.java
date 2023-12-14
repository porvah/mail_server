package com.porvah.mailserver.models;

public class User {
    private String email;
    private String password;



    User(String email, String password){
        this.email = email;
        this.password = password;
    }
    String getEmail(){
        return this.email;
    }
    String getPassword(){
        return this.password;
    }
    void setEmail(String email){
        this.email = email;
    }
    void setPassword(String password){
        this.password = password;
    }

}
