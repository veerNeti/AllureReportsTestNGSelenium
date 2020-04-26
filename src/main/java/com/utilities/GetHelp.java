package com.utilities;

import java.util.Base64;

public class GetHelp implements TestHelper {

    @Override
    public String decodePassword(String encodedString) {
        return new String(Base64.getDecoder().decode(encodedString));
    }
}
