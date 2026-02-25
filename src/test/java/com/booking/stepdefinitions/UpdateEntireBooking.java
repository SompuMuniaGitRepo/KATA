package com.booking.stepdefinitions;

import com.booking.client.BookingClient;
import com.booking.utils.RequestBuilderUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class UpdateEntireBooking {
    private Response bookingUpdateResponse;

    @When("I want to update my entire booking")
    public void iWantToUpdateMyEntireBooking() {
        // Update entire booking for a booking id
        bookingUpdateResponse = BookingClient.entireUpdateBooking(RequestBuilderUtils.BOOKING_ID, RequestBuilderUtils.buildEntirelyUpdatedBookingPayload());
    }

    @Then("I should get my entirely updated booking details")
    public void iShouldGetMyEntirelyUpdatedBookingDetails()  {
        bookingUpdateResponse
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .log()
                .all();

        // Retrieve booking for a booking id
        Response retrieveBookingResponse = BookingClient.retrieveBooking(RequestBuilderUtils.BOOKING_ID);

        // Match with updated response
        retrieveBookingResponse
                .then()
                .statusCode(200)
                .body("bookingid", equalTo(RequestBuilderUtils.BOOKING_ID))
                .body("roomid", equalTo(RequestBuilderUtils.ENTIRELY_UPDATED_ROOM_ID))
                .body("firstname", equalTo(RequestBuilderUtils.ENTIRELY_UPDATED_FIRSTNAME))
                .body("lastname", equalTo(RequestBuilderUtils.ENTIRELY_UPDATED_LASTNAME))
                .body("depositpaid", equalTo(RequestBuilderUtils.ENTIRELY_UPDATED_DEPOSIT_PAID))
                .body("bookingdates.checkin", equalTo(RequestBuilderUtils.ENTIRELY_UPDATED_CHECK_IN_DATE))
                .body("bookingdates.checkout", equalTo(RequestBuilderUtils.ENTIRELY_UPDATED_CHECK_OUT_DATE))
                .log()
                .all();

    }
}
