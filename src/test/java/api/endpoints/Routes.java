package api.endpoints;

public class Routes {

    public static final String BASE_URL = "https://petstore.swagger.io/v2";

    // User Module
    public static final String USER_PATH = "/user";
    public static final String GET_URL = BASE_URL + USER_PATH + "/{username}";
    public static final String POST_URL = BASE_URL + USER_PATH;
    public static final String PUT_URL = BASE_URL + USER_PATH + "/{username}";
    public static final String DELETE_URL = BASE_URL + USER_PATH + "/{username}";


}
