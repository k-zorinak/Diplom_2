import base.BaseOrderTest;
import constants.ErrorMessage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

@Feature("Получение заказов конкретного пользователя - GET /api/orders")
public class GetOrdersUserTest extends BaseOrderTest {
    @Test
    @DisplayName("Отправка корректного GET запроса /api/orders c авторизацией")
    @Description("Удачное получение списка заказов для /api/orders")
    public void getOrderUserLogInTest() {
        generateEmailPassNameUserData();
        createOrderAllIngredients();
        orderAction.postRequestCreateOrderLogIn(orderCard, userAction.getUserInfo(userCard));
        Response response = orderAction.getRequestAllOrdersUserLogIn(userAction.getUserToken(userCard));
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.SUCCESS))
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Отправка корректного GET запроса /api/orders без авторизации")
    @Description("Для получения заказов пользователя необходимо авторизоваться /api/orders")
    public void getOrderUserNotLogInTest() {
        generateEmailPassNameUserData();
        createOrderAllIngredients();
        orderAction.postRequestCreateOrderLogIn(orderCard, userAction.getUserInfo(userCard));
        Response response = orderAction.getRequestAllOrdersUserNotLogIn();
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.NOT_SUCCESS))
                .statusCode(SC_UNAUTHORIZED);
    }

    @After
    public void deleteUser() {

        if (userAction.getUserToken(userCard) != null) {
            userAction.deleteRequestRemoveUser(userCard);
        }
    }
}
