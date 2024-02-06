import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class PB2Negative {

    private final String baseURI = "https://gorest.co.in";
    private final String accessToken = "b6a35233b6ccd6670029335ffafcbd99dcfa8d80bde9612d1a2c302a2db5297b";
    private final APIutil requestUtil = new APIutil(accessToken);

    @Test
    void postTest() {
        JSONObject userObject = new JSONObject();
        userObject.put("id", 6140500);
        userObject.put("name", "Rev. Mina Varrier");
        userObject.put("email", "rev_varrier_mina@okuneva-becker.test");
        userObject.put("gender", "male");
        userObject.put("status", "active");

        requestUtil.sendRequest("POST", baseURI + "/public/v2/users/", userObject, 200);
    }

    @Test
    void getUser() {
        requestUtil.sendRequest("GET", baseURI + "/public/v2/users/", null, 200);
    }

    @Test
    void deleteTest() {
        requestUtil.sendRequest("DELETE", baseURI + "/public/v2/users/6160611", null, 204);
    }

    @Test
    void getAgain() {
        requestUtil.sendRequest("GET", baseURI + "/public/v2/users/6160611", null, 404);
    }
}

