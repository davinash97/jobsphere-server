package com.portal.jobsphere.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.portal.jobsphere.model.Post;
import com.portal.jobsphere.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
	@Query("SELECT p FROM Post p WHERE p.profile_id = :profile_id")
	List<Post> getAllPostsByProfileId(@Param("profile_id") UUID profile_id);
}
