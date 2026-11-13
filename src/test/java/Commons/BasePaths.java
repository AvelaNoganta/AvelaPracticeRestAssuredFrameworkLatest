package Commons;

public class BasePaths {
    public static final String BASE_URL = "https://www.ndosiautomation.co.za/APIDEV";
    
    // Authentication endpoints
    public static final String LOGIN_PATH = "/login";
    public static final String REGISTER_PATH = "/register";
    public static final String PROFILE_PATH = "/profile";
    
    // Admin endpoints
    public static final String APPROVE_USER_PATH = "/admin/users/{userId}/approve";
}
