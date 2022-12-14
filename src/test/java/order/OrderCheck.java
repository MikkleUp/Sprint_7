package order;

import java.util.HashMap;
import java.util.List;

public class OrderCheck {

    private boolean listIsNotEmptyAndNotNull;

    public boolean check(List<HashMap> ordersList) {
        listIsNotEmptyAndNotNull = false;
        ordersList.forEach(order -> {
            if (order != null && !order.isEmpty()) {
                listIsNotEmptyAndNotNull = true;
            }
        });
        return listIsNotEmptyAndNotNull;
    }
}
