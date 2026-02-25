package com.booking.authentication;

import com.booking.client.AuthenticationClient;
import com.booking.models.auth.Authentication;
import com.booking.models.auth.Token;
import com.booking.utils.KataUtils;
import io.restassured.response.Response;

public class TokenManager {

    private Token generateToken() {
        Authentication authentication = buildAuthenticationPayload();

        Response auth = AuthenticationClient.retrieveToken(authentication);

        return KataUtils.deserialize(
                auth
                        .then()
                        .statusCode(200)
                        .extract()
                        .asString(),
                Token.class
        );
    }

    private static Authentication buildAuthenticationPayload() {
        Authentication authentication = new Authentication();
        authentication.setUsername("admin");
        authentication.setPassword("password");
        return authentication;
    }

    public String getToken() {
        Token token = generateToken();
        return token.getToken();
    }
}
