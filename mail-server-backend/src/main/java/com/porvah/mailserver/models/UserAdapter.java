package com.porvah.mailserver.models;

public class UserAdapter {
    public UserData getUserDataByToken(int token){
        UserBase base = UserBase.getInstance();
        UserDataRepository repo = UserDataRepository.getInstance();
        User user = base.getLoggedUser(token);
        return repo.getUserData(user.getUserDataId());
    }
}
