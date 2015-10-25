package pw.latematt.accountspammer.example;

import pw.latematt.accountspammer.AccountSpammer;

/**
 * An example of how to use AccountSpammer#getReply
 *
 * @author Matthew
 */
public class ReplyExample {
    /**
     * Main program method
     *
     * @param args Program arguments 
     */
    public static void main(String args[]) {
        AccountSpammer latemattpwExample = new AccountSpammer("http://rxdy.gq/gauth/check.php", "username=latematt", "authCode=047774");
        latemattpwExample.postData();
        System.out.println(latemattpwExample.getReply()); // should print with "hello 123"
        // does
    }
}
