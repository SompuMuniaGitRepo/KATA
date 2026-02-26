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

    /**
     * Create booking by calling API endpoint with Booking details
     * @param booking booking details containing firstName, lastName, roomId, checkIn and checkOut dates
     * @return Response
     */
    public static Response createBooking(Booking booking) {
        return given()
                .contentType(ContentType.JSON)
                .body(KataUtils.serialize(booking))
                .when()
                .post("https://automationintesting.online/api/booking");
    }
    /**
     * Retrieve booking by calling API endpoint with Booking ID and login token
     * @param bookingId bookingId for which booking details will be retrieved
     * @return Response
     */
    public static Response retrieveBooking(Integer bookingId) {
        return given()
                .header("Cookie", "token=" + tokenManager.getPassKey())
                .when()
                .get(String.format("https://automationintesting.online/api/booking/%d", bookingId));
    }

    /**
     * Delete booking by calling API endpoint with Booking ID and login token
     * @param bookingId bookingId for which booking details will be deleted
     * @return Response
     */
    public static Response deleteBooking(Integer bookingId) {
        return given()
                .header("Cookie", "token=" + tokenManager.getPassKey())
                .when()
                .delete(String.format("https://automationintesting.online/api/booking/%d", bookingId));
    }

    /**
     * Update entire booking by calling API endpoint with Booking ID and login token
     * @param bookingId bookingId for which booking details will be entirely updated
     * @param booking booking details containing firstName, lastName, roomId, checkIn and checkOut dates
     * @return Response
     */
    public static Response entireUpdateBooking(Integer bookingId, Booking booking) {
        return given()
                .header("Cookie", "token=" + tokenManager.getPassKey())
                .contentType(ContentType.JSON)
                .body(KataUtils.serialize(booking))
                .when()
                .put(String.format("https://automationintesting.online/api/booking/%d", bookingId));
    }

    /**
     * Update partial booking by calling API endpoint with Booking ID and login token
     * @param bookingId bookingId for which booking details will be partially updated
     * @param bookingPartialUpdate booking details containing firstName, lastName and deposit paid
     * @return Response
     */
    public static Response partialUpdateBooking(Integer bookingId, BookingPartialUpdate bookingPartialUpdate) {
        return given()
                .header("Cookie", "token=" + tokenManager.getPassKey())
                .contentType(ContentType.JSON)
                .body(KataUtils.serialize(bookingPartialUpdate))
                .when()
                .patch(String.format("https://automationintesting.online/api/booking/%d", bookingId));
    }
}
