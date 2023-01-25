package main.products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Telephone extends Technics {
    private String model;

    public Telephone(String series, String screenType, String price, String model) {
        super(series, screenType, price);
        this.model = model;
        technicsType = TechnicsType.TELEPHONE;
    }

    @Override
    public String getDetails() {
        return "Type: " + getTechnicsType() + "; Series: " + getSeries() + "; Model: " + getModel() +
                "; ScreenType: " + getScreenType() + "; Price: " + getPrice();
    }
}
