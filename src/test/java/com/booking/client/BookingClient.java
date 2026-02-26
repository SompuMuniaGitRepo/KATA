package com.booking.client;

import com.booking.authentication.TokenManager;
import com.booking.models.booking.Booking;
import com.booking.models.booking.BookingPartialUpdate;
import com.booking.utils.KataUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingClient {

    private static final TokenManager tokenManager = new TokenManager();

    public static Response createBooking(Booking booking) {
        return given()
                .contentType(ContentType.JSON)
                .body(KataUtils.serialize(booking))
                .when()
                .post("https://automationintesting.online/api/booking");
    }
    public static Response retrieveBooking(Integer bookingId) {
        return given()
                .header("Cookie", "token=" + tokenManager.getPassKey())
                .when()
                .get(String.format("https://automationintesting.online/api/booking/%d", bookingId));
    }

    public static Response deleteBooking(Integer bookingId) {
        return given()
                .header("Cookie", "token=" + tokenManager.getPassKey())
                .when()
                .delete(String.format("https://automationintesting.online/api/booking/%d", bookingId));
    }

    public static Response entireUpdateBooking(Integer bookingId, Booking booking) {
        return given()
                .header("Cookie", "token=" + tokenManager.getPassKey())
                .contentType(ContentType.JSON)
                .body(KataUtils.serialize(booking))
                .when()
                .put(String.format("https://automationintesting.online/api/booking/%d", bookingId));
    }

    public static Response partialUpdateBooking(Integer bookingId, BookingPartialUpdate bookingPartialUpdate) {
        return given()
                .header("Cookie", "token=" + tokenManager.getPassKey())
                .contentType(ContentType.JSON)
                .body(KataUtils.serialize(bookingPartialUpdate))
                .when()
                .patch(String.format("https://automationintesting.online/api/booking/%d", bookingId));
    }
}
