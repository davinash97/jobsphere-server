package com.portal.jobsphere.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portal.jobsphere.model.Post;

public interface PostRepository extends JpaRepository<Post, UUID> {

}