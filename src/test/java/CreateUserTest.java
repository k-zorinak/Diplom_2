import base.BaseUser;
import constants.ErrorMessage;
import constants.UserFields;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;

@Feature("Создание пользователя - POST /api/auth/register")
public class CreateUserTest extends BaseUser {

    @Test
    @DisplayName("Отправка корректного POST запроса /api/auth/register")
    @Description("Удачное создание для /api/auth/register")
    public void createUserHappyPathTest() {
        generateEmailPassNameUserData();
        Response response = userAction.postRequestCreateUser(userCard);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.SUCCESS))
                .and().statusCode(SC_OK);
    }

    @Test
    @DisplayName("Отправка POST запроса /api/auth/register дважды")
    @Description("Невозможно создать одного и того же пользователя дважды")
    public void createUserTwiceTest() {
        generateEmailPassNameUserData();
        userAction.postRequestCreateUser(userCard);
        Response response = userAction.postRequestCreateUser(userCard);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.NOT_SUCCESS))
                .and().body("message", Matchers.equalTo(ErrorMessage.EXIST_LOGIN))
                .and().statusCode(SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Отправка POST запроса /api/auth/register без поля email")
    @Description("Невозможно создать пользователя без email")
    public void createUserEmailNullValueTest() {
        generateCustomUserData(UserFields.PASSWORD, UserFields.NAME);
        Response response = userAction.postRequestCreateUser(userCard);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.NOT_SUCCESS))
                .and().body("message", Matchers.equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOR_LOG_IN))
                .and().statusCode(SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Отправка POST запроса /api/auth/register без поля password")
    @Description("Невозможно создать курьера без password")
    public void createUserPasswordNullValueTest() {
        generateCustomUserData(UserFields.EMAIL, UserFields.NAME);
        Response response = userAction.postRequestCreateUser(userCard);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.NOT_SUCCESS))
                .and().body("message", Matchers.equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOR_LOG_IN))
                .and().statusCode(SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Отправка POST запроса /api/auth/register без поля name")
    @Description("Невозможно создать курьера без name")
    public void createUserNameNullValueTest() {
        generateCustomUserData(UserFields.EMAIL, UserFields.PASSWORD);
        Response response = userAction.postRequestCreateUser(userCard);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.NOT_SUCCESS))
                .and().body("message", Matchers.equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOR_LOG_IN))
                .and().statusCode(SC_FORBIDDEN);
    }

    @After
    public void deleteUser() {

        if (userAction.getUserToken(userCard) != null) {
            userAction.deleteRequestRemoveUser(userCard);
        }
    }
}
