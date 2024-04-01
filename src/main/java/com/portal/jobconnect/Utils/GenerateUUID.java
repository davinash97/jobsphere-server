package com.portal.jobconnect.Utils;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class GenerateUUID {
    public static String generateId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}