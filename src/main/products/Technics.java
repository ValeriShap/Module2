package main.products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Technics {
    private String series;
    private String screenType;
    private Double price;
    protected TechnicsType technicsType;

    public Technics() {
    }

    public Technics(String series, String screenType, String price) {
        this.series = series;
        this.screenType = screenType;
        this.price = Double.parseDouble(price);
    }

    public String getDetails() {
        return "Type: " + getTechnicsType() + "; Series: " + getSeries() +
                "; ScreenType: " + getScreenType() + "; Price: " + getPrice();
    }
}
