package com.portal.jobconnect.service;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.portal.jobconnect.model.Profile;

@Component
public class ProfileService {

	HashMap<String, Object> profileMap = new HashMap<String, Object>();

	Profile profile;

	public ProfileService() {
	}

	public boolean createProfile(String profileId, String name, String accountType, String email, long phone) {
		if (profileMap.containsKey(profileId))
			return false;
		profile = new Profile(profileId, name, accountType, email, phone);
		profileMap.put(profileId, profile);
		return true;
	}

	public Profile readProfile(String profileId) {
		if (!profileMap.containsKey(profileId))
			return null;

		return (Profile) profileMap.get(profileId);
	}

	public boolean updateProfile(String profileId, String name, String accountType, String email, long phone,
			int numOfPosts, long numOfApplicants, String organizationName) {

		if (!profileMap.containsKey(profileId))
			return false;

		Profile existingProfile = readProfile(profileId);

		if (name != null)
			existingProfile.setName(name);
		else
			existingProfile.getName();

		if (accountType != null)
			existingProfile.setAccountType(accountType);
		else
			existingProfile.getAccountType();

		if (email != null)
			existingProfile.setEmail(email);
		else
			existingProfile.getEmail();

		if (phone != 0)
			existingProfile.setPhone(phone);
		else
			existingProfile.getPhone();

		if (numOfPosts != 0)
			existingProfile.setNumOfPosts(numOfPosts);
		else
			existingProfile.getNumOfPosts();

		if (numOfApplicants != 0)
			existingProfile.setNumOfApplicants(numOfApplicants);
		else
			existingProfile.getNumOfApplicants();

		if (organizationName.isEmpty())
			existingProfile.setOrganizationName(organizationName);
		else
			existingProfile.getOrganizationName();

		profileMap.put(profileId, existingProfile);

		return true;
	}

	public boolean deleteProfile(String profileId) {
		if (!profileMap.containsKey(profileId))
			return false;
		profileMap.remove(profileId);
		return true;
	}
}
