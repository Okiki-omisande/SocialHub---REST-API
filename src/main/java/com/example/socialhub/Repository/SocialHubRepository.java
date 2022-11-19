package com.example.socialhub.Repository;

import com.example.socialhub.Entity.SocialHuber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialHubRepository extends JpaRepository<SocialHuber, Long> {
}
