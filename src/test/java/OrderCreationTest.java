import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import order.Order;
import order.OrderClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertNotEquals;

@RunWith(Parameterized.class)
public class OrderCreationTest {

    Order order;
    OrderClient oderClient;
    private final Order response;

    public OrderCreationTest(Order response) {
        this.response = response;
    }

    @Parameterized.Parameters(name = "Order: {0}")
    public static Object[] getResponse() {
        return new Object[] {
                Order.getOrderBlackGrey(),
                Order.getOrderBlack(),
                Order.getOrderGrey(),
                Order.getOrderAny(),
        };
    }

    @Before
    public void setUp() {
        order = response;
        oderClient = new OrderClient();
    }

    @Test
    @DisplayName("Создание заказов с различными цветами самокатов")
    @Description("Проверка создания заказов с различными цветами самокатов: Черный и Серый, Черный, Серый, без цвета. " +
            "В ответе возвращается номер отслеживания заказа \"track\"")
    public void createOrderWithScooterColour() {
        int track = oderClient.create(order)
                .statusCode(201)
                .extract()
                .path("track");
        assertNotEquals(0, track);
    }
}
