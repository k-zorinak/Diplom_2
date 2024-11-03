import base.BaseUserTest;
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

@Feature("Авторизация пользователя - POST /api/auth/login")
public class LogInUserTest extends BaseUserTest {

    @Test
    @DisplayName("Отправка корректного POST запроса /api/auth/login")
    @Description("Удачный вход для /api/auth/login")
    public void logInUserHappyPathTest() {
        generateEmailPassNameUserData();
        userAction.postRequestCreateUser(userCard);
        Response response = userAction.postRequestLogIn(userCard);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.SUCCESS))
                .and().assertThat().body("accessToken", Matchers.notNullValue())
                .and().assertThat().body("refreshToken", Matchers.notNullValue())
                .and().statusCode(SC_OK);
    }

    @Test
    @DisplayName("Отправка POST запроса /api/auth/login c неверными или несуществующими данными")
    @Description("Невозможно авторизоваться под несуществующим пользователем")
    public void noSuccessLogInUserTest() {
        generateEmailPassNameUserData();
        userCard.setEmail("proverka@mail.com");
        userCard.setPassword("qwerty");
        Response response = userAction.postRequestLogIn(userCard);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.NOT_SUCCESS))
                .and().assertThat().body("message", Matchers.equalTo(ErrorMessage.INCORRECT_OR_MISSING_LOGIN_OR_PASSWORD))
                .and().statusCode(SC_UNAUTHORIZED);
    }

    @After
    public void deleteUser() {

        if (userAction.getUserToken(userCard) != null) {
            userAction.deleteRequestRemoveUser(userCard);
        }
    }
}
