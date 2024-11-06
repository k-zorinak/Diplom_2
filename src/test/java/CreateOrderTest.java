import base.BaseOrder;
import constants.ErrorMessage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;

import static base.BaseOrder.orderCard;
import static base.BaseOrder.userCard;
import static base.BaseUser.userAction;
import static org.apache.http.HttpStatus.*;

@Feature("Создание заказа - POST /api/orders")
public class CreateOrderTest {

    @Test
    @DisplayName("Отправка корректного POST запроса /api/orders c авторизацией")
    @Description("Удачное создание заказа для /api/orders")
    public void createOrderHappyLogInTest() {
        BaseOrder.generateEmailPassNameUserData();
        BaseOrder.createOrderAllIngredients();
        Response response = BaseOrder.orderAction.postRequestCreateOrderLogIn(orderCard, userAction.getUserInfo(userCard));
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.SUCCESS))
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Отправка корректного POST запроса /api/orders без авторизации")
    @Description("Удачное создание заказа для /api/orders")
    public void createOrderHappyNotLogInTest() {
        BaseOrder.generateEmailPassNameUserData();
        BaseOrder.createOrderAllIngredients();
        Response response = BaseOrder.orderAction.postRequestCreateOrderNotLogIn(orderCard);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.SUCCESS))
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Отправка корректного POST запроса /api/orders без ингредиентов")
    @Description("Создать заказ без ингредиентов нельзя /api/orders")
    public void createOrderNotIngredients() {
        BaseOrder.generateEmailPassNameUserData();
        BaseOrder.createOrderUseNotIngredients();
        Response response = BaseOrder.orderAction.postRequestCreateOrderLogIn(orderCard, userAction.getUserInfo(userCard));
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.NOT_SUCCESS))
                .and().assertThat().body("message", Matchers.equalTo(ErrorMessage.NOT_TRANSFER_INGREDIENTS))
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Отправка корректного POST запроса /api/orders c передачей невалидного хеша ингредиента")
    @Description("Создать заказ с невалдиным хешем нельзя /api/orders")
    public void createOrder() {
        BaseOrder.generateEmailPassNameUserData();
        BaseOrder.generateCustomUserData();
        Response response = BaseOrder.orderAction.postRequestCreateOrderLogIn(orderCard, userAction.getUserInfo(userCard));
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
