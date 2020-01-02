package me.ham.user;

import me.ham.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/create")
    public  User create(@RequestBody User user) throws IOException {
        userService.createUser(user);
        return user;
    }
    @PostMapping("/create/never")
    public  User createUserNever(@RequestBody User user){
        userService.createUserNever(user);
        return user;
    }
    @PostMapping("/create/readonly")
    public  User createUserReadOnly(@RequestBody User user){
        userService.createUserReadOnly(user);
        return user;
    }
    @PostMapping("/create/jdbc")
    public  User createJdbc(@RequestBody User user){
        userService.createJdbcUser(user);
        return user;
    }
    @PostMapping("/create/jdbc/never")
    public  User createJdbcUserNever(@RequestBody User user){
        userService.createJdbcUserNever(user);
        return user;
    }
    @PostMapping("/create/jdbc/readonly")
    public  User createUserJdbcReadOnly(@RequestBody User user){
        userService.createJdbcUserReadOnly(user);
        return user;
    }
}
