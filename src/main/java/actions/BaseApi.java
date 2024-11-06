package actions;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseApi {
    public RequestSpecification requestSpecification() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        return RestAssured.given()
                .header("Content-type", "application/json");
    }
}
