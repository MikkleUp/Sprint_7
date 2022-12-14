package courier;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

@Data
public class CourierCredentials {

    private String login;
    private String password;

    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierCredentials from(Courier courier) {
        return new CourierCredentials(courier.getLogin(), courier.getPassword());
    }

    public static CourierCredentials withoutLoginFrom(Courier courier) {
        return new CourierCredentials("", courier.getPassword());
    }

    public static CourierCredentials withoutPasswordFrom(Courier courier) {
        return new CourierCredentials(courier.getLogin(), "");
    }

    public static CourierCredentials withoutFieldsFrom(Courier courier) {
        return new CourierCredentials("", "");
    }

    public static CourierCredentials withIncorrectLoginFrom(Courier courier) {
        return new CourierCredentials(courier.getLogin() + RandomStringUtils.randomAlphanumeric(1), courier.getPassword());
    }

    public static CourierCredentials withIncorrectPasswordFrom(Courier courier) {
        return new CourierCredentials(courier.getLogin(), courier.getPassword() + RandomStringUtils.randomAlphanumeric(1));
    }
}
