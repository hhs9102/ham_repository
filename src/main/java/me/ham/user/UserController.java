package me.ham.user;

import me.ham.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> findAllUser(){
        return userService.findAllUser();
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping
    public  User create(@RequestBody User user) throws IOException {
        userService.createUser(user);
        return user;
    }
    @PostMapping("/never")
    public  User createUserNever(@RequestBody User user){
        userService.createUserNever(user);
        return user;
    }
    @PostMapping("/readonly")
    public  User createUserReadOnly(@RequestBody User user){
        userService.createUserReadOnly(user);
        return user;
    }
    @PostMapping("/checked")
    public  User createUserCheckedException(@RequestBody User user) throws IOException {
        userService.createUserCheckedException(user);
        return user;
    }
    @PostMapping("/runtime")
    public  User createUserRuntimeException(@RequestBody User user){
        userService.createUserRuntimeException(user);
        return user;
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Integer id){
        return userService.findUserById(id);
    }

    @GetMapping("/name/{username}")
    public List<User> findUserById(@PathVariable String username){
        return userService.findUserByUsername(username);
    }

    @GetMapping("/file")
    public List<User> findUserFromTextFile(){
        return userService.findUserFromTextFile();
    }
}
