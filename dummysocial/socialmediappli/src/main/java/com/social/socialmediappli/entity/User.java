package com.social.socialmediappli.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@Entity
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "user_table")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name",nullable = false)
    private String userName;

    @Column(name = "user_mail",nullable = false, unique = true)
    private String userEmail;

    @Column(name = "user_age",nullable = false)
    private int userAge;

    @Column(name = "contact",nullable = false)
    private String userMobileNo;


    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_platform",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private Set<PlatForm> platFormNames = new HashSet<>();


    @ManyToMany( fetch = FetchType.LAZY , cascade = {
            CascadeType.MERGE,CascadeType.PERSIST
    })
    @JoinTable(
            name = "User_Influ_Follower_Fb",
            joinColumns = @JoinColumn(name = "user_Id", referencedColumnName = "user_Id"),
            inverseJoinColumns = @JoinColumn(name = "influencer_Id", referencedColumnName = "influencer_Id")
    )
    private Set<Influencer> influencerListFb = new HashSet<>();

    @ManyToMany( fetch = FetchType.LAZY , cascade = {
            CascadeType.MERGE,CascadeType.PERSIST
    })
    @JoinTable(
            name = "User_Influ_Follower_Ig",
            joinColumns = @JoinColumn(name = "user_Id", referencedColumnName = "user_Id"),
            inverseJoinColumns = @JoinColumn(name = "influencer_Id", referencedColumnName = "influencer_Id")
    )
    private Set<Influencer> influencerListIg = new HashSet<>();

    @ManyToMany( fetch = FetchType.LAZY , cascade = {
            CascadeType.MERGE,CascadeType.PERSIST
    })
    @JoinTable(
            name = "User_Influ_Follower_Tw",
            joinColumns = @JoinColumn(name = "user_Id", referencedColumnName = "user_Id"),
            inverseJoinColumns = @JoinColumn(name = "influencer_Id", referencedColumnName = "influencer_Id")
    )
    private Set<Influencer> influencerListTw = new HashSet<>();

    @ManyToMany( fetch = FetchType.LAZY , cascade = {
            CascadeType.MERGE,CascadeType.PERSIST
    })
    @JoinTable(
            name = "User_Influ_Follower_Yh",
            joinColumns = @JoinColumn(name = "user_Id", referencedColumnName = "user_Id"),
            inverseJoinColumns = @JoinColumn(name = "influencer_Id", referencedColumnName = "influencer_Id")
    )
    private Set<Influencer> influencerListYh = new HashSet<>();

}