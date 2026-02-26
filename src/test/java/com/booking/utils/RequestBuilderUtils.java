package com.booking.utils;

import com.booking.models.auth.Authentication;
import com.booking.models.booking.Booking;
import com.booking.models.booking.BookingDates;
import com.booking.models.booking.BookingPartialUpdate;

import java.util.Random;

public class RequestBuilderUtils {
    private static final Random random = new Random();

    public static final Integer RANDOM_ROOM_ID = random.ints(1, 1000)
            .findFirst()
            .getAsInt();
    public static final String USERNAME = "admin";
    public static final String PASSWORD = "password";

    public static Integer BOOKING_ID = -1;
    public static final String FIRSTNAME = "John";
    public static final String LASTNAME = "Snow";
    public static final Integer ROOM_ID = 7;
    public static final String EMAIL = "john.snow@got.com";
    public static final String PHONE = "1234567890889";
    public static final boolean DEPOSIT_PAID = true;
    public static final String CHECK_IN_DATE = "2026-12-25";
    public static final String CHECK_OUT_DATE = "2026-12-31";

    public static final String ENTIRELY_UPDATED_FIRSTNAME = "Denaerys";
    public static final String ENTIRELY_UPDATED_LASTNAME = "Targaryen";
    public static final Integer ENTIRELY_UPDATED_ROOM_ID = 7;
    public static final String ENTIRELY_UPDATED_EMAIL = "denaerys.targaryen@got.com";
    public static final String ENTIRELY_UPDATED_PHONE = "1234765890889";
    public static final boolean ENTIRELY_UPDATED_DEPOSIT_PAID = false;
    public static final String ENTIRELY_UPDATED_CHECK_IN_DATE = "2026-06-01";
    public static final String ENTIRELY_UPDATED_CHECK_OUT_DATE = "2026-06-12";

    public static final String PARTIALLY_UPDATED_FIRSTNAME = "Arya";
    public static final String PARTIALLY_UPDATED_LASTNAME = "Stark";
    public static final boolean PARTIALLY_UPDATED_DEPOSIT_PAID = true;

    /**
     * Build payload for create new booking
     * @return Booking
     */
    public static Booking buildBookingPayload() {
        Booking booking = new Booking();
        booking.setFirstname(FIRSTNAME);
        booking.setLastname(LASTNAME);
        booking.setRoomid(ROOM_ID);
        booking.setEmail(EMAIL);
        booking.setPhone(PHONE);
        booking.setDepositpaid(DEPOSIT_PAID);

        BookingDates bookingDates = buildBookingDatesPayload();

        booking.setBookingdates(bookingDates);

        return booking;
    }

    /**
     * Build payload for update entire booking
     * @return Booking
     */
    public static Booking buildEntirelyUpdatedBookingPayload() {
        Booking booking = new Booking();
        booking.setFirstname(ENTIRELY_UPDATED_FIRSTNAME);
        booking.setLastname(ENTIRELY_UPDATED_LASTNAME);
        booking.setRoomid(ENTIRELY_UPDATED_ROOM_ID);
        booking.setEmail(ENTIRELY_UPDATED_EMAIL);
        booking.setPhone(ENTIRELY_UPDATED_PHONE);
        booking.setDepositpaid(ENTIRELY_UPDATED_DEPOSIT_PAID);

        BookingDates bookingDates = buildEntirelyUpdatedBookingDatesPayload();

        booking.setBookingdates(bookingDates);

        return booking;
    }

    /**
     * Build payload for update partial booking
     * @return BookingPartialUpdate
     */
    public static BookingPartialUpdate buildPartiallyUpdatedBookingPayload() {
        BookingPartialUpdate bookingPartialUpdate = new BookingPartialUpdate();

        // Partial update for checkIn and checkOut dates
        bookingPartialUpdate.setFirstname(PARTIALLY_UPDATED_FIRSTNAME);
        bookingPartialUpdate.setLastname(PARTIALLY_UPDATED_LASTNAME);
        bookingPartialUpdate.setDepositpaid(PARTIALLY_UPDATED_DEPOSIT_PAID);

        return bookingPartialUpdate;
    }

    /**
     * Build payload for new booking checkIn and checkOut dates
     * @return BookingPartialUpdate
     */
    public static BookingDates buildBookingDatesPayload() {
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(CHECK_IN_DATE);
        bookingDates.setCheckout(CHECK_OUT_DATE);

        return bookingDates;
    }

    /**
     * Build payload for entirely update booking checkIn and checkOut dates
     * @return BookingPartialUpdate
     */
    public static BookingDates buildEntirelyUpdatedBookingDatesPayload() {
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(ENTIRELY_UPDATED_CHECK_IN_DATE);
        bookingDates.setCheckout(ENTIRELY_UPDATED_CHECK_OUT_DATE);

        return bookingDates;
    }

    /**
     * Set bookingId in static variable so that other test cases can use it
     * @param bookingId booking Id
     */
    public static void setBookingId(Integer bookingId) {
        BOOKING_ID = bookingId;
    }

    /**
     * Build payload for login to retrieve token
     * @return Authentication
     */
    public static Authentication buildAuthenticationPayload() {
        Authentication authentication = new Authentication();
        authentication.setUsername(USERNAME);
        authentication.setPassword(PASSWORD);
        return authentication;
    }
}
