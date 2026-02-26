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

        // Create booking
        createBookingResponse = BookingClient.createBooking(RequestBuilderUtils.buildBookingPayload());

        // check if booking was successful
        createBookingResponse.then().statusCode(201)
                .body("bookingid", greaterThan(0))
                .log().all();
    }

    @Then("I should retrieve my booking")
    public void iShouldRetrieveMyBooking() {
        // Retrieve Booking object to get booking Id
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

        // Match booking id with retrieved booking id
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
