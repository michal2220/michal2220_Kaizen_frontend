package com.kaizen.frontend;

import com.kaizen.domain.User;
import com.kaizen.service.dbService.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private final UserDbService userDbService;

    public UserService(UserDbService userDbService) {
        this.userDbService = userDbService;
    }


    public List<User> getUserList() {
        return userDbService.getAllUsers();
    }

    public void save(User user){
        userDbService.saveUser(user);
    }

    public void delete(User user){
        userDbService.deleteUserById(user.getUserId());
    }
}
