package api.test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserModuleTests {

    Faker faker;
    User userPayload;
    private final static Logger log = LogManager.getLogger(UserModuleTests.class);

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
    }

    @Test(priority = 1)
    public void testPostUser() {
        log.info("Creating user with username: {}", userPayload.getUsername());
        Response response = UserEndpoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        log.info("User {} created successfully", userPayload.getUsername());
    }

    @Test(priority = 2)
    public void testGetUserByName() {
        log.info("Reading user with username: {}", userPayload.getUsername());
        Response response = UserEndpoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        log.info("User {} read successfully", userPayload.getUsername());
    }

    @Test(priority = 3)
    public void testUpdateUserByName() {
        // update data using payload
        log.info("Updating user with username: {}", userPayload.getUsername());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndpoints.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().log().body().statusCode(200);
        //validate the updated data
        Response responseAfterPutRequest = UserEndpoints.readUser(this.userPayload.getUsername());
        responseAfterPutRequest.then().log().all();
        Assert.assertEquals(responseAfterPutRequest.getStatusCode(), 200);
        Assert.assertEquals(responseAfterPutRequest.getBody().jsonPath().getString("firstName"), this.userPayload.getFirstName());
        Assert.assertEquals(responseAfterPutRequest.getBody().jsonPath().getString("lastName"), this.userPayload.getLastName());
        Assert.assertEquals(responseAfterPutRequest.getBody().jsonPath().getString("email"), this.userPayload.getEmail());
        log.info("User {} updated successfully", userPayload.getUsername());
    }

    @Test(priority = 4)
    public void testDeleteUserByName() {
        log.info("Deleting user with username: {}", userPayload.getUsername());
        Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        //validate the deleted data
        Response responseAfterDeleteRequest = UserEndpoints.readUser(this.userPayload.getUsername());
        responseAfterDeleteRequest.then().log().all();
        Assert.assertEquals(responseAfterDeleteRequest.getStatusCode(), 404);
        log.info("User {} deleted successfully", userPayload.getUsername());
    }

    // schema validation
    public void testUserSchema() {
        Response response = UserEndpoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        response.then().assertThat().body(matchesJsonSchemaInClasspath("user-schema.json"));
    }
}







