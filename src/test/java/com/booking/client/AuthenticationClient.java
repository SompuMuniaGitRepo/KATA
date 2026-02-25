package com.booking.client;

import com.booking.models.auth.Authentication;
import com.booking.utils.KataUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationClient {

    public static Response retrieveToken(Authentication authentication) {
        return given()
                .contentType(ContentType.JSON)
                .body(KataUtils.serialize(authentication))
                .when()
                .post("https://automationintesting.online/api/auth/login");
    }
}
