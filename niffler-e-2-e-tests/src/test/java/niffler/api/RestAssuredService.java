package niffler.api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

public class RestAssuredService {

    protected RequestSpecification setup() {
        return RestAssured
                .given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .contentType(JSON)
                .when();
    }

}
