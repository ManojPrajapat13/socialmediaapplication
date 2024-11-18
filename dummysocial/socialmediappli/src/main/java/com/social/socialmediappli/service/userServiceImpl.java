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

@Service
public class userServiceImpl implements UserService {

    @Autowired
    private  UserRepo userRepo;

    @Autowired
    UtilityMethods method;

//    @Autowired
//    public UserService(UserRepo userRepo ){
//        this.userRepo = userRepo;
//    }

    @Autowired
    private PlatformRepo platformRepo;

    @Autowired
    private InfluencerRepo influencerRepo;

    @Autowired
    private ModelMapper modelMapper;

    // for user registration
    @Override
    public String registerUser(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);

        Set<PlatForm> platForms = new HashSet<>();
        for(PlatForm p : user.getPlatFormNames()){
            PlatForm existPlatform = platformRepo.findByPlatformName(p.getPlatformName());
            if(existPlatform != null){
                platForms.add(existPlatform);
            }else{
                platForms.add(p);
            }
        }
        user.setPlatFormNames(platForms);
        userRepo.save(user);
        return "user register successfully..";
    }

    // get one user
    @Override
    public UserDto getOneUser(int userId){
        Optional<User> user1 = userRepo.findById(userId);
//        User user;
        if(user1.isPresent()){
            User user = user1.get();
            UserDto userDto = modelMapper.map(user , UserDto.class);
            return userDto;
        }else{
            return null;
        }
    }

    // get all user
    @Override
    public List<UserDto> getAllUser(){
        List<User> userList = (List<User>) userRepo.findAll();

        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : userList ){
            UserDto userDto = modelMapper.map(user, UserDto.class);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    // delete one user
    @Override
    public String deleteOneUser(int userId, String platformName){
        User user = userRepo.findByUserId(userId);
        PlatForm platForm = platformRepo.findByPlatformName(platformName);

        if(method.checkUserPresent(userId,platformName)){
            // Remove the platform from the user's platform set
            user.getPlatFormNames().remove(platformName);
            // If bidirectional, also remove the user from the platform's user set
            platForm.getUsers().remove(user);
            // Save the user to reflect the removal
            userRepo.save(user);
//            platformRepo.save(platForm);

            return "User successfully delete from the platform " + platformName;
        }else{
            return "user is not present on this platform.....";
        }
    }

    // user follow influencer
    @Override
    public String userFollowInfluencer(int userId, int influencerId , String platformName){
        if (method.checkUserAlreadyFollowInfluencerOnSpecificPlatform(userId, influencerId, platformName)){
            return "User Already Follow Influencer On Specific Platform...";
        }
        if(method.checkUserPresent(userId , platformName) && method.checkInfluencerPresent(influencerId , platformName)) {
            Influencer influencer = influencerRepo.findByInfluencerId(influencerId);
            User user = userRepo.findByUserId(userId);
            // Increment the follower count based on the platform name
            switch (platformName) {
                case "Facebook":
                    influencer.setFacebookFollower(method.increseCountFollower(influencer.getFacebookFollower()));
                    influencer.getUserListFb().add(user);
                    user.getInfluencerListFb().add(influencer);
                    break;
                case "Instagram":
                    influencer.setInstagramFollower(method.increseCountFollower(influencer.getInstagramFollower()));
                    influencer.getUserListIg().add(user);
                    user.getInfluencerListIg().add(influencer);
                    break;
                case "Twitter":
                    influencer.setTwitterFollower(method.increseCountFollower(influencer.getTwitterFollower()));
                    influencer.getUserListTw().add(user);
                    user.getInfluencerListTw().add(influencer);
                    break;
                case "Yahoo":
                    influencer.setYahooFollower(method.increseCountFollower(influencer.getYahooFollower()));
                    influencer.getUserListYh().add(user);
                    user.getInfluencerListYh().add(influencer);
                    break;
                default:
                    return "Invalid platform name.";
            }
            userRepo.save(user);
            influencerRepo.save(influencer);
        } else {
            return  "user or influencer is not present on specific platform...";
        }
        return "follow successfully...";
    }

    // unfollow
    @Override
    public String userUnfollowInfluencer(int userId, int influencerId , String platformName){
        if (method.checkUserAlreadyFollowInfluencerOnSpecificPlatform(userId, influencerId, platformName)){
            Influencer influencer = influencerRepo.findByInfluencerId(influencerId);
            User user = userRepo.findByUserId(userId);
            switch (platformName) {
                case "Facebook":
                    influencer.setFacebookFollower(method.decreaseCountFollower(influencer.getFacebookFollower()));
                    user.getInfluencerListFb().remove(influencer);
                    influencer.getUserListFb().remove(user);
                    break;
                case "Instagram":
                    influencer.setInstagramFollower(method.decreaseCountFollower(influencer.getInstagramFollower()));
                    user.getInfluencerListIg().remove(influencer);
                    influencer.getUserListIg().remove(user);
                    break;
                case "Twitter":
                    influencer.setTwitterFollower(method.decreaseCountFollower(influencer.getTwitterFollower()));
                    user.getInfluencerListTw().remove(influencer);
                    influencer.getUserListTw().remove(user);
                    break;
                case "Yahoo":
                    influencer.setYahooFollower(method.decreaseCountFollower(influencer.getYahooFollower()));
                    user.getInfluencerListYh().remove(influencer);
                    influencer.getUserListYh().remove(user);
                    break;
                default:
                    return "Invalid platform name.";
            }
            userRepo.save(user);
            influencerRepo.save(influencer);
            return  "unfollow successfully...";
        }else{
            return "User not follow Influencer On Specific Platform...";
        }
    }
}
