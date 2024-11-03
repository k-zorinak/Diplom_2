package actions;

import api.model.AllIngredientsResponse;
import constants.PathApi;
import io.restassured.response.Response;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class OrderAction extends BaseApi {
    public OrderAction() {
    }

    public Response getRequestAllIngredients() {
        return given(RequestSpecification())
                .when()
                .get(PathApi.GET_INGREDIENTS);
    }

    public Response postRequestCreateOrderLogIn(Object obj, Object obj1) {
        return given(RequestSpecification())
                .header("Authorization", obj1)
                .body(obj)
                .when()
                .post(PathApi.CREATE_ORDER);
    }

    public Response getRequestAllOrdersUserLogIn(Object obj) {
        return given(RequestSpecification())
                .header("Authorization", obj)
                .when()
                .get(PathApi.GET_ORDER_USER);
    }

    public Response getRequestAllOrdersUserNotLogIn() {
        return given(RequestSpecification())
                .when()
                .get(PathApi.GET_ORDER_USER);
    }

    public Response postRequestCreateOrderNotLogIn(Object obj) {
        return given(RequestSpecification())
                .body(obj)
                .when()
                .post(PathApi.CREATE_ORDER);
    }

    public List<String> getAllIngredients() {
        Response response = getRequestAllIngredients();
        var allIn = response.as(AllIngredientsResponse.class);
        var id = allIn.getData().stream().map(x -> x.getId()).collect(Collectors.toList());
        return id;
    }
}
