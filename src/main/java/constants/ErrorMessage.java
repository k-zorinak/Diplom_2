package constants;

public class ErrorMessage {
    //  Успех
    public static final Boolean SUCCESS = true;
    //  Неудача
    public static final Boolean NOT_SUCCESS = false;
    //  Создание пользователя
    //  Если пользователь существует, вернётся код ответа 403 Forbidden
    public static final String EXIST_LOGIN = "User already exists";
    //  Создание пользователя
    //  Если нет одного из полей, вернётся код ответа 403 Forbidden
    public static final String NOT_ENOUGH_DATA_FOR_LOG_IN = "Email, password and name are required fields";
    //  Авторизация
    //  Если логин или пароль неверные или нет одного из полей, вернётся код ответа 401 Unauthorized
    public static final String INCORRECT_OR_MISSING_LOGIN_OR_PASSWORD = "email or password are incorrect";
    //  Обновление информации о пользователе
    //  Если выполнить запрос без авторизации, вернётся код ответа 401 Unauthorized
    public static final String NOT_LOGIN_PATCH_USER = "You should be authorised";
    //  Обновление информации о пользователе
    //  Если передать почту, которая уже используется, вернётся код ответа 403 Forbidden\
    public static final String EMAIL_ALREADY_USE = "User with such email already exists";
    //  Создание заказа
    //  Если не передать ни один ингредиент, вернётся код ответа 400 Bad Request
    public static final String NOT_TRANSFER_INGREDIENTS = "Ingredient ids must be provided";
    //  Получение заказов конкретного пользователя
    //  Если выполнить запрос без авторизации, вернётся код ответа 401 Unauthorized
  



}
