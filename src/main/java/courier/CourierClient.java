package courier;

import io.restassured.response.ValidatableResponse;

public class CourierClient extends BaseClient {

    private final String ROOT = "/courier";
    private final String COURIER = ROOT + "/{courierId}";
    private final String LOGIN = ROOT + "/login";
    private int courierId;

    public ValidatableResponse create(Courier courier) {
        return getSpec()
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    public ValidatableResponse login(CourierCredentials creds) {
        return getSpec()
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all();
    }

    public int getId (CourierCredentials creds){
        return login(creds)
                .extract()
                .path("id");
    }

    public void delete(int courierId) {
        getSpec()
                .pathParam("courierId", courierId)
                .when()
                .delete(COURIER)
                .then().log().all();
    }

    public void tearDown(CourierCredentials creds) {
        courierId = getId(creds);
        delete(courierId);
    }
}
