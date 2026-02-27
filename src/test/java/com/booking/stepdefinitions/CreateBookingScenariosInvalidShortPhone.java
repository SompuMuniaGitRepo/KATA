package com.booking.stepdefinitions;

import com.booking.client.BookingClient;
import com.booking.models.booking.Booking;
import com.booking.utils.RequestBuilderUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.hasItem;

public class CreateBookingScenariosInvalidShortPhone {

    private Response createBookingResponse;

    private Booking booking;

    @Given("phone number is shorter than 11 characters")
    public void phoneIsLonger() {
        // Create booking by calling API endpoint POST /booking
        booking = RequestBuilderUtils.buildBookingPayload();
        booking.setPhone("458754");
    }

    @When("I want to book the room with invalid short phone number")
    public void iWantToBookTheRoomWithInvalidPhone() {
        // Create booking by calling API endpoint POST /booking
        createBookingResponse = BookingClient.createBooking(booking);
    }

    @Then("Booking creation should fail with short phone number error message size between 11 to 21")
    public void bookingCreationShouldFailWithErrorMessageSize11to21() {
        // After response from API is received, need to check if create call was successful
        createBookingResponse.then()
                .statusCode(400)
                .body("errors", hasItem("size must be between 11 and 21"))
                .log().all();
    }
}
