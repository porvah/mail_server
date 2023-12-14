package com.porvah.mailserver.models;

public class User {
    private String name;
    private String email;
    private String password;



    public User(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
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

}
