package base;

import actions.GenerateOrderData;
import actions.GenerateUserData;
import actions.OrderAction;
import actions.UserAction;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import resources.OrderCard;
import resources.UserCard;

import static constants.OrderFields.INGREDIENTS_1;

@Getter
@Setter
public class BaseOrderTest {
    protected final GenerateOrderData generateOrderData = new GenerateOrderData();
    private final GenerateUserData generateUserData = new GenerateUserData();

    public OrderAction orderAction = new OrderAction();
    public UserAction userAction = new UserAction();
    public OrderCard orderCard;
    public UserCard userCard;

    @Step("Создание заказа используяю все ингредиенты")
    public void createOrderAllIngredients() {
        generateOrderData.IdIngredients();
        orderCard = new OrderCard(
                generateOrderData.getIngredients()
        );
    }

    @Step("Создание заказа без ингредиентов")
    public void createOrderUseNotIngredients() {
        orderCard = new OrderCard(
                generateOrderData.getIngredients()
        );
    }

    @Step("Создание и заполнение карточки заказа с неверным хешем ингредиентов (при помощи полей)")
    public void generateCustomUserData() {
        orderCard = new OrderCard(INGREDIENTS_1);
    }

    @Step("Создание и заполение карточки пользователя (email + password + name)")
    public void generateEmailPassNameUserData() {
        generateUserData.generateEmailPassName();
        userCard = new UserCard(
                generateUserData.getUserEmail(),
                generateUserData.getUserPassword(),
                generateUserData.getUserName());
    }

}
