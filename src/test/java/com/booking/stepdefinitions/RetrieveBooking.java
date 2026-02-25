package com.booking.stepdefinitions;

import com.booking.authentication.TokenManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RetrieveBooking {

    private Response retrieveBookingResponse;
    private final Integer TEST_BOOKING_ID = 6;

    @When("I want to retrieve my booking")
    public void iWantToRetrieveMyBooking() {

        TokenManager tokenManager = new TokenManager();

        retrieveBookingResponse = given()
                .header("Cookie", "token=" + tokenManager.getToken())
                .when()
                .get(String.format("https://automationintesting.online/api/booking/%d", TEST_BOOKING_ID));
    }

    @Then("I should retrieve my booking details")
    public void iShouldRetrieveMyBookingDetails() {
        retrieveBookingResponse
                .then()
                .statusCode(200)
                .body("bookingid", equalTo(TEST_BOOKING_ID))
                .body("roomid", equalTo(557))
                .body("firstname", equalTo("John"))
                .body("lastname", equalTo("Snow"))
                .body("despositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2026-12-25"))
                .body("bookingdates.checkout", equalTo("2026-12-31"))
                .log()
                .all();
    }
}
