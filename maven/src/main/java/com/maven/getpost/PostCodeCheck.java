package com.maven.getpost;
public class PostCodeCheck {
    // postcodecheck
    public static boolean isValidZipCode(String postCode) {
        if (postCode == null) {
            return false;
        }
        // 7 digit number check
        return postCode.matches("\\d{7}");
    }
}
