package com.booking.authentication;

import com.booking.models.auth.Authentication;
import com.booking.models.auth.Token;
import com.booking.utils.KataUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenManager {

    public TokenManager () {
        populateToken();
    }

    private Token token;

    private void populateToken() {
        Authentication authentication = buildAuthenticationPayload();

        Response auth = given()
                .contentType(ContentType.JSON)
                .body(KataUtils.serialize(authentication))
                .when()
                .post("https://automationintesting.online/api/auth/login");

        this.token = KataUtils.deserialize(
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
        return token.getToken();
    }
}
