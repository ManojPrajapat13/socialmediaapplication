package com.social.socialmediappli.dto;

import com.social.socialmediappli.entity.PlatForm;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private int userId;

    private String userName;

    private String userEmail;

    private int userAge;

    private String userMobileNo;

    private Set<PlatForm> platFormNames = new HashSet<>();



}
