package com.booking.stepdefinitions;

import com.booking.client.BookingClient;
import com.booking.utils.RequestBuilderUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class RetrieveBooking {

    private Response retrieveBookingResponse;

    @When("I want to retrieve my booking")
    public void iWantToRetrieveMyBooking() {
        // Retrieve booking for a booking id
        retrieveBookingResponse = BookingClient.retrieveBooking(RequestBuilderUtils.BOOKING_ID);
    }

    @Then("I should retrieve my booking details")
    public void iShouldRetrieveMyBookingDetails() {
        retrieveBookingResponse
                .then()
                .statusCode(200)
                .body("bookingid", equalTo(RequestBuilderUtils.BOOKING_ID))
                .body("roomid", equalTo(RequestBuilderUtils.ROOM_ID))
                .body("firstname", equalTo(RequestBuilderUtils.FIRSTNAME))
                .body("lastname", equalTo(RequestBuilderUtils.LASTNAME))
                .body("depositpaid", equalTo(RequestBuilderUtils.DEPOSIT_PAID))
                .body("bookingdates.checkin", equalTo(RequestBuilderUtils.CHECK_IN_DATE))
                .body("bookingdates.checkout", equalTo(RequestBuilderUtils.CHECK_OUT_DATE))
                .log()
                .all();
    }
}
