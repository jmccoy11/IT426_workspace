package encryption_practice;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.jasypt.util.password.BasicPasswordEncryptor;

import javax.swing.plaf.basic.BasicPasswordFieldUI;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EncryptionPractice {
    
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        
        //prompt for username/password
        System.out.println("Username:");
        String username = console.nextLine();
    
        System.out.println("Password:");
        String password = console.nextLine();
    
        BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
        password = encryptor.encryptPassword(password);
        
        //show the user
        System.out.println("Encrypted password: " + password);
    
        System.out.println("Confirm password:");
        String confirm = console.nextLine();
    
        if (encryptor.checkPassword(confirm, password)) {
            System.out.println("Password recognized!");
        } else {
            System.out.println("Password not recognized!");
        }
    
//        String message = "Hello world!";
//        switch (message) {
//            case "Hello":
//                break;
//            case "Hello world":
//                break;
//            case "Hello world!":
//                break;
//        }
//
//        try (Scanner fileReader = new Scanner(new FileInputStream("input.txt"))) {
//
//        } catch (FileNotFoundException exc) {
//
//        }
    }
}
