import service.EncryptorService;
import service.FileService;


import java.util.InputMismatchException;
import java.util.Scanner;


import static constans.Consts.MAIN_MENU;


public class Main {

    public static void main(String[] args) {
        EncryptorService encryptorService = new EncryptorService(new FileService());
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
                case 1 -> {encryptorService.encryption(scanner);}
                case 2 -> {encryptorService.decryption(scanner);}
                case 3 -> {encryptorService.bruteforse();}
                case 0 -> {System.exit(0);
                }
                default -> System.out.println("Выберите правильный пункт меню!");
            }
        }
    }
}
