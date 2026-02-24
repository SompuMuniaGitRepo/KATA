package com.booking.stepdefinitions;

import com.booking.authentication.TokenManager;
import com.booking.models.booking.Booking;
import com.booking.models.booking.BookingDates;
import com.booking.utils.KataUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class CreateBooking {

    private Response createBookingResponse;

    private Response retriveBookingResponse;

    @When("I want to book the room")
    public void iWantToBookTheRoom() {

        createBookingResponse = given()
                .contentType(ContentType.JSON)
                .body(KataUtils.serialize(buildBookingPayload()))
                .when()
                .post("https://automationintesting.online/api/booking");

        // Create Booking and check it was successful
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
        TokenManager tokenManager = new TokenManager();

        retriveBookingResponse = given()
                .header("Cookie", "token=" + tokenManager.getToken())
                .when()
                .get(String.format("https://automationintesting.online/api/booking/%d", myBooking.getBookingid()));


        // Match booking id with retrieved booking id
        retriveBookingResponse.then().statusCode(200)
                .body("bookingid", equalTo(myBooking.getBookingid()))
                .log().all();
    }

    private static Booking buildBookingPayload() {
        Booking booking = new Booking();
        booking.setFirstname("John");
        booking.setLastname("Snow");
        booking.setRoomid(557);
        booking.setEmail("john.snow@example.com");
        booking.setPhone("1234567890889");
        booking.setDepositpaid(true);

        BookingDates bookingDates = buildBookingDatesPayload();

        booking.setBookingdates(bookingDates);

        return booking;
    }

    private static BookingDates buildBookingDatesPayload() {
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2026-12-25");
        bookingDates.setCheckout("2026-12-31");

        return bookingDates;
    }
}
