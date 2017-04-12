package az.fuad.rest.database;

import az.fuad.rest.models.Message;
import az.fuad.rest.models.Profile;

import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {

    private static Map<Long, Message> messages = new HashMap<>();
    private static Map<String, Profile> profiles = new HashMap<>();

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }

}
