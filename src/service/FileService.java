package service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileService {
    /**
     * Чтение символов из файла в List.
     */
    public List<Character> readFile() {
        List<Character> fileChars = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String pathFile = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(pathFile),
                "UTF-8"));) {

            int symbol;
            while ((symbol = reader.read()) != -1) {
                fileChars.add((char) symbol);
            }

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
            System.exit(0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileChars;
    }

    /**
     * Запись из List в файл.
     */
    public void writeFile(List<Character> readFile) {
        Scanner scanner = new Scanner(System.in);
        String pathFile = scanner.nextLine();
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathFile),
                "UTF-8"))) {

            for (int i = 0; i < readFile.size(); i++) {
                writer.write(readFile.get(i));
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }


    }
}
