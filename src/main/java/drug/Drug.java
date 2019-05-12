package drug;

import java.math.BigDecimal;

public class Drug {

    private DrugType name;
    private BigDecimal price;

    public Drug(DrugType name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public DrugType getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
