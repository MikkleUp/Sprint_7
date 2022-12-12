package courier;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

@Data
public class Courier {

    private String login;
    private String password;
    private String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public static Courier getCourier() {
        return new Courier(
                RandomStringUtils.randomAlphabetic(10),
                "1234",
                RandomStringUtils.randomAlphabetic(10)
        );
    }

    public static Courier getCourierWithoutLogin() {
        return new Courier(
                "",
                "1234",
                RandomStringUtils.randomAlphabetic(10)
        );
    }

    public static Courier getCourierWithoutPassword() {
        return new Courier(
                RandomStringUtils.randomAlphabetic(10),
                "",
                RandomStringUtils.randomAlphabetic(10)
        );
    }

    public static Courier getCourierWithoutFirstName() {
        return new Courier(
                RandomStringUtils.randomAlphabetic(10),
                "1234",
                ""
        );
    }
}
