package com.porvah.mailserver.models;

import java.util.ArrayList;
import java.util.List;

public class UserFacade {
    public UserData getUserDataByToken(int token){
        UserBase base = UserBase.getInstance();
        UserDataRepository repo = UserDataRepository.getInstance();
        User user = base.getLoggedUser(token);
        return repo.getUserData(user.getUserDataId());
    }
    public List<UserData> getUserDataByEmail(List<String> emails){
        UserBase base = UserBase.getInstance();
        UserDataRepository repo = UserDataRepository.getInstance();
        List<UserData> userList = new ArrayList<UserData>();
        for(String email : emails){
            User user = base.getUser(email);
            userList.add(repo.getUserData(user.getUserDataId()));
        }
        return userList;
    }
}
