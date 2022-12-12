import courier.Courier;
import courier.CourierClient;
import courier.CourierCredentials;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourierCreationTest {

    Courier courier;
    CourierClient courierClient;
    CourierCredentials creds;


    @Before
    public void setUp() {
        courier = Courier.getCourier();
        courierClient = new CourierClient();
        creds = CourierCredentials.from(courier);
    }

    @Test
    @DisplayName("Можно создать курьера")
    @Description("Проверка создания курьера при передаче в теле запроса валидных значений: login, password, firstName.")
    public void courierIsCreated() {
        courierClient
                .create(courier)
                .statusCode(201);
        courierClient.tearDown(creds);
    }

    @Test
    @DisplayName("Создание курьера с обязательными полями")
    @Description("Проверка создания курьера при передаче в теле запроса валидных значений: login и password")
    public void courierIsCreatedWithRequiredFields() {
        courier = Courier.getCourierWithoutFirstName();
        courierClient
                .create(courier)
                .statusCode(201);

        creds = CourierCredentials.from(courier);
        courierClient.tearDown(creds);
    }

    @Test
    @DisplayName("Возвращается ok: true при успешном запросе")
    @Description("Проверка возвращения ok: true в теле ответа при успешном запросе на создание курьера")
    public void courierCreationIsOkResponse() {
        boolean isOk = courierClient.create(courier)
                .statusCode(201)
                .extract()
                .path("ok");
        assertTrue(isOk);
        courierClient.tearDown(creds);
    }

    @Test
    @DisplayName("Сообщение об ошибке при запросе без логина")
    @Description("Проверка возвращения message с сообщением об ошибке в теле ответа при запросе на создание курьера без логина")
    public void checkCourierCreationWithoutLogin() {
        courier = Courier.getCourierWithoutLogin();
        String message = courierClient.create(courier)
                .statusCode(400)
                .extract()
                .path("message");
        assertEquals("Недостаточно данных для создания учетной записи", message);
    }

    @Test
    @DisplayName("Сообщение об ошибке при запросе без пароля")
    @Description("Проверка возвращения message с сообщением об ошибке в теле ответа при запросе на создание курьера без логина")
    public void checkCourierCreationWithoutPassword() {
        courier = Courier.getCourierWithoutPassword();
        String message = courierClient.create(courier)
                .statusCode(400)
                .extract()
                .path("message");
        assertEquals("Недостаточно данных для создания учетной записи", message);
    }

    @Test
    @DisplayName("Невозможность создания двух одиноковых курьеров")
    @Description("Проверка невозможность создания двух курьеров с одинаковыми значениями полей: login, password, firstName.")
    public void checkTwoIdenticalCouriersCreation() {
        courierClient.create(courier);
        courierClient.create(courier)
                .statusCode(409);
        courierClient.tearDown(creds);
    }

    @Test
    @DisplayName("Сообщение об ошибке при создании курьеров с одинаковыми значениями login")
    @Description("Проверка возвращения message с сообщением об ошибке в теле ответа при запросе на создание курьера с существуюшим логином")
    public void checkMessageCouriersCreationWithIdenticalLogin() {
        courier = Courier.getCourierWithoutFirstName();
        creds = CourierCredentials.from(courier);
        courierClient.create(courier);
        String message = courierClient.create(courier)
                .statusCode(409)
                .extract()
                .path("message");
        assertEquals("Этот логин уже используется. Попробуйте другой.", message);
        courierClient.tearDown(creds);
    }
}
