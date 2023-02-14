package niffler.api;

import io.restassured.response.Response;
import niffler.api.RestAssuredService;
import niffler.model.UserJson;

public class NifflerUserDataServiceRestAssured extends RestAssuredService {

    String nifflerUserdataUri = "http://127.0.0.1:8089";

    public Response updateUser(UserJson userJson) {
        return setup()
                .baseUri(nifflerUserdataUri)
                .body(userJson)
                .post("/updateUserInfo");
    }
}
