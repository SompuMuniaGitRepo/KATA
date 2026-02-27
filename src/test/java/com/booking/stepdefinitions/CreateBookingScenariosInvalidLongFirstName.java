package com.booking.stepdefinitions;

import com.booking.client.BookingClient;
import com.booking.models.booking.Booking;
import com.booking.utils.RequestBuilderUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.hasItem;

public class CreateBookingScenariosInvalidLongFirstName {

    private Response createBookingResponse;

    private Booking booking;

    @Given("firstName is longer than 18 characters")
    public void firstNameIsLonger() {
        // Create booking by calling API endpoint POST /booking
        booking = RequestBuilderUtils.buildBookingPayload();
        booking.setFirstname("Smith Bart Elizabeth Stevenson");
    }

    @When("I want to book the room with invalid long firstName")
    public void iWantToBookTheRoomWithInvalidFirstName() {
        // Create booking by calling API endpoint POST /booking
        createBookingResponse = BookingClient.createBooking(booking);
    }

    @Then("Booking creation should fail with long firstName error message size between 3 to 18")
    public void bookingCreationShouldFailWithErrorMessageSize3to18() {
        // After response from API is received, need to check if create call was successful
        createBookingResponse.then()
                .statusCode(400)
                .body("errors", hasItem("size must be between 3 and 18"))
                .log().all();
    }
}
