package com.booking.stepdefinitions;

import com.booking.client.BookingClient;
import com.booking.models.booking.Booking;
import com.booking.utils.RequestBuilderUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class RetrieveBookingNonExistingBooking {

    private Response retrieveBookingResponse;

    private Integer bookingId;

    @Given("booking id does not exist")
    public void firstNameIsLonger() {
        // Create booking by calling API endpoint POST /booking
        this.bookingId = 9999;
    }

    @When("I want to retrieve non existing booking")
    public void iWantToRetrieveNonExistingBooking() {
        // Retrieve booking by calling API endpoint GET /booking/{id}
        retrieveBookingResponse = BookingClient.retrieveBooking(this.bookingId);
    }

    @Then("I should not be able to retrieve my booking details")
    public void bookingCreationShouldFailWithNotFound() {
        // After response from API is received, need to check if create call was successful
        retrieveBookingResponse.then()
                .statusCode(404)
                .log().all();
    }
}
