package com.social.socialmediappli.entity;

import com.fasterxml.jackson.annotation.*;
import com.social.socialmediappli.repository.InfluencerRepo;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "influencer_table")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "influencerId")
public class Influencer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "influencer_Id")
    private int influencerId;

    @Column(name = "InfluencerName", nullable = false)
    private String influencerName;

    @Column(name = "InfluencerEamil" , nullable = false , unique = true)
    private String influencerEmail;

    @Column(name = "InfluencerAge", nullable = false)
    private int influencerAge;

    @Column(name = "InfluencerMobileNo", nullable = false)
    private String influencerMobileNo;

    @Column(name = "facebookFollower" )
    private int facebookFollower;

    @Column(name = "instagramFollower" )
    private int instagramFollower;

    @Column(name = "YahooFollower" )
    private int YahooFollower;

    @Column(name = "twitterFollower" )
    private int twitterFollower;

    @Column(name = "category" ,nullable = false)
    private String category;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {
            CascadeType.PERSIST,CascadeType.MERGE
    })
    @JoinTable(
            name = "influencer_platform",
            joinColumns = @JoinColumn(name = "influencer_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private Set<PlatForm> platFormNamess = new HashSet<>();

    @ManyToMany(mappedBy = "influencerListFb", cascade = {
            CascadeType.MERGE,CascadeType.PERSIST
    })
    private Set<User> userListFb = new HashSet<>();

    @ManyToMany(mappedBy = "influencerListIg", cascade = {
            CascadeType.MERGE,CascadeType.PERSIST
    })
    private Set<User> userListIg = new HashSet<>();

    @ManyToMany(mappedBy = "influencerListTw", cascade = {
            CascadeType.MERGE,CascadeType.PERSIST
    })
    private Set<User> userListTw = new HashSet<>();

    @ManyToMany(mappedBy = "influencerListYh", cascade = {
            CascadeType.MERGE,CascadeType.PERSIST
    })
    private Set<User> userListYh = new HashSet<>();

}
