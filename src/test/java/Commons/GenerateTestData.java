package Commons;

import com.github.javafaker.Faker;

public class GenerateTestData {
    private static final Faker faker = new Faker();
    
    public static String adminEmail = "admin@gmail.com";
    public static String adminPassword = "@12345678";

    public static String generateFirstName() {
        return faker.name().firstName();
    }

    public static String generateLastName() {
        return faker.name().lastName();
    }

    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

    public static String generatePassword() {
        return faker.internet().password(8, 16, true, true);
    }

    public static String GroupID  = "0321f54a-64e2-4dbf-acf6-ab9614738149";
}
