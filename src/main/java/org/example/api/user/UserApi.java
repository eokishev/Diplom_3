package org.example.api.user;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.example.Constants.*;
import static org.hamcrest.Matchers.equalTo;

public class UserApi {
    @Step("Отправить запрос на регистрацию /api/auth/register")
    public static void sendPostCreateUser(CreateUser newUser) {
        given().header(HEADER_TYPE, HEADER_JSON).body(newUser).post(CREATE_USER)
                .then().body("success", equalTo(true)).statusCode(SC_OK);
    }

    @Step("Авторизация пользователя /api/auth/login")
    public static Response responseSendPostLoginUser(Login login) {
        return given()
                .header(HEADER_TYPE, HEADER_JSON)
                .body(login)
                .post(LOGIN_USER);
    }

    @Step("Получить accessToken")
    public static String getAccessToken(Response response) {
        return response.body().as(UserCreateResponse.class).getAccessToken();
    }

    @Step("Удаление пользователя /api/auth/user")
    public static Response sendDeleteUser(String accessToken) {
        return given().header(HEADER_AUTHORIZATION, accessToken).delete(USER);
    }

    @Step("Проверка тела и статуса")
    public static void verificationUserSuccessTrue(Response response, String message, int statusCode) {
        response.then().assertThat()
                .body("success", equalTo(true))
                .body("message", equalTo(message))
                .statusCode(statusCode);
    }

    @Step("Создание пользователя")
    public static void createUser() {
        RestAssured.baseURI = BASE_URL;
        CreateUser newUser = new CreateUser(USER_EMAIL, USER_PASSWORD, USER_NAME);
        sendPostCreateUser(newUser);
    }

    @Step("Удаление пользователя")
    public static void deleteUser() {
        RestAssured.baseURI = BASE_URL;
        Login login = new Login(USER_EMAIL, USER_PASSWORD);
        Response authorization = responseSendPostLoginUser(login);
        String accessToken = getAccessToken(authorization);
        if (accessToken != null) {
            Response delete = sendDeleteUser(accessToken);
            verificationUserSuccessTrue(delete, "User successfully removed", SC_ACCEPTED);
        }
    }
}
