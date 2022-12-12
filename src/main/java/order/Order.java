package order;

import org.apache.commons.lang3.RandomStringUtils;
import lombok.Data;

@Data
public class Order {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] colour;

    public Order(String firstName, String lastName, String address, String metroStation, String phone, int rentTime,
                 String deliveryDate, String comment, String[] colour) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address =  address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment =  comment;
        this.colour = colour;
    }

    public static Order getOrderBlackGrey() {
        return new Order(
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphanumeric(15),
                RandomStringUtils.randomNumeric(2),
                RandomStringUtils.randomNumeric(11),
                2,
                "2022-12-12",
                RandomStringUtils.randomAlphanumeric(10),
                new String[]{"BLACK", "GREY"}
        );
    }

    public static Order getOrderBlack() {
        return new Order(
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphanumeric(15),
                RandomStringUtils.randomNumeric(2),
                RandomStringUtils.randomNumeric(11),
                2,
                "2022-12-12",
                RandomStringUtils.randomAlphanumeric(10),
                new String[]{"BLACK"}
        );
    }

    public static Order getOrderGrey() {
        return new Order(
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphanumeric(15),
                RandomStringUtils.randomNumeric(2),
                RandomStringUtils.randomNumeric(11),
                2,
                "2022-12-12",
                RandomStringUtils.randomAlphanumeric(10),
                new String[]{"GREY"}
        );
    }

    public static Order getOrderAny() {
        return new Order(
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphanumeric(15),
                RandomStringUtils.randomNumeric(2),
                RandomStringUtils.randomNumeric(11),
                2,
                "2022-12-12",
                RandomStringUtils.randomAlphanumeric(10),
                new String[]{}
        );
    }
}
