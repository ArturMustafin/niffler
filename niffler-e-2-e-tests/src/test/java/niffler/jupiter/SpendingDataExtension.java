package niffler.jupiter;

import niffler.testData.Spend;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class SpendingDataExtension implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) {
        given()
                .contentType(JSON)
                .body(Spend.getRandom())
                .when().log().all()
                .post("http://127.0.0.1:8093/addSpend")
                .then()
                .statusCode(201);
    }

}
