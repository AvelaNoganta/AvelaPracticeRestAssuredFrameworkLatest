package RequestBuilder;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static Commons.GenerateTestData.*;
import static Commons.Paths.BASE_URL;
import static Commons.Paths.REGISTER_PATH;
import static PayloadBuilder.RegisterUserPayload.registerUserPayload;

public class RegisterUserRequestBuilder {

    private static String registeredUserId;

    public static Response registerUser() {
        String firstName = generateFirstName();
        String lastName = generateLastName();
        String email = generateEmail();
        String password = generatePassword();
        String confirmPassword = password;
        
        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .basePath(REGISTER_PATH)
                .header("Content-Type", "application/json")
                .body(registerUserPayload(firstName, lastName, email, password, confirmPassword, GroupID))
                .post()
                .then()
                .log().all()
                .extract()
                .response();

        registeredUserId = response.jsonPath().getString("data.id");
        return response;
    }

    public static String getRegisteredUserId() {
        return registeredUserId;
    }
}
