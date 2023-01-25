package main.util;

import main.service.Client;

import java.util.Random;

public class RandomGenerator {
    private static final Random random = new Random();

    public String randomString() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 7; i++) {
            int index = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(random.nextInt(alphabet.length() - 1)));
        }
        return sb.toString();
    }

    public static Client randomName() {
        Client[] clients = Client.values();
        return clients[random.nextInt(clients.length)];
    }

}
