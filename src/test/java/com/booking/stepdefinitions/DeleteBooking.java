package com.booking.stepdefinitions;

import com.booking.authentication.TokenManager;
import com.booking.utils.RequestBuilderUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBooking {
    private Response bookingDeletionResponse;

    @When("I want to delete my booking")
    public void iWantToDeleteMyBooking() {
        TokenManager tokenManager = new TokenManager();

        bookingDeletionResponse = given()
                .header("Cookie", "token=" + tokenManager.getToken())
                .when()
                .delete(String.format("https://automationintesting.online/api/booking/%d", RequestBuilderUtils.BOOKING_ID));
    }

    @Then("I should be able to delete my booking")
    public void iShouldBeAbleToDeleteMyBooking()  {

        // Create Booking and check it was successful
        bookingDeletionResponse.then().statusCode(200)
                .log()
                .all();
    }
}
