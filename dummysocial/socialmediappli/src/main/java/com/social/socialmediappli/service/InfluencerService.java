package com.social.socialmediappli.service;

import com.social.socialmediappli.dto.InfluencerDto;
import com.social.socialmediappli.entity.Influencer;
import com.social.socialmediappli.entity.PlatForm;
import com.social.socialmediappli.entity.User;
import com.social.socialmediappli.repository.InfluencerRepo;
import com.social.socialmediappli.repository.PlatformRepo;
import com.social.socialmediappli.utilitymethod.UtilityMethods;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

public interface InfluencerService {

    String registerInfluencer(InfluencerDto influencerDto);

    InfluencerDto getOneInfluencer(int influencerId);

    List<InfluencerDto> getAllInfluencer();

    String deleteOneInfluencer(int influencerId , String platformName);

    Set<PlatForm> getAllPlatformName();








}
