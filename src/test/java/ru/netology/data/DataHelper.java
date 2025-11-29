package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;


public class DataHelper {
    private static final Faker FAKER = new Faker();

    private DataHelper() {
    }

    public static AuthInfo getAuthInfo () {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static String generateRandomLogin() {
        return FAKER.name().username();
    }

    public static String generateRandomPassword() {
        return FAKER.internet().password();
    }

    public static AuthInfo getRandomUser() {
        return new AuthInfo(generateRandomLogin(), generateRandomPassword());
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String cardId;
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }
}