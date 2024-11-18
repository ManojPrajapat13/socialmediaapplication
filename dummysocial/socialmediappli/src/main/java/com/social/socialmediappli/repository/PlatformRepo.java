package com.social.socialmediappli.repository;

import com.social.socialmediappli.entity.PlatForm;
import com.social.socialmediappli.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlatformRepo extends CrudRepository<PlatForm, Integer> {

    public PlatForm findByPlatformName(String platformName);

    @Query("select p from PlatForm p join p.users u where u.userId = :userId")  // JPQL
    List<PlatForm> getAllPlatFormByUserId(int userId);

    @Query("select p from PlatForm p join p.influencers i where i.influencerId = :influencerId")
    List<PlatForm> getAllPlatFormByInfluencerId(int influencerId);



}
