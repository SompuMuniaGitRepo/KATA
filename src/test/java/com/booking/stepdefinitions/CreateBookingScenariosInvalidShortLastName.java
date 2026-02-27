package com.booking.stepdefinitions;

import com.booking.client.BookingClient;
import com.booking.models.booking.Booking;
import com.booking.utils.RequestBuilderUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.hasItem;

public class CreateBookingScenariosInvalidShortLastName {

    private Response createBookingResponse;

    private Booking booking;

    @Given("lastName is shorter than 3 characters")
    public void lastNameIsLonger() {
        // Create booking by calling API endpoint POST /booking
        booking = RequestBuilderUtils.buildBookingPayload();
        booking.setLastname("Me");
    }

    @When("I want to book the room with invalid short lastName")
    public void iWantToBookTheRoomWithInvalidlastName() {
        // Create booking by calling API endpoint POST /booking
        createBookingResponse = BookingClient.createBooking(booking);
    }

    @Then("Booking creation should fail with short lastName error message size between 3 to 18")
    public void bookingCreationShouldFailWithErrorMessageSize3to18() {
        // After response from API is received, need to check if create call was successful
        createBookingResponse.then()
                .statusCode(400)
                .body("errors", hasItem("size must be between 3 and 30"))
                .log().all();
    }
}
