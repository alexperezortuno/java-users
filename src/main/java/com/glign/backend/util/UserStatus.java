package com.glign.backend.util;

public enum UserStatus {
    ACTIVE,
    INACTIVE,
    PENDING;

    public static UserStatus fromString(String status) {
        for (UserStatus userStatus : UserStatus.values()) {
            if (userStatus.name().equalsIgnoreCase(status)) {
                return userStatus;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + status);
    }
}
