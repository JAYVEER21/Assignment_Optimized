import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class APIutil {

    private final String accessToken;

    public APIutil(String accessToken) {
        this.accessToken = accessToken;
    }

    public Response sendRequest(String method, String endpoint, JSONObject requestBody, int expectedStatusCode) {
        Response response;

        switch (method.toUpperCase()) {
            case "GET":
                response = given()
                        .header("Authorization", "Bearer " + accessToken)
                        .when()
                        .get(endpoint);
                break;
            case "POST":
                response = given()
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(ContentType.JSON)
                        .body(requestBody.toJSONString())
                        .when()
                        .post(endpoint);
                break;
            case "PUT":
                response = given()
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(ContentType.JSON)
                        .body(requestBody.toJSONString())
                        .when()
                        .put(endpoint);
                break;
            case "DELETE":
                response = given()
                        .header("Authorization", "Bearer " + accessToken)
                        .when()
                        .delete(endpoint);
                break;
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }

        response.then().statusCode(expectedStatusCode).log().all();
        return response;
    }
}
