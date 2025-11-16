package christmas.domain.model;

import javax.xml.namespace.QName;

public class OrderLine {
    private final String name;
    private final Integer qty;

    public OrderLine(String name, Integer qty) {
        this.name = name;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public Integer getQty() {
        return qty;
    }
}
