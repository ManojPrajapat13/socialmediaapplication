package com.social.socialmediappli.service;

import com.social.socialmediappli.dto.UserDto;
import com.social.socialmediappli.entity.Influencer;
import com.social.socialmediappli.entity.PlatForm;
import com.social.socialmediappli.entity.User;
import com.social.socialmediappli.repository.InfluencerRepo;
import com.social.socialmediappli.repository.PlatformRepo;
import com.social.socialmediappli.repository.UserRepo;
import com.social.socialmediappli.utilitymethod.UtilityMethods;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


public interface UserService {

    String registerUser(UserDto userDto);

    UserDto getOneUser(int userId);

    List<UserDto> getAllUser();

    String deleteOneUser(int userId, String platformName);

    String userFollowInfluencer(int userId, int influencerId , String platformName);

    String userUnfollowInfluencer(int userId, int influencerId , String platformName);











    // update user
//    public void updateUser()




}