import courier.Courier;
import courier.CourierClient;
import courier.CourierCredentials;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CourierLoginTest {

    Courier courier;
    CourierClient courierClient;
    CourierCredentials creds;
    private int courierId;

    @Before
    public void setUp() {
        courier = Courier.getCourier();
        courierClient = new CourierClient();
        courierClient.create(courier);
        creds = CourierCredentials.from(courier);
    }

    @After
    public void tearDown() {
        courierId = courierClient.getId(creds);
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Происходит авторизация курьера")
    @Description("Проверка авторизация курьера при передаче в теле запроса валидных значений: login и password")
    public void courierIsAuthorized() {
        courierClient.login(creds)
                .statusCode(200);
    }

    @Test
    @DisplayName("Возвращение id при успешном запросе")
    @Description("Проверка возвращения id в теле ответа при успешном запросе на авторизацию курьера")
    public void courierIdIsInResponse() {
        courierId = courierClient.login(creds)
                .extract().path("id");
        assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Невозможность авторизации без логина")
    @Description("Проверка возвращения message с сообщением об ошибке в теле ответа " +
            "при запросе на авторизацию курьера без значения поля login")
    public void courierLoginMessageCheckWithoutLogin() {
        CourierCredentials newCreds = CourierCredentials.withoutLoginFrom(courier);
        String message = courierClient.login(newCreds)
                .statusCode(400)
                .extract().path("message");
        assertEquals("Недостаточно данных для входа", message);
    }

    @Test
    @DisplayName("Невозможность авторизации без пароля")
    @Description("Проверка возвращения message с сообщением об ошибке в теле ответа " +
            "при запросе на авторизацию курьера без значения поля password")
    public void courierLoginMessageCheckWithoutPassword() {
        CourierCredentials newCreds = CourierCredentials.withoutPasswordFrom(courier);
        String message = courierClient.login(newCreds)
                .statusCode(400)
                .extract().path("message");
        assertEquals("Недостаточно данных для входа", message);
    }

    @Test
    @DisplayName("Невозможность авторизации без логина и пароля")
    @Description("Проверка возвращения message с сообщением об ошибке в теле ответа " +
            "при запросе на авторизацию курьера без полей: login и password")
    public void courierLoginMessageCheckWithoutFieldValues() {
        CourierCredentials newCreds = CourierCredentials.withoutFieldsFrom(courier);
        String message = courierClient.login(newCreds)
                .statusCode(400)
                .extract().path("message");
        assertEquals("Недостаточно данных для входа", message);
    }

    @Test
    @DisplayName("Сообщение об ошибке при авторизации с некорректным логином")
    @Description("Проверка возвращения message с сообщением об ошибке в теле ответа " +
            "при запросе на авторизацию курьера с некорректным полем login")
    public void courierLoginWithIncorrectLogin() {
        CourierCredentials newCreds = CourierCredentials.withIncorrectLoginFrom(courier);
        String message = courierClient.login(newCreds)
                .statusCode(404)
                .extract().path("message");
        assertEquals("Учетная запись не найдена", message);
    }

    @Test
    @DisplayName("Сообщение об ошибке при авторизации с некорректным паролем")
    @Description("Проверка возвращения message с сообщением об ошибке в теле ответа " +
            "при запросе на авторизацию курьера с некорректным полем password")
    public void courierLoginWithIncorrectPassword() {
        CourierCredentials newCreds = CourierCredentials.withIncorrectPasswordFrom(courier);
        String message = courierClient.login(newCreds)
                .statusCode(404)
                .extract().path("message");
        assertEquals("Учетная запись не найдена", message);
    }
}
