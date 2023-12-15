package com.porvah.mailserver.models;

import java.util.HashMap;
import java.util.Map;

public class UserDataRepository {

    private static UserDataRepository instance;

    private int dataIdCounter = 0;

    private Map<Integer, UserData> userDataByUserId;

    private UserDataRepository(){
        this.userDataByUserId = new HashMap<>();
    }


    public static UserDataRepository getInstance(){
        if(instance == null){
            instance = new UserDataRepository();
        }
        return instance;
    }

    public int addUserData(UserData userData){
        userDataByUserId.put(dataIdCounter, userData);
        return dataIdCounter++;
    }

    public UserData getUserData(int userId){
        return userDataByUserId.get(userId);
    }

    public void removeUserData(int userId){
        userDataByUserId.remove(userId);
    }








}
