package main.service;

import main.products.Customer;
import main.util.RandomGenerator;

import java.util.Random;

public class PersonService {
    private final Random random = new Random();
    RandomGenerator randomGenerator = new RandomGenerator();

    public Customer createClient() {
        Customer customer = new Customer();
        customer.setName(RandomGenerator.randomName());
        customer.setAge(random.nextInt(16, 75));
        customer.setEmail(randomGenerator.randomString() + "@gmail.com");
        return customer;
    }
}
