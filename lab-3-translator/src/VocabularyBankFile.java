import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class VocabularyBankFile {
    public record TranslatedPhrase(String phrase, String translated) {}

    private List<TranslatedPhrase> phrases;

    public void load(String path) throws FileReadException, InvalidFileFormatException {
        try (var lines = Files.lines(Paths.get(path))) {
            phrases = lines.map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .map(line -> {
                        String[] parts = line.split("\\|");
                        if (parts.length != 2)
                            throw new InvalidFileFormatException("Can't read translated phrase: " + line);

                        return new TranslatedPhrase(
                                parts[0].trim().toLowerCase(),
                                parts[1].trim()
                        );
                    })
                    .sorted(Comparator.comparing(TranslatedPhrase::phrase))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileReadException("Can't read file " + path, e);
        }
    }

    public List<TranslatedPhrase> getMatched(String pattern) {
        pattern = pattern.toLowerCase();

        int startIndex = Collections.binarySearch(
                phrases,
                new TranslatedPhrase(pattern, ""),
                Comparator.comparing(TranslatedPhrase::phrase)
        );

        if (startIndex < 0)
            startIndex = -startIndex - 1;

        int endIndex = startIndex;
        while (endIndex < phrases.size() && startsWithWholeWord(phrases.get(endIndex).phrase(), pattern))
            endIndex++;

        return phrases.subList(startIndex, endIndex);
    }

    private static boolean startsWithWholeWord(String text, String pattern) {
        if (!text.startsWith(pattern)) return false;
        if (text.length() == pattern.length()) return true;
        return Character.isWhitespace(text.charAt(pattern.length()));
    }
}