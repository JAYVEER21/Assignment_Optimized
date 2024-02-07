

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
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
            requestUtil.sendRequest("DELETE", baseURI + "/ShoppingCart/1", null, 200);
        }
    }


