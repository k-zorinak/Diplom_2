package base;

import actions.GenerateUserData;
import actions.UserAction;
import constants.UserFields;
import io.qameta.allure.Step;
import resources.UserCard;

public class BaseUser {
    private static final GenerateUserData generateUserData = new GenerateUserData();
    public static UserAction userAction = new UserAction();
    public static UserCard userCard;

    @Step("Создание и заполение карточки пользователя (email + password + name)")
    public static void generateEmailPassNameUserData() {
        generateUserData.generateEmailPassName();
        userCard = new UserCard(
                generateUserData.getUserEmail(),
                generateUserData.getUserPassword(),
                generateUserData.getUserName());
    }

    @Step("Создание и заполение данных для изменения (email + name)")
    public static Object generateEmailNameUserData() {
        generateUserData.generateEmailPassName();
        userCard = new UserCard(
                generateUserData.getUserEmail(),
                generateUserData.getUserName());
        return userCard;
    }

    @Step("Создание и заполнение карточки пользователя случайными данными (при помощи полей)")
    public void generateCustomUserData(String firstField, String secondField) {
        generateUserData.generateEmailPassName();
        userCard = new UserCard();
        fillField(firstField);
        fillField(secondField);
    }

    private void fillField(String field) {
        switch (field) {
            case UserFields.EMAIL:
                userCard.setEmail(generateUserData.getUserEmail());
                break;
            case UserFields.PASSWORD:
                userCard.setPassword(generateUserData.getUserPassword());
                break;
            case UserFields.NAME:
                userCard.setName(generateUserData.getUserName());
                break;
        }
    }
}
