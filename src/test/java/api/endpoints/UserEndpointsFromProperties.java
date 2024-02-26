package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndpointsFromProperties {

    public static ResourceBundle loadPropertiesFile() {
        // "routes.properties" is the name of the properties file so we put "routes" as the base name
        return ResourceBundle.getBundle("routes");
    }

    public static Response createUser(User payload) {
        String postUrl = loadPropertiesFile().getString("post_url");
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(postUrl);
    }

    public static Response readUser(String username) {
        String getUrl = loadPropertiesFile().getString("get_url");
        return given()
                .pathParam("username", username)
                .when()
                .get(getUrl);
    }

    public static Response updateUser(String username, User payload) {
        String putUrl = loadPropertiesFile().getString("put_url");
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)
                .when()
                .put(putUrl);
    }

    public static Response deleteUser(String username) {
        String deleteUrl = loadPropertiesFile().getString("delete_url");
        return given()
                .pathParam("username", username)
                .when()
                .delete(deleteUrl);
    }
}
