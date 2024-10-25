package com.rolfje.anonimatron.anonymizer;

import com.rolfje.anonimatron.synonyms.StringSynonym;
import com.rolfje.anonimatron.synonyms.Synonym;

import java.util.Random;

public class PhoneNumberAnonymizer implements Anonymizer {
    private static final Random random = new Random();

    @Override
    public Synonym anonymize(Object from, int size, boolean shortlived) {
        String originalPhoneNumber = (String) from;
        String anonymizedPhoneNumber = anonymizePhoneNumber(originalPhoneNumber);
        
        return new StringSynonym(
                getType(),
                originalPhoneNumber,
                anonymizedPhoneNumber,
                shortlived
        );
    }

    private String anonymizePhoneNumber(String phoneNumber) {
        StringBuilder anonymized = new StringBuilder();
        if (phoneNumber == null) {
            return null;
        }

        // Check if the phone number matches the E.164 format
        int offset = 0;
        if (phoneNumber.matches("^\\+\\d{1,3}\\d{1,14}$")) {
            offset = 4;
            anonymized.append(phoneNumber.substring(0, 4));
        }

        // Replace the rest with random digits
        for (int i = offset; i < phoneNumber.length(); i++) {
            if (Character.isDigit(phoneNumber.charAt(i))) {
                anonymized.append(random.nextInt(10));
            } else {
                anonymized.append(phoneNumber.charAt(i));
            }
        }

        return anonymized.toString();
    }

    @Override
    public String getType() {
        return "PHONE_NUMBER";
    }
}
