package springboot.angular.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springboot.angular.models.User;
import springboot.angular.services.IUserService;

import java.util.HashMap;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/api/users/register")
    public ResponseEntity registerUser(@RequestBody User user) {

        return userService.userRegister(user);
    }

    @PostMapping("/api/users/login")
    public ResponseEntity loginUser(@RequestBody HashMap<String, Object> credentials) {

        return userService.userLogin(credentials);
    }


}
