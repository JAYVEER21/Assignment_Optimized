

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
    public class BOOK {


        private final String baseURI = "https://bookcart.azurewebsites.net/api";
        private final String accessToken = "KqcL7s998JrfFHRP";
        private final APIutil requestUtil = new APIutil(accessToken);

        @Test
        void addCartItem() {
            JSONObject requestBody = new JSONObject();
            requestBody.put("productId", "1");
            requestBody.put("quantity", "85");

            requestUtil.sendRequest("POST", baseURI + "/ShoppingCart/AddToCart/1/85", requestBody, 200);
        }

        @Test
        void getCartItem() {
            requestUtil.sendRequest("GET", baseURI + "/ShoppingCart/1", null, 200);
        }

        @Test
        void deleteCartItem() {
            requestUtil.sendRequest("DELETE", baseURI + "/ShoppingCart/1", null, 200);
        }
    }


