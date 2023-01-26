package main.products;

import lombok.Getter;
import lombok.Setter;
import main.service.Client;

import java.util.UUID;

@Getter
@Setter

public class Customer {
    private final String id;
    private Client name;
    private String email;
    private int age;

    public Customer() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Name:" + getName() + "; ID:" +
                getId() + "; Email:" + getEmail() + "; Age:" + getAge();
    }

}
