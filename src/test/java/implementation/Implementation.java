package implementation;

import implementation.logger.Log;
import implementation.pojo.AuthenticationDetails;
import implementation.pojo.BookingInformation;
import implementation.pojo.CheckInOutTime;
import implementation.restutils.RestUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
* authorizationToken- To Fetch the authorization Token
* bookingIds- Get all the bookingIds from the api
* .path method to fetch the data from the jsonBody
* bookingData- Fetch the data of the first id present
* createBooking- Fetching the data using pojo and then post the data on the server using post method
* updateBooking- Performing the put method by updating the data
* partialUpdate- Performing Patch method by only updating the additionalneeds key
* delete- Performing the Delete method
* @author-Diksha Popli
* */
public class Implementation {
    public static String bookingId;

    @Test
    public static void authorizationToken() {

        AuthenticationDetails payload = new AuthenticationDetails();
        payload.setUsername("admin");
        payload.setPassword("password123");

        Response response = RestUtils.postData(payload, "auth/");

        String authToken = response.asPrettyString();
        Log.info("AuthToken = " + authToken);

    }

    @Test
    public static void verifyTheSite() {

        Log.info("**************************Verify The Site********************");
        Assert.assertEquals(200, RestUtils.getData("booking/").getStatusCode(), "Verified");
        Assert.assertEquals(201, Implementation.ping(), "Successfull");
    }

    @Test
    public static void bookingIds() {
        Log.info("***********************Get Booking Id**************************");

        Response response = RestUtils.getData("booking/");
        bookingId = response.path("bookingid[0]").toString();
        Log.info("booking Id = " + bookingId);
        Assert.assertEquals(201, Implementation.ping(), "Successfull");
    }

    @Test
    public static void bookingData() {
        Log.info("************************Get Booking pojo.Data**********************");

        RestUtils.getData("booking/" + bookingId);
        Assert.assertEquals(201, Implementation.ping(), "Successfull");
    }

    @Test
    public static void createBooking() {
        Log.info("**********************Create Booking*************************");

        BookingInformation payload = new BookingInformation();
        payload.setFirstname("Diksha");
        payload.setLastname("Popli");
        payload.setTotalprice(111);
        payload.setDepositpaid(true);
        CheckInOutTime data = new CheckInOutTime();
        data.setCheckin("2018-01-01");
        data.setCheckout("2018-01-01");
        payload.setBookingdates(data);
        payload.setAdditionalneeds("lunch");
        RestUtils.postData(payload, "booking/");
        Assert.assertEquals(201, Implementation.ping(), "Successfull");
    }

    @Test
    public static void updateBooking() {
        BookingInformation payload = new BookingInformation();
        payload.setFirstname("changed");
        payload.setLastname("changed");
        payload.setTotalprice(111);
        payload.setDepositpaid(true);

        CheckInOutTime data = new CheckInOutTime();
        data.setCheckin("2018-01-01");
        data.setCheckout("2018-01-01");

        payload.setBookingdates(data);
        payload.setAdditionalneeds("lunch");

        RestUtils.putData(bookingId, payload, "booking/");
        Assert.assertEquals(201, Implementation.ping(), "Successfull");
    }

    @Test
    public static void partialUpdate() {
        Log.info("**********************Partial Update Booking*************************");

        BookingInformation payload = new BookingInformation();
        payload.setAdditionalneeds("brunch");
        RestUtils.patchData(bookingId, payload, "booking/");
        Assert.assertEquals(201, Implementation.ping(), "Successfull");
    }

    @Test
    public static void delete() {
        Log.info("*****************************Delete********************");
        RestUtils.deleteData(bookingId, "booking/");
        Assert.assertEquals(201, Implementation.ping(), "Successfull");
    }

    public static int ping() {
        int id = RestUtils.getData("ping").getStatusCode();
        return id;
    }

}
