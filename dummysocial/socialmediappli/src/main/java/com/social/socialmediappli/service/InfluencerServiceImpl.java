package com.social.socialmediappli.service;

import com.social.socialmediappli.dto.InfluencerDto;
import com.social.socialmediappli.entity.Influencer;
import com.social.socialmediappli.entity.PlatForm;
import com.social.socialmediappli.repository.InfluencerRepo;
import com.social.socialmediappli.repository.PlatformRepo;
import com.social.socialmediappli.utilitymethod.UtilityMethods;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InfluencerServiceImpl implements InfluencerService {

    private InfluencerRepo influencerRepo;

    @Autowired
    private PlatformRepo platformRepo;

    @Autowired
    private UtilityMethods utilityMethods;

//    @Autowired
//    public InfluencerService(InfluencerRepo influencerRepo){
//        this.influencerRepo = influencerRepo;
//    }
    @Autowired
    private ModelMapper modelMapper;

    // for influencer registration
    @Override
    public String registerInfluencer(InfluencerDto influencerDto){
        Influencer influencer = modelMapper.map(influencerDto, Influencer.class);
        // check platform name
        Set<PlatForm> platforms = new HashSet<>();
        for(PlatForm p : influencer.getPlatFormNamess()){
            PlatForm existPlatform = platformRepo.findByPlatformName(p.getPlatformName());
            if(existPlatform != null){
                platforms.add(existPlatform);
            }else {
                platforms.add(p);
            }
        }

        influencer.setPlatFormNamess(platforms);

        influencerRepo.save(influencer);
        return "influencer registration successfully..";
    }

    // get one influencer
    @Override
    public InfluencerDto getOneInfluencer(int influencerId){
        Optional<Influencer> influencer1 = influencerRepo.findById(influencerId);
        Influencer influencer;
        if(influencer1.isPresent()){
            influencer = influencer1.get();
            InfluencerDto influencerDto = modelMapper.map(influencer ,  InfluencerDto.class);
            return influencerDto;
        }
        else{
            return null;
        }
    }

    // get all influencer
    @Override
    public List<InfluencerDto> getAllInfluencer(){

        List<Influencer> influencerList= (List<Influencer>) influencerRepo.findAll();

        List<InfluencerDto> influencerDtoList = new ArrayList<>();
        for(Influencer influencer : influencerList){
            InfluencerDto influencerDto = modelMapper.map(influencer , InfluencerDto.class);
            influencerDtoList.add(influencerDto);
        }
        return influencerDtoList;
    }

    // delete one influencer
    @Override
    public String deleteOneInfluencer(int influencerId , String platformName){

        if (utilityMethods.checkInfluencerPresent(influencerId, platformName)){
            Influencer influencer = influencerRepo.findByInfluencerId(influencerId);
            PlatForm platForm = platformRepo.findByPlatformName(platformName);

            // Remove the platform from the user's platform set
            influencer.getPlatFormNamess().remove(platformName);

            // If bidirectional, also remove the user from the platform's user set
            platForm.getInfluencers().remove(influencer);

            // Save the influencer to reflect the removal
            influencerRepo.save(influencer);

            return "User successfully removed from the platform " + platformName;
        }else{
            return "influencer is not found on this platform.......";
        }
    }

    // get all platform names...
    @Override
    public Set<PlatForm> getAllPlatformName(){
        // Retrieve all PlatForm entries from the database
        List<PlatForm> allPlatforms = (List<PlatForm>) platformRepo.findAll();
        //  store in set for unique
        Set<PlatForm> platFormName = new HashSet<>(allPlatforms);
        return platFormName;
    }
}
