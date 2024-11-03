import base.BaseOrderTest;
import constants.ErrorMessage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;

@Feature("Создание заказа - POST /api/orders")
public class CreateOrderTest extends BaseOrderTest {

    @Test
    @DisplayName("Отправка корректного POST запроса /api/orders c авторизацией")
    @Description("Удачное создание заказа для /api/orders")
    public void createOrderHappyLogInTest() {
        generateEmailPassNameUserData();
        createOrderAllIngredients();
        Response response = orderAction.postRequestCreateOrderLogIn(orderCard, userAction.getUserInfo(userCard));
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.SUCCESS))
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Отправка корректного POST запроса /api/orders без авторизации")
    @Description("Удачное создание заказа для /api/orders")
    public void createOrderHappyNotLogInTest() {
        generateEmailPassNameUserData();
        createOrderAllIngredients();
        Response response = orderAction.postRequestCreateOrderNotLogIn(orderCard);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.SUCCESS))
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Отправка корректного POST запроса /api/orders без ингредиентов")
    @Description("Создать заказ без ингредиентов нельзя /api/orders")
    public void createOrderNotIngredients() {
        generateEmailPassNameUserData();
        createOrderUseNotIngredients();
        Response response = orderAction.postRequestCreateOrderLogIn(orderCard, userAction.getUserInfo(userCard));
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.NOT_SUCCESS))
                .and().assertThat().body("message", Matchers.equalTo(ErrorMessage.NOT_TRANSFER_INGREDIENTS))
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Отправка корректного POST запроса /api/orders c передачей невалидного хеша ингредиента")
    @Description("Создать заказ с невалдиным хешем нельзя /api/orders")
    public void createOrder() {
        generateEmailPassNameUserData();
        generateCustomUserData();
        Response response = orderAction.postRequestCreateOrderLogIn(orderCard, userAction.getUserInfo(userCard));
        response.then().assertThat()
                .statusCode(SC_INTERNAL_SERVER_ERROR);
    }

    @After
    public void deleteUser() {

        if (userAction.getUserToken(userCard) != null) {
            userAction.deleteRequestRemoveUser(userCard);
        }
    }
}
