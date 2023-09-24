import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static constans.Consts.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input;

        while (true) {
            System.out.println(MAIN_MENU);
            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                String stringInput = scanner.nextLine();
                System.out.println("Вы ввели строку! " + stringInput);
                input = 8;
            }

            switch (input) {
                case 1 -> {
                    Encryptor encryptor = new Encryptor(new FileService());
                    encryptor.encryption(scanner);
                    System.exit(0);
                }
                case 2 -> {
                    Encryptor encryptor = new Encryptor(new FileService());
                    encryptor.decryption(scanner);
                    System.exit(0);
                }
                case 3 -> {
                    Encryptor encryptor = new Encryptor(new FileService());
                    encryptor.bruteforse();
                }
                case 0 -> {
                    System.exit(0);
                }
                default -> System.out.println("Выберите правильный пункт меню!");
            }
        }
    }
}
