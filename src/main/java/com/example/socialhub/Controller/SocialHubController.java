package com.example.socialhub.Controller;

import com.example.socialhub.Entity.SocialHuber;
import com.example.socialhub.Exception.ResourceNotFoundException;
import com.example.socialhub.Repository.SocialHubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SocialHubController {
   @Autowired
    private SocialHubRepository socialHubRepository;

    @GetMapping("/socialHuber")
    public List<SocialHuber> getAllSocialHuber() {
        return socialHubRepository.findAll();
    }

    @PostMapping("/socialHuber")
    private ResponseEntity<SocialHuber> addSocialHuber(@Validated @RequestBody SocialHuber socialHuber) {
        SocialHuber addedSocialHuber = socialHubRepository.save(socialHuber);
        return ResponseEntity.ok().body(addedSocialHuber);
    }

    @GetMapping("/socialHuber/{id}")
    private ResponseEntity<SocialHuber> getSocialHuberById(@PathVariable(value = "id") Long socialHuber)  throws ResourceNotFoundException {
      SocialHuber socialHuberRequested = socialHubRepository.findById(socialHuber).orElseThrow(() -> new ResourceNotFoundException("socialHub user not found for id: " +socialHuber));
      return ResponseEntity.ok().body(socialHuberRequested);
    }

    @PutMapping("/socialHuber/{id}")
    private ResponseEntity<SocialHuber> updateSocialHuber(@PathVariable(value = "id") Long socialHuberId, @RequestBody SocialHuber socialHuber) throws ResourceNotFoundException {
        SocialHuber socialHuberRequested = socialHubRepository.findById(socialHuberId).orElseThrow(() -> new ResourceNotFoundException("socialHub user not found for id: " +socialHuberId));
        socialHuberRequested.setUsername(socialHuber.getUsername());
        socialHuberRequested.setEmail(socialHuber.getEmail());
        socialHuberRequested.setGender(socialHuber.getGender());
        socialHuberRequested.setLocation(socialHuber.getLocation());
        return ResponseEntity.ok().body(socialHuber);
    }

    @DeleteMapping("/socialHuber/{id}")
    public void deleteSocialHuber(@PathVariable(value = "id") Long socialHuberId) throws ResourceNotFoundException {
        SocialHuber socialHuberRequested = socialHubRepository.findById(socialHuberId).orElseThrow(() -> new ResourceNotFoundException("socialHub user not found for id: " +socialHuberId));
        socialHubRepository.delete(socialHuberRequested);
    }

}
