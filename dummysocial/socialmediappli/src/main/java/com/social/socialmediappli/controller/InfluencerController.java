package com.social.socialmediappli.controller;

import com.social.socialmediappli.dto.InfluencerDto;
import com.social.socialmediappli.entity.Influencer;
import com.social.socialmediappli.entity.User;
import com.social.socialmediappli.service.InfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/socialmedia/influencer")
public class InfluencerController {

    private InfluencerService influencerService;
    @Autowired
    public InfluencerController(InfluencerService influencerService){
        this.influencerService = influencerService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody InfluencerDto influencerDto){
        return influencerService.registerInfluencer(influencerDto);
    }

    @GetMapping("/getOneInfluencer/{influencerId}")
    public ResponseEntity<?> getOneInfluencer(@PathVariable int influencerId){
        InfluencerDto influencerDto = influencerService.getOneInfluencer(influencerId);

        if(influencerDto !=null){
            return ResponseEntity.ok(influencerDto);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("influencer is not found...");
        }
    }

    @GetMapping("/getAllInfluencer")
    public List<InfluencerDto> getAllInfluencerRepo(){
        return influencerService.getAllInfluencer();
    }

    @DeleteMapping("/deleteOneInfluencer/{influencerId}/{platformName}")
    public String deleteOneInfluencer(@PathVariable int influencerId ,@PathVariable String platformName){
        return influencerService.deleteOneInfluencer(influencerId,platformName);
    }
}
