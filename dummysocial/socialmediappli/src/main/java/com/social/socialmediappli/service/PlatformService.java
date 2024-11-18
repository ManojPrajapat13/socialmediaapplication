package com.social.socialmediappli.service;

import com.social.socialmediappli.entity.PlatForm;
import com.social.socialmediappli.repository.PlatformRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformService {

    private PlatformRepo platformRepo;

    @Autowired
    public PlatformService(PlatformRepo platformRepo){
        this.platformRepo=platformRepo;
    }



}
