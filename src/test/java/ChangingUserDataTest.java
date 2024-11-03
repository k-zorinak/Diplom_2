import base.BaseUserTest;
import constants.ErrorMessage;
import constants.UserFields;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import resources.UserCard;

import static org.apache.http.HttpStatus.*;
@Feature("Изменение данных пользователя - PATCH /api/auth/user")
public class ChangingUserDataTest extends BaseUserTest {

    @Test
    @DisplayName("Отправка GET запроса /api/auth/user для получения данных о пользователе")
    @Description("Данные получены")
    public void getUserInfoTest() {
        generateEmailPassNameUserData();
        Response response = userAction.getRequestUserInfo(userAction.getUserInfo(userCard));
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.SUCCESS))
                .and().assertThat().body("user.email", Matchers.equalTo(userCard.getEmail().toLowerCase()))
                .and().assertThat().body("user.name", Matchers.equalTo(userCard.getName()))
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Отправка PATCH запроса /api/auth/user для изменения данных о пользователе")
    @Description("Данные успешно изменены")
    public void patchUserInfoTest() {
        generateEmailPassNameUserData();
        String password = userCard.getPassword();
        Response response = userAction.patchRequestUserInfo(userAction.getUserInfo(userCard), generateEmailNameUserData());
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.SUCCESS))
                .and().assertThat().body("user.email", Matchers.equalTo(userCard.getEmail().toLowerCase()))
                .and().assertThat().body("user.name", Matchers.equalTo(userCard.getName()))
                .statusCode(SC_OK);
        userCard.setPassword(password);
    }

    @Test
    @DisplayName("Отправка PATCH запроса /api/auth/user без авторизации")
    @Description("Для изменения требуется авторизация")
    public void patchUserInfoNotTokenTest() {
        generateEmailPassNameUserData();
        Response response = userAction.patchRequestUserInfoNotToken(userCard);
        userAction.getUserInfo(userCard);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.NOT_SUCCESS))
                .and().assertThat().body("message", Matchers.equalTo(ErrorMessage.NOT_LOGIN_PATCH_USER))
                .statusCode(SC_UNAUTHORIZED);
    }

    @Test
    @DisplayName("Отправка PATCH запроса /api/auth/user с использованием занятой почты")
    @Description("Пользователь с таким адресом уже существует")
    public void patchUserInfoEmailAlreadyUseTest() {
        generateEmailPassNameUserData();
        UserCard alreadyEmailUse = new UserCard(UserFields.EMAIL_USE, UserFields.NAME_USE);
        Response response = userAction.patchRequestUserInfo(userAction.getUserInfo(userCard), alreadyEmailUse);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.NOT_SUCCESS))
                .and().assertThat().body("message", Matchers.equalTo(ErrorMessage.EMAIL_ALREADY_USE))
                .statusCode(SC_FORBIDDEN);
    }

    @After
    public void deleteUser() {

        if (userAction.getUserToken(userCard) != null) {
            userAction.deleteRequestRemoveUser(userCard);
        }
    }
}
