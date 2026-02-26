package com.booking.authentication;

import com.booking.client.AuthenticationClient;
import com.booking.models.auth.Token;
import com.booking.utils.KataUtils;
import com.booking.utils.RequestBuilderUtils;
import io.restassured.response.Response;

public class TokenManager {

    private Token token;

    /**
     * Generate login token by calling API endpoint with userId and password
     * @return Token
     */
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

    /**
     * Returns generated token to Client methods where it will be used for login
     * @return Token
     */
    public String getPassKey() {
        if (token == null) {
            token = generateToken();
        }
        return token.getToken();
    }
}
