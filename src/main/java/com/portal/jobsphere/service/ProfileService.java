package com.portal.jobsphere.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.jobsphere.enums.Gender;
import com.portal.jobsphere.enums.Role;
import com.portal.jobsphere.model.Message;
import com.portal.jobsphere.model.Post;
import com.portal.jobsphere.model.Profile;
import com.portal.jobsphere.repository.ProfileRepository;

@Service
public class ProfileService {

	private static final Logger logger = LoggerFactory.getLogger(
		ProfileService.class
	);
	Profile profile;

	@Autowired
	private ProfileRepository profileRepository;

	public ProfileService() {}

	// CRUD Operations
	public Profile createProfile(
		String name,
		String gender,
		String role,
		String email,
		Long phone
	) {
		Gender genderEnum = null;
		Role roleEnum = null;
		if (gender != null) {
			genderEnum = Gender.valueOf(gender.toUpperCase());
		}

		if (role != null) {
			roleEnum = Role.valueOf(role.toUpperCase());
		}

		profile = new Profile(name, genderEnum, roleEnum, email, phone);
		profileRepository.save(profile);
		return readProfile(profile.getProfileId());
	}

	public Profile readProfile(UUID profile_id) {
		return profileRepository.findById(profile_id).orElse(null);
	}

	public boolean updateProfile(
		UUID profile_id,
		String name,
		String first_name,
		String last_name,
		String gender,
		String role,
		String email,
		Long phone,
		Integer num_of_posts,
		Long num_of_applicants,
		String organization
	) {
		try {
			Profile existingProfile = readProfile(profile_id);

			if (existingProfile == null) {
				return false;
			}

			if (name != null) {
				existingProfile.setName(name);
			}

			if (first_name != null) {
				existingProfile.setFirstName(first_name);
			}

			if (last_name != null) {
				existingProfile.setLastName(last_name);
			}

			if (gender != null) {
				Gender genderEnum = Gender.valueOf(gender.toUpperCase());
				existingProfile.setGender(genderEnum);
			}

			if (role != null) {
				Role roleEnum = Role.valueOf(role.toUpperCase());
				existingProfile.setRole(roleEnum);
			}

			if (email != null) {
				existingProfile.setEmail(email);
			}

			if (phone != 0) {
				existingProfile.setPhone(phone);
			}

			if (num_of_posts != 0) {
				existingProfile.setNumOfPosts(num_of_posts);
			}

			if (num_of_applicants != 0) {
				existingProfile.setNumOfApplicants(num_of_applicants);
			}

			if (organization != null) {
				existingProfile.setOrganizationName(organization);
			}

			profileRepository.save(existingProfile);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public boolean deleteProfile(UUID profile_id) {
		if (profileIdExists(profile_id)) {
			profileRepository.deleteById(profile_id);
			return true;
		}
		return false;
	}

	// Others
	public boolean profileIdExists(UUID profile_id) {
		return profileRepository.existsById(profile_id);
	}

	public List<Post> getAllPosts(UUID profile_id) {
		if (profileIdExists(profile_id)) {
			return profileRepository.getAllPostsByProfileId(profile_id);
		}
		return null;
	}

	public List<Message> getAllMessages(UUID profile_id) {
		if (profileIdExists(profile_id)) {
			return profileRepository.getAllMessagesByProfileId(profile_id);
		}
		return null;
	}
}
