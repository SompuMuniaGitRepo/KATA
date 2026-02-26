package com.booking.stepdefinitions;

import com.booking.client.BookingClient;
import com.booking.models.booking.Booking;
import com.booking.utils.KataUtils;
import com.booking.utils.RequestBuilderUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class CreateBooking {

    private Response createBookingResponse;

    @When("I want to book the room")
    public void iWantToBookTheRoom() {

        // Create booking by calling API endpoint POST /booking
        createBookingResponse = BookingClient.createBooking(RequestBuilderUtils.buildBookingPayload());
    }

    @Then("I should retrieve my booking")
    public void iShouldRetrieveMyBooking() {
        // After response from API is received, need to check if create call was successful
        createBookingResponse.then().statusCode(201)
                .body("bookingid", greaterThan(0))
                .log().all();

        // Retrieve Booking via API call GET /booking/{id} to ensure booking was successfully made
        Booking myBooking = KataUtils.deserialize(
                createBookingResponse
                        .then()
                        .statusCode(201)
                        .extract()
                        .asString(),
                Booking.class
        );

        // Retrieve booking for booking id just created
        Response retriveBookingResponse = BookingClient.retrieveBooking(myBooking.getBookingid());

        // Match booking id with retrieved booking id along with other booking data like firstName,
        // lastName, roomId, payments done and checkIn/checkOut dates
        retriveBookingResponse.then().statusCode(200)
                .body("bookingid", equalTo(myBooking.getBookingid()))
                .body("roomid", equalTo(RequestBuilderUtils.ROOM_ID))
                .body("firstname", equalTo(RequestBuilderUtils.FIRSTNAME))
                .body("lastname", equalTo(RequestBuilderUtils.LASTNAME))
                .body("depositpaid", equalTo(RequestBuilderUtils.DEPOSIT_PAID))
                .body("bookingdates.checkin", equalTo(RequestBuilderUtils.CHECK_IN_DATE))
                .body("bookingdates.checkout", equalTo(RequestBuilderUtils.CHECK_OUT_DATE))
                .log().all();

        // set the BOOKING ID at static level so that other test cases can use it
        RequestBuilderUtils.setBookingId(myBooking.getBookingid());
    }
}
