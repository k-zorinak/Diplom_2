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
public class BaseOrder {
    protected static final GenerateOrderData generateOrderData = new GenerateOrderData();
    private static final GenerateUserData generateUserData = new GenerateUserData();

    public static OrderAction orderAction = new OrderAction();
    public UserAction userAction = new UserAction();
    public static OrderCard orderCard;
    public static UserCard userCard;

    @Step("Создание заказа используя все ингридиенты")
    public static void createOrderAllIngredients() {
        generateOrderData.idIngredients();
        orderCard = new OrderCard(
                generateOrderData.getIngredients()
        );
    }

    @Step("Создание заказа без ингридиентов")
    public static void createOrderUseNotIngredients() {
        orderCard = new OrderCard(
                generateOrderData.getIngredients()
        );
    }

    @Step("Создание и заполнение карточки заказа с неверным хешем ингридиентов (при помощи полей)")
    public static void generateCustomUserData() {
        orderCard = new OrderCard(INGREDIENTS_1);
    }

    @Step("Создание и заполение карточки пользователя (email + password + name)")
    public static void generateEmailPassNameUserData() {
        generateUserData.generateEmailPassName();
        userCard = new UserCard(
                generateUserData.getUserEmail(),
                generateUserData.getUserPassword(),
                generateUserData.getUserName());
    }

}
