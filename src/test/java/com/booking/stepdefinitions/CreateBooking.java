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

    @When("I want to book the room")
    public void iWantToBookTheRoom() {

        createBookingResponse = given()
                .contentType(ContentType.JSON)
                .body(KataUtils.serialize(buildBookingPayload()))
                .when()
                .post("https://automationintesting.online/api/booking");
    }

    @Then("I should retrieve my booking")
    public void iShouldRetrieveMyBooking() {
        TokenManager tokenManager = new TokenManager();
        System.out.println("Kuttumona Token =========== : " + tokenManager.getToken());

        System.out.println(createBookingResponse.then().log().all());

        createBookingResponse.then().statusCode(201)
                .body("bookingid", greaterThan(0))
                .body("roomid", equalTo(35))
                .log().all();

    }

    private static Booking buildBookingPayload() {
        Booking booking = new Booking();
        booking.setFirstname("John");
        booking.setLastname("Snow");
        booking.setRoomid(35);
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
