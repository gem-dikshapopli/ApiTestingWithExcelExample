package stepdefinition;
import implementation.GoRestImplementation;
import implementation.Implementation;
import implementation.PostDataFromExcel;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.io.IOException;
public class StepDefinition {

    @Given("Get the Authorization Token")
    public void getAuthToken(){
        Implementation.authorizationToken();
    }
    @And("Verify the Restful-booker site")
    public void verifySite(){
        Implementation.verifyTheSite();
    }
    @Then("Get the Booking Id's")
    public void getBookingIds(){
        Implementation.bookingIds();
    }
    @And("Get the Booking Data of The first id")
    public void getBookingData(){
        Implementation.bookingData();
    }
    @Then("Create a new Booking and post it on the server")
    public void postData(){
        Implementation.createBooking();
    }
    @Then("Perform updation of the created booking data")
    public void updateData(){
        Implementation.updateBooking();
    }
    @Then("Perform the partial updation on the changed data")
    public void partialUpdate(){
        Implementation.partialUpdate();
    }
    @And("Delete the data")
    public void deleteData(){
        Implementation.delete();
    }
    @Given("Get the data from the GoRest Website and write data in excel")
    public void getDataFromGoRest() throws IOException {
        GoRestImplementation.getData();
    }
    @Given("Get data from the excel and post the data on the server")
    public void postDataOnGoRest() throws IOException {
        PostDataFromExcel.postData();
    }
}
