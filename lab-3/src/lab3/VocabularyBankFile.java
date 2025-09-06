package lab3;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class VocabularyFile {
    public record TranslatedPhrase(String phrase, String translated) {}

    private List<TranslatedPhrase> phrases;

    public void load(String path) {
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

    public List<TranslatedPhrase> getWordCompiter(String pattern) {
        pattern = pattern.toLowerCase();

        int startIndex = Collections.binarySearch(
                phrases,
                new TranslatedPhrase(pattern, ""),
                Comparator.comparing(TranslatedPhrase::phrase)
        );

        if (startIndex < 0)
            startIndex = -startIndex - 1;

        int endIndex = startIndex;
        while (endIndex < phrases.size() && phrases.get(endIndex).phrase().startsWith(pattern))
            endIndex++;

        return phrases.subList(startIndex, endIndex);
    }
}