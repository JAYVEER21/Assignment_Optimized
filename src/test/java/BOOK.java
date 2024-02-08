

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import util.APIutil;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class BOOK {


    private final String baseURI = "https://bookcart.azurewebsites.net/api";
    private final String accessToken = "KqcL7s998JrfFHRP";
    private final APIutil requestUtil = new APIutil(accessToken);

    @Test(dataProvider = "BookCartLogin",dataProviderClass = DataProvider_BOOK.class)
    void addCartItem(String userId,String bookId) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userId", userId);
        requestBody.put("bookId", bookId);

        requestUtil.sendRequest("POST", baseURI + "/ShoppingCart/AddToCart/"+userId+"/"+bookId+"/", requestBody, 200);
    }

    @Test(dataProvider = "BookCartLogin",dataProviderClass = DataProvider_BOOK.class)
    void getCartItem(String userId,String bookId) {
        requestUtil.sendRequest("GET", baseURI + "/ShoppingCart/"+userId, null, 200);
    }

    @Test(dataProvider = "BookCartLogin",dataProviderClass = DataProvider_BOOK.class)
    void deleteCartItem(String userId,String bookId) {
        requestUtil.sendRequest("DELETE", baseURI + "/ShoppingCart/"+userId, null, 200);
    }

    @Test//Validating Response body
    void getBook(){
        Response response=requestUtil.sendRequest("GET",baseURI+"/Book/",null,200);
        response.then().body("[42].bookId",equalTo(85))
                .body("[42].title",equalTo("All of Us with Wings"))
                .body("[42].author",equalTo("Michelle Ruiz Keil "))
                .body("[42].category",equalTo("Fantasy"))
                .body("[42].price",equalTo(555.0F));


    }


}