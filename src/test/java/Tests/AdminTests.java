package Tests;

import RequestBuilder.AdminRequestBuilder;
import RequestBuilder.RegisterUserRequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class AdminTests {

    @Test(priority = 1)
    public void testRegisterUser() {
        Response response = RegisterUserRequestBuilder.registerUser();
        
        assertEquals(response.getStatusCode(), 201, "Expected status code 201 for successful user registration");
        assertNotNull(RegisterUserRequestBuilder.getRegisteredUserId(), "User ID should not be null");
        System.out.println("Registered User ID: " + RegisterUserRequestBuilder.getRegisteredUserId());
    }

    @Test(priority = 2)
    public void testAdminLogin() {
        Response response = AdminRequestBuilder.adminLoginRequestBuilder();
        
        assertEquals(response.getStatusCode(), 200, "Expected status code 200 for successful admin login");
        assertNotNull(response.jsonPath().getString("data.token"), "Admin token should not be null");
        System.out.println("Admin Login Successful");
    }

    @Test(priority = 3)
    public void testApproveUser() {
        String userId = RegisterUserRequestBuilder.getRegisteredUserId();
        assertNotNull(userId, "User ID should be available from registration test");
        
        Response response = AdminRequestBuilder.approveUser(userId);
        
        assertEquals(response.getStatusCode(), 200, "Expected status code 200 for successful user approval");
        System.out.println("User Approved Successfully: " + userId);
    }

    @Test(priority = 4)
    public void testGetAdminProfile() {
        Response response = AdminRequestBuilder.getAdminProfile();
        
        assertEquals(response.getStatusCode(), 200, "Expected status code 200 for getting admin profile");
        assertNotNull(response.jsonPath().getString("data"), "Profile data should not be null");
        System.out.println("Admin Profile Retrieved Successfully");
    }
}
