package pw.latematt.accountspammer.example;

import pw.latematt.accountspammer.AccountSpammer;

import java.util.Arrays;
import java.util.Random;

/**
 * An example of how Account Spammer could be used.
 *
 * @author Matthew
 */
public class SpamExample {
    /**
     * Generate a string with random letters and numbers.
     *
     * @param length How long the generated string should be.
     * @return The generated string with random letters and numbers.
     */
    public static String generateRandomString(int length) {
        String randomString = "";
        char[] characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        for (int generatedCharacters = 0; generatedCharacters < length; generatedCharacters++) {
            randomString += characters[new Random().nextInt(characters.length)];
        }
        return randomString;
    }

    /**
     * Main program method
     *
     * @param args Program arguments
     */
    public static void main(String[] args) {
        for (int accountsCreated = 0; accountsCreated < 10; accountsCreated++) {
            AccountSpammer accountSpammer = new AccountSpammer("http://website.com/register.php", "username=" + generateRandomString(10), "password=" + generateRandomString(10));
            accountSpammer.postData();
        }
    }
}
