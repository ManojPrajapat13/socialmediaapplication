package com.social.socialmediappli.controller;

import com.social.socialmediappli.dto.UserDto;
import com.social.socialmediappli.entity.User;
import com.social.socialmediappli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/socialmedia/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }

    @GetMapping("/getOneUser/{userId}")
    public ResponseEntity<?> getOneUser(@PathVariable int userId){
        UserDto userDto = userService.getOneUser(userId);
        if(userDto != null){
            return ResponseEntity.ok(userDto);  //
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found..");
        }
    }

    @GetMapping("/getAllUser")
    public List<UserDto> getAllUser(){
        return userService.getAllUser();
    }

    @DeleteMapping("/deleteOneUser/{userId}/{platformName}")
    public void  deleteOneUser(@PathVariable int userId, @PathVariable String platformName){
        userService.deleteOneUser(userId , platformName );
    }

    @PostMapping("/followInfluencer/{userId}/{influencerId}/{platformName}")
    public String userFollowInfluencer(@PathVariable int userId, @PathVariable int influencerId, @PathVariable String platformName){
        return userService.userFollowInfluencer(userId,influencerId,platformName);
    }

    @Transactional
    @PostMapping("/unfollowInfluencer/{userId}/{influencerId}/{platformName}")
    public String userUnfollowInfluencer(@PathVariable int userId,@PathVariable int influencerId ,@PathVariable String platformName){
        return userService.userUnfollowInfluencer(userId,influencerId,platformName);
    }

//    @GetMapping("")
//    public
}
