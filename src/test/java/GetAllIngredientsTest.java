import base.BaseOrder;
import constants.ErrorMessage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;

@Feature("Получение данных об ингредиентах - GET /api/ingredients")
public class GetAllIngredientsTest extends BaseOrder {
    @Test
    @DisplayName("Отправка GET запроса /api/ingredients для получения данных об ингредиентах")
    @Description("Данные получены")
    public void getAllIngredientsTest() {
        Response response = orderAction.getRequestAllIngredients();
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessage.SUCCESS))
                .statusCode(SC_OK);
    }

}
