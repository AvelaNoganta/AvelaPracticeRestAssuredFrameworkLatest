package PayloadBuilder;

import org.json.simple.JSONObject;

public class AdminPayload {
    public static JSONObject adminLoginPayload(String email, String password) {
        JSONObject payload = new JSONObject();
        payload.put("email", email);
        payload.put("password", password);
        return payload;
    }
}
