package com.social.socialmediappli.utilitymethod;

import com.social.socialmediappli.dto.UserDto;
import com.social.socialmediappli.entity.Influencer;
import com.social.socialmediappli.entity.PlatForm;
import com.social.socialmediappli.entity.User;
import com.social.socialmediappli.exceptions.NotFoundException;
import com.social.socialmediappli.repository.InfluencerRepo;
import com.social.socialmediappli.repository.PlatformRepo;
import com.social.socialmediappli.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class UtilityMethods {

    private UserRepo userRepo;
    @Autowired
    public UtilityMethods(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Autowired
    private PlatformRepo platformRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private InfluencerRepo influencerRepo;

    // no. of follower increase by 1
    public int increseCountFollower(int count){
        count++;
        return count;
    }

    // no. of follower decrease by 1
    public int decreaseCountFollower(int count){
        count--;
        return count;
    }

    //  check user present on specific platform or not
    public boolean checkUserPresent(int userId ,String platformName) {

        return userRepo.findById(userId)
                .map(user -> platformRepo.getAllPlatFormByUserId(user.getUserId())
                        .stream()
                        .anyMatch(platform -> platform.getPlatformName().equals(platformName)))
                .orElse(false);
    }
    //  check influencer present on specific platform or not
    public boolean checkInfluencerPresent(int influencerId ,String platformName){
        // If the infu is not present or the platform is not associated, return false

        return influencerRepo.findById(influencerId)
                .map(influencer -> platformRepo.getAllPlatFormByInfluencerId(influencer.getInfluencerId())
                        .stream()
                        .anyMatch(platform -> platform.getPlatformName().equals(platformName)))
                .orElse(false);
    }

    // check user already follow influencer on same platform
    public boolean checkUserAlreadyFollowInfluencerOnSpecificPlatform(int userId, int influencerId, String platformName){
        if (checkInfluencerPresent(influencerId,platformName) && checkUserPresent(userId , platformName)){
            // Retrieve User and Influencer entities
            Optional<User> userOptional = userRepo.findById(userId);
            Optional<Influencer> influencerOptional = influencerRepo.findById(influencerId);

            if (userOptional.isPresent() && influencerOptional.isPresent()) {
                User user = userOptional.get();
                Influencer influencer = influencerOptional.get();

                switch (platformName) {
                    case "Facebook":
                        return user.getInfluencerListFb().contains(influencer);
                    case "Instagram":
                        return user.getInfluencerListIg().contains(influencer);
                    case "Twitter":
                        return user.getInfluencerListTw().contains(influencer);
                    case "Yahoo":
                        return user.getInfluencerListYh().contains(influencer);
                    default:
                        return false;
                }
            }
        }
        return false;
    }

    // return influencer follower list

//    public List<UserDto> followersList(int influencerId, String platformName) {
//        // Retrieve the influencer from the database
//        Optional<Influencer> influencerOpt = influencerRepo.findById(influencerId);
//
//        if (influencerOpt.isEmpty()) {
//            throw new NotFoundException("Influencer not found with ID: " + influencerId);
//        }
//
//        Influencer influencer = influencerOpt.get();
//
//        // Retrieve the list of users following the influencer on the specified platform
//        List<User> followers;
//        switch (platformName.toLowerCase()) {
//            case "facebook":
//                followers = userRepo.findByInfluencerFbContaining(influencer);
//                break;
//            case "instagram":
//                followers = userRepo.findByInfluencerIgContaining(influencer);
//                break;
//            case "twitter":
//                followers = userRepo.findByInfluencerTwContaining(influencer);
//                break;
//            case "yaaho":
//                followers = userRepo.findByInfluencerYhContaining(influencer);
//                break;
//            default:
//                throw new NotFoundException("Platform Not present: " + platformName);
//        }
//
//        List<UserDto> followerList = new ArrayList<>();
//        for (User u : followers) {
//            UserDto dto = modelMapper.map(u, UserDto.class);
//            followerList.add(dto);
//        }
//
//        if (followerList.isEmpty()) {
//            throw new NotFoundException("no followers present");
//        } else {
//            return followerList;
//        }
//
//    }
}
