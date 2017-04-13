package az.fuad.rest.services;

import az.fuad.rest.database.DatabaseClass;
import az.fuad.rest.exceptions.DataNotFoundException;
import az.fuad.rest.models.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileService {

    private Map<String, Profile> profiles = DatabaseClass.getProfiles();

    public ProfileService() {
        profiles.put("fd.huseynov", new Profile(1L, "fd.huseynov", "Fuad", "Huseynov"));
        profiles.put("sheva", new Profile(2L, "sheva", "Andrey", "Shevchenko"));
    }

    public List<Profile> getAllProfiles() {
        return new ArrayList<>(profiles.values());
    }

    public Profile getProfile(String profileName) {
        if (profiles.get(profileName) == null)
            throw new DataNotFoundException("Profile with profile name " + profileName + " does not exist!");
        return profiles.get(profileName);
    }

    public Profile addProfile(Profile profile) {
        profile.setId(profiles.size() + 1);
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        if (profile.getProfileName().isEmpty())
            return null;
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile deleteProfile(String profileName) {
        return profiles.remove(profileName);
    }

}
