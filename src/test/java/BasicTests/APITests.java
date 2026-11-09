package BasicTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class APITests {

    String BASE_URL = "https://www.ndosiautomation.co.za/APIDEV";
    String adminToken;
    String registeredUserId;

    @Test(priority = 1)
    public void registerUser() {
        String appPath = "/register";
        String registerPayload = "{\n" +
                "  \"firstName\": \"Jeffery\",\n" +
                "  \"lastName\": \"Rutherford\",\n" +
                "  \"email\": \"Elvie59@yahoo.com3\",\n" +
                "  \"password\": \"Testing1234!\",\n" +
                "  \"confirmPassword\": \"Testing1234!\",\n" +
                "  \"groupId\": \"0321f54a-64e2-4dbf-acf6-ab9614738149\"\n" +
                "}";
        Response response = RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(registerPayload)
                .post(BASE_URL + appPath)
                .then()
                .log().all()
                .extract()
                .response();

        System.out.println("Register User Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 201, "User registration failed, expected status code " +
                "201" + " but got " + response.getStatusCode());
        registeredUserId = response.jsonPath().getString("data.id");
        System.out.println("Registered User ID: " + registeredUserId);
    }

    @Test(priority = 2)
    public void adminLogin() {
        // Code for admin login test
        String appPath = "/login";
        String adminLoginPayload = "{\n" +
                "  \"email\": \"admin@gmail.com\",\n" +
                "  \"password\": \"@12345678\"\n" +
                "}";
        Response response = RestAssured.given()
                //if you want to see the request details in the console, uncomment the line below
                //.log().all()
                .header("Content-Type", "application/json")
                .body(adminLoginPayload)
                .post(BASE_URL + appPath);
                //stop after post and run the test to see the response in console include line 30
                //This is optional, you can comment it out if you don't want to see the response in the console
//                .then()
//                .log().all()
//                .extract()
//                .response();

        System.out.println("Admin Login Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Admin login failed, expected status code " +
                "200" + " but got " + response.getStatusCode());
        adminToken = response.jsonPath().getString("data.token");
    }
    @Test(priority = 3)
    public void approveUser() {
        String appPath = "/admin/users/" + registeredUserId + "/approve";
        Response response = RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .put(BASE_URL + appPath)
                .then()
                .log().all()
                .extract()
                .response();

        System.out.println("Approve User Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "User approval failed, expected status code " +
                "200" + " but got " + response.getStatusCode());
    }
    @Test(priority = 4)
    public void getAdminProfile() {
        String appPath = "/profile";
        Response response = RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .get(BASE_URL + appPath)
                .then()
                .log().all()
                .extract()
                .response();

        System.out.println("Admin Profile Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Get profile failed, expected status code " +
                "200" + " but got " + response.getStatusCode());
    }

}
