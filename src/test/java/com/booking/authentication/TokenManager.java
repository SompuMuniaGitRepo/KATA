package com.booking.authentication;

import com.booking.client.AuthenticationClient;
import com.booking.models.auth.Token;
import com.booking.utils.KataUtils;
import com.booking.utils.RequestBuilderUtils;
import io.restassured.response.Response;

public class TokenManager {

    private Token token;

    private Token generateToken() {
        Response auth = AuthenticationClient.retrieveToken(RequestBuilderUtils.buildAuthenticationPayload());

        return KataUtils.deserialize(
                auth
                        .then()
                        .statusCode(200)
                        .extract()
                        .asString(),
                Token.class
        );
    }

    public String getPassKey() {
        if (token == null) {
            token = generateToken();
        }
        return token.getToken();
    }
}
