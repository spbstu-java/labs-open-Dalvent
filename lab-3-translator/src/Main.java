import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var vocabulary = new VocabularyBankFile();

        try {
            vocabulary.load("dictionary.txt");
        } catch (InvalidFileFormatException | FileReadException e) {
            System.err.println(e.getMessage());
            return;
        }

        var sc = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String line = sc.nextLine();

            if (line.equalsIgnoreCase("q")) {
                return;
            }

            var translator = new TextTranslator(vocabulary, line);
            String translated = translator.translate();
            System.out.println(translated);
        }
    }
}