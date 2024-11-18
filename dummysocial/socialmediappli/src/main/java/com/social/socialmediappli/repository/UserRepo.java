package com.social.socialmediappli.repository;

import com.social.socialmediappli.entity.Influencer;
import com.social.socialmediappli.entity.PlatForm;
import com.social.socialmediappli.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Integer> {

    User findByUserId(int userId);

//    List<User> findByInfluencerIgContaining(Influencer influencer);
//
//    List<User> findByInfluencerFbContaining(Influencer influencer);
//    List<User> findByInfluencerYhContaining(Influencer influencer);
//
//    List<User> findByInfluencerTwContaining(Influencer influencer);

}
