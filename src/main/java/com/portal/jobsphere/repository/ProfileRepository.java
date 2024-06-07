package com.portal.jobsphere.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portal.jobsphere.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

}
