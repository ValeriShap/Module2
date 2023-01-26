package main.products;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Getter
@Setter
public class Invoice {
    private List<Technics> technicsCatalog = new ArrayList<>();
    private Customer customer;
    private String type;
    private final Random random = new Random();
    private final int limit = random.nextInt(200000);

    public void saleType() {
        if (getTotalSum() > limit) {
            type = "wholesale";
        } else {
            type = "retail";
        }
    }

    private Double getTotalSum() {
        return technicsCatalog.stream()
                .mapToDouble(Technics::getPrice)
                .sum();
    }

    public void addProduct(Technics technics) {
        technicsCatalog.add(technics);
        saleType();
    }


    @Override
    public String toString() {
        return "Invoice:  Time[ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
                "] User-data[ " + customer + "] Invoice-data:  " + technicsCatalog.stream().map(Technics::getDetails).reduce("", String::concat)
                + " CustomerType: [ " + type + "]";
    }
}

