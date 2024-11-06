package constants;

public class PathApi {
    //  Создание пользователя
    //  POST
    public static final String CREATE_USER = "/api/auth/register";
    //  Авторизация
    //  POST
    public static final String LOGIN_USER = "/api/auth/login";
    //  Обновление информации о пользователе
    //  PATCH
    public static final String PATCH_USER = "/api/auth/user";
    //  Создание заказа
    //  POST
    public static final String CREATE_ORDER = "/api/orders";
    //  Получение данных об ингредиентах
    //  GET
    public static final String GET_INGREDIENTS = "/api/ingredients";
    //  Получить заказы конкретного пользователя
    //  GET
    public static final String GET_ORDER_USER = "/api/orders";
    //  Удаление пользователя
    //  DELETE
    public static final String DELETE_USER = "/api/auth/user";
    //  Получить информации о пользователе
    //  GET
    public static final String GET_INFO_USER = "/api/auth/user";
}
