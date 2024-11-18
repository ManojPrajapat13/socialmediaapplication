package com.social.socialmediappli.dto;

import com.social.socialmediappli.entity.PlatForm;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class InfluencerDto {

    private int influencerId;

    private String influencerName;

    private String influencerEmail;

    private int influencerAge;

    private String influencerMobileNo;

    private String category;

    private int instagramFollower;

    private int facebookFollower;

    private int yahooFollower;

    private int twitterFollower;

    private Set<PlatForm> platFormNamess = new HashSet<>();
}
