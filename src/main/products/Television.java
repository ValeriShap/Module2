package main.products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Television extends Technics {
    private String diagonal;
    private String country;

    public Television() {
    }

    public Television(String series, String diagonal, String screenType, String country, String price) {
        super(series, screenType, price);
        this.diagonal = diagonal;
        this.country = country;
        technicsType = TechnicsType.TELEVISION;
    }


    @Override
    public String getDetails() {
        return "Type: " + getTechnicsType() + "; Series: " + getSeries() + "; Diagonal: " + getDiagonal() +
                "; ScreenType: " + getScreenType() + "; Country: " + getCountry() + "; Price: " + getPrice();
    }

}
