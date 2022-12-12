package order;

import courier.BaseClient;

import io.restassured.response.ValidatableResponse;

public class OrderClient extends BaseClient {

    private final String ROOT = "/orders";

    public ValidatableResponse create(Order order) {
        return getSpec()
                .body(order)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    public ValidatableResponse get() {
        return getSpec()
                .when()
                .get(ROOT)
                .then().log().all();
    }
}
