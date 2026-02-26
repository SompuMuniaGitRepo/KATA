package com.booking.stepdefinitions;

import com.booking.client.BookingClient;
import com.booking.models.booking.Booking;
import com.booking.utils.RequestBuilderUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.hasItem;

public class CreateBookingScenariosInvalidEmail {

    private Response createBookingResponse;

    private Booking booking;

    @Given("email is malformed email")
    public void phoneIsLonger() {
        // Create booking by calling API endpoint POST /booking
        booking = RequestBuilderUtils.buildBookingPayload();
        booking.setEmail("westeros.got");
    }

    @When("I want to book the room with invalid email")
    public void iWantToBookTheRoomWithInvalidPhone() {
        // Create booking by calling API endpoint POST /booking
        createBookingResponse = BookingClient.createBooking(booking);
    }

    @Then("Booking creation should fail with invalid email error message well-formed email")
    public void bookingCreationShouldFailWithErrorMessageSize11to21() {
        // After response from API is received, need to check if create call was successful
        createBookingResponse.then()
                .statusCode(400)
                .body("errors", hasItem("must be a well-formed email address"))
                .log().all();
    }
}
