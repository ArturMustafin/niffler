package niffler.jupiter;

import niffler.model.SpendModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class SpendingDataExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        given()
                .contentType(JSON)
                .body(SpendModel.randomSpend())
                .when().log().all()
                .post("http://127.0.0.1:8093/addSpend")
                .then()
                .statusCode(201);
    }

}
