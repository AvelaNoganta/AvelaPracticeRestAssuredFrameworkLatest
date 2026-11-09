package RequestBuilder;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static Commons.GenerateTestData.adminEmail;
import static Commons.GenerateTestData.adminPassword;
import static Commons.Paths.*;
import static PayloadBuilder.AdminPayload.adminLoginPayload;

public class AdminRequestBuilder {

    private static String adminToken;

    public static Response adminLoginRequestBuilder() {
        Response response = RestAssured.given()
                .log().all()
                .baseUri(BASE_URL)
                .basePath(LOGIN_PATH)
                .header("Content-Type", "application/json")
                .body(adminLoginPayload(adminEmail, adminPassword))
                .post()
                .then()
                .log().all()
                .extract()
                .response();
        
        adminToken = response.jsonPath().getString("data.token");
        return response;
    }

    public static Response getAdminProfile() {
        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .basePath(PROFILE_PATH)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .get()
                .then()
                .log().all()
                .extract()
                .response();
        return response;
    }

    public static Response approveUser(String userId) {
        String approvePath = APPROVE_USER_PATH.replace("{userId}", userId);
        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .basePath(approvePath)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .put()
                .then()
                .log().all()
                .extract()
                .response();
        return response;
    }
}
