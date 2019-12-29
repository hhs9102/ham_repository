package me.ham.user;

import me.ham.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/users/create")
    public  User create(@RequestBody User user, HttpServletRequest httpServletRequest){
        userService.createUser(user);
        return user;
    }
}
