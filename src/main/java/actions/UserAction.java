package actions;

import constants.PathApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import resources.UserCard;

import static io.restassured.RestAssured.given;

public class UserAction extends BaseApi {
    public UserAction() {
    }

    public Response postRequestCreateUser(UserCard obj) {
        return given(requestSpecification())
                .body(obj)
                .when()
                .post(PathApi.CREATE_USER);
    }

    public Response postRequestLogIn(UserCard obj) {
        return given(requestSpecification())
                .body(obj)
                .when()
                .post(PathApi.LOGIN_USER);
    }

    public Response getRequestUserInfo(Object obj) {
        return given(requestSpecification())
                .header("Authorization", obj)
                .when()
                .get(PathApi.GET_INFO_USER);
    }

    public Response patchRequestUserInfo(String obj, UserCard obj1) {
        return given(requestSpecification())
                .header("Authorization", obj)
                .body(obj1)
                .when()
                .patch(PathApi.PATCH_USER);
    }

    public Response patchRequestUserInfoNotToken(UserCard obj) {
        return given(requestSpecification())
                .body(obj)
                .when()
                .patch(PathApi.PATCH_USER);
    }

    @Step("Удаление пользователя")
    public Response deleteRequestRemoveUser(UserCard userCard) {
        return deleteRequestRemoveUserToken(getUserToken(userCard));
    }

    @Step("Узнать токен пользователя по его логину и паролю")
    public String getUserToken(UserCard userCard) {
        Response response = postRequestLogIn(userCard);
        return response.jsonPath().getString("accessToken");
    }

    @Step("Получить токен")
    public String getUserInfo(UserCard userCard) {
        Response response = postRequestCreateUser(userCard);
        return response.jsonPath().getString("accessToken");
    }

    @Step("Удалить пользователя, DELETE запрос - /api/auth/user + токен")
    public Response deleteRequestRemoveUserToken(String userToken) {
        return given(requestSpecification())
                .header("Authorization", userToken)
                .delete(PathApi.DELETE_USER);
    }
}
