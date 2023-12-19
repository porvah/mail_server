package com.porvah.mailserver.models;

public class UserFacade {
    public UserData getUserDataByToken(int token){
        UserBase base = UserBase.getInstance();
        UserDataRepository repo = UserDataRepository.getInstance();
        User user = base.getLoggedUser(token);
        return repo.getUserData(user.getUserDataId());
    }
    public UserData getUserDataByEmail(String email){
        UserBase base = UserBase.getInstance();
        UserDataRepository repo = UserDataRepository.getInstance();
        User user = base.getUser(email);
        return repo.getUserData(user.getUserDataId());
    }
}
