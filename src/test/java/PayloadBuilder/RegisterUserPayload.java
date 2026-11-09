package PayloadBuilder;

import org.json.simple.JSONObject;

public class RegisterUserPayload {

    public static JSONObject registerUserPayload(String firstName, String lastName, String email, String password, String confirmPassword, String groupId) {
        JSONObject payload = new JSONObject();
        payload.put("firstName", firstName);
        payload.put("lastName", lastName);
        payload.put("email", email);
        payload.put("password", password);
        payload.put("confirmPassword", confirmPassword);
        payload.put("groupId", groupId);
        return payload;
    }
}
