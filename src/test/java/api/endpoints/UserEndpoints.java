package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndpoints {

    public static Response createUser(User payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.POST_URL);
    }

    public static Response readUser(String username) {
        return given()
                .pathParam("username", username)
                .when()
                .get(Routes.GET_URL);
    }

    public static Response updateUser(String username, User payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)
                .when()
                .put(Routes.PUT_URL);
    }

    public static Response deleteUser(String username) {
        return given()
                .pathParam("username", username)
                .when()
                .delete(Routes.DELETE_URL);
    }
}
