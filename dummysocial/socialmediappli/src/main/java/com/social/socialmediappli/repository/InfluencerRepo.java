package com.social.socialmediappli.repository;

import com.social.socialmediappli.entity.Influencer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfluencerRepo extends CrudRepository<Influencer, Integer> {

    Influencer findByInfluencerId(int influencerId);

}
