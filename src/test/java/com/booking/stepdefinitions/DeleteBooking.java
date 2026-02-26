package com.booking.stepdefinitions;

import com.booking.client.BookingClient;
import com.booking.utils.RequestBuilderUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class DeleteBooking {
    private Response bookingDeletionResponse;

    @When("I want to delete my booking")
    public void iWantToDeleteMyBooking() {
        // Delete booking by calling API endpoint DELETE /booking/{id}
        bookingDeletionResponse = BookingClient.deleteBooking(RequestBuilderUtils.BOOKING_ID);
    }

    @Then("I should be able to delete my booking")
    public void iShouldBeAbleToDeleteMyBooking()  {
        // After response from API is received, need to check if delete call was successful
        bookingDeletionResponse.then().statusCode(200)
                .log()
                .all();

        // Retrieve booking for booking id just deleted
        Response retriveBookingResponse = BookingClient.retrieveBooking(RequestBuilderUtils.BOOKING_ID);

        // Check retrieve booking call has received 404 to ensure deletion of booking
        retriveBookingResponse.then()
                .statusCode(404)
                .log().all();
    }
}
