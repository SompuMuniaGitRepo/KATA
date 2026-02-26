package com.booking.stepdefinitions;

import com.booking.client.BookingClient;
import com.booking.utils.RequestBuilderUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class DeleteBooking {
    private Response bookingDeletionResponse;

    @When("I want to delete my booking")
    public void iWantToDeleteMyBooking() {
        bookingDeletionResponse = BookingClient.deleteBooking(RequestBuilderUtils.BOOKING_ID);
    }

    @Then("I should be able to delete my booking")
    public void iShouldBeAbleToDeleteMyBooking()  {

        // Create Booking and check it was successful
        bookingDeletionResponse.then().statusCode(200)
                .log()
                .all();
    }
}
