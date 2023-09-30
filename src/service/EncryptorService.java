package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

import static constans.Consts.PATH_OF_FILE;
import static constans.Consts.PATH_OF_ENCRYPTION_FILE;
import static constans.Consts.DONE;

public class EncryptorService {
    private FileService fileService;

    public EncryptorService(FileService fileService) {
        this.fileService = fileService;
    }

    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и',
            'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ', '\n'};

    public static char[] getAlphabet() {
        return ALPHABET;
    }

    /**
     * Получем новый List, сдвигая символы в старом, на значение ключа.
     */
    public List<Character> encryptionFile(int key, List<Character> readFile) {
        List<Character> afterEnrcyption = new ArrayList<>();

        for (int i = 0; i < readFile.size(); i++) {
            for (int j = 0; j < ALPHABET.length; j++) {
                if (readFile.get(i) == ALPHABET[j]) {

                    int index = j + key;
                    if (index >= ALPHABET.length) {
                        afterEnrcyption.add(ALPHABET[(index) - ALPHABET.length]);
                    } else if (index < ALPHABET.length) {
                        afterEnrcyption.add(ALPHABET[j + key]);
                    }
                }
            }
        }
        return afterEnrcyption;

    }

    /**
     * Шифруем с помошью метода encryptionFile и записываем символы в файл.
     */
    public void encryption(Scanner scanner) {
        System.out.println(PATH_OF_FILE);
        List<Character> readFile = fileService.readFile();

        System.out.println("Введите ключ");
        int key = scanner.nextInt();
        if (key > 0 && key <= ALPHABET.length - 1) {

            System.out.println(PATH_OF_ENCRYPTION_FILE);
            fileService.writeFile(encryptionFile(key, readFile));

            System.out.println(DONE);
        } else {
            System.err.println("Введите правильный ключ! Ключ должен быть больше 0 и меньше "
                    + EncryptorService.getAlphabet().length);
            System.exit(0);
        }
    }

    /**
     * Разшифровываем и записывавем символы в файл.
     */
    public void decryption(Scanner scanner) {
        System.out.println(PATH_OF_ENCRYPTION_FILE);
        List<Character> readFile = fileService.readFile();

        System.out.println("Введите ключ");
        int key = scanner.nextInt();
        if (key > 0 && key <= ALPHABET.length - 1) {
            key = ALPHABET.length - key;

            System.out.println(PATH_OF_FILE);
            fileService.writeFile(encryptionFile(key, readFile));

            System.out.println(DONE);
        } else {
            System.err.println("Введите правильный ключ! Ключ должен быть больше 0 и меньше "
                    + ALPHABET.length);
            System.exit(0);
        }
    }

    /**
     * Подставляем все возможные ключи и считаем количество ситуаций, когда после запятой идет пробел.
     * Запоминаем этот ключ, с наибольшим количеством таких ситкаций.
     */
    public void bruteforse() {
        System.out.println(PATH_OF_ENCRYPTION_FILE);
        List<Character> readFile = fileService.readFile();
        Map<Integer, Integer> bruteForce = new HashMap<>();
        int count = 0;

        for (int i = 0; i < EncryptorService.getAlphabet().length; i++) {
            count = 0;
            List<Character> temp = encryptionFile(i, readFile);
            for (int j = 0; j < temp.size(); j++) {
                for (int k = j + 1; k < temp.size(); k++) {
                    if (((temp.get(j) == ',') & (temp.get(k)) == ' ')) {
                        count++;
                        bruteForce.put(i, count);
                    }
                }
            }
        }
        Integer maxKey = null;
        for (Integer key : bruteForce.keySet()) {
            if (maxKey == null || bruteForce.get(key) > bruteForce.get(maxKey)) {
                maxKey = key;
            }
        }
        System.out.println(PATH_OF_FILE);
        fileService.writeFile(encryptionFile(maxKey, readFile));
        System.out.println(DONE);
        System.exit(0);
    }

}