package com.abhinav.usersystem.repositories;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.abhinav.usersystem.entities.UserProfile;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryRepository implements IRepository<UserProfile> {

    private final Map<String, UserProfile> userProfileMap = new HashMap<>();
    private final Set<Long> phoneNumbers = new HashSet<>();
    private final Set<String> emailAddresses = new HashSet<>();

    @Override
    public Boolean findEmail(String emailAddress) {
        return emailAddresses.contains(emailAddress);
    }

    @Override
    public Boolean findPhone(Long phone) {
        return phoneNumbers.contains(phone);
    }

    @Override
    public UserProfile retrieve(String userId) {
        if (userProfileMap.containsKey(userId)) {
            return userProfileMap.get(userId);
        }
        return null;
    }

    @Override
    public void save(UserProfile userProfile) {
           userProfileMap.put(userProfile.getUserId(), userProfile);
           addToIndices(userProfile);
    }

    @Override
    public UserProfile update(UserProfile userProfile) {

        if (userProfileMap.containsKey(userProfile.getUserId())) {
            UserProfile oldProfile = userProfileMap.get(userProfile.getUserId());
            removeFromIndices(oldProfile);
        }

        userProfileMap.put(userProfile.getUserId(), userProfile);
        addToIndices(userProfile);
        return userProfileMap.get(userProfile.getUserId());
    }

    @Override
    public void delete(String userId) {
        UserProfile userProfile = retrieve(userId);
        userProfileMap.remove(userId);
        removeFromIndices(userProfile);

    }

    private void removeFromIndices(UserProfile userProfile) {
        if (Objects.nonNull(userProfile.getPhoneNumber()))
            phoneNumbers.remove(userProfile.getPhoneNumber());

        if (Objects.nonNull(userProfile.getEmailAddress()))
            emailAddresses.remove(userProfile.getEmailAddress());
    }

    private void addToIndices(UserProfile userProfile) {
        if (Objects.nonNull(userProfile.getPhoneNumber()))
            phoneNumbers.add(userProfile.getPhoneNumber());

        if (Objects.nonNull(userProfile.getEmailAddress()))
            emailAddresses.add(userProfile.getEmailAddress());
    }
}
