package com.portal.jobsphere.service;

import com.portal.jobsphere.enums.Gender;
import com.portal.jobsphere.enums.Role;
import com.portal.jobsphere.model.Profile;
import com.portal.jobsphere.repository.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileService {

	private static final Logger logger = LoggerFactory.getLogger(ProfileService.class);
	Profile profile;

	@Autowired
	private ProfileRepository profileRepository;

	public ProfileService() {
	}

	public Profile createProfile(String name, String gender, String role, String email, Long phone) {
		Role roleEnum = Role.valueOf(role.toUpperCase());
		Gender genderEnum = Gender.valueOf(gender.toUpperCase());
		profile = new Profile(name, genderEnum, roleEnum, email, phone);
		profileRepository.save(profile);
		return readProfile(profile.getProfileId());
	}

	public Profile readProfile(UUID profileId) {
		return profileRepository.findById(profileId).orElse(null);
	}

	public boolean updateProfile(UUID profileId, String name, String gender, String role, String email,
								 Long phone, Integer numOfPosts, Long numOfApplicants, String organizationName) {

		try {
			Profile existingProfile = readProfile(profileId);

			if (name != null)
				existingProfile.setName(name);

			if (gender != null) {
				Gender genderEnum = Gender.valueOf(gender.toUpperCase());
				existingProfile.setGender(genderEnum);
			}

			if (role != null) {
				Role roleEnum = Role.valueOf(role.toUpperCase());
				existingProfile.setRole(roleEnum);
			}

			if (email != null)
				existingProfile.setEmail(email);

			if (phone != 0)
				existingProfile.setPhone(phone);

			if (numOfPosts != 0)
				existingProfile.setNumOfPosts(numOfPosts);

			if (numOfApplicants != 0)
				existingProfile.setNumOfApplicants(numOfApplicants);

			if (organizationName != null)
				existingProfile.setOrganizationName(organizationName);

			profileRepository.save(existingProfile);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public boolean deleteProfile(UUID profileId) {
		if (!profileRepository.existsById(profileId))
			return false;
		profileRepository.deleteById(profileId);
		return true;
	}
}
