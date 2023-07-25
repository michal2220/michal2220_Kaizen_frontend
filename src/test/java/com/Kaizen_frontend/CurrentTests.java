package com.Kaizen_frontend;

import com.Kaizen_frontend.frontend.domain.User;
import com.Kaizen_frontend.frontend.exception.UserNotFoundException;
import com.Kaizen_frontend.frontend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CurrentTests {

    @Autowired
    private UserService userService;


    @Test
    public void fetchingUserByLastname() throws UserNotFoundException {
        //Given
        User[] users = userService.findByLastname("Zbigniew");

        System.out.println(users[0].getLastname());


    }

}
