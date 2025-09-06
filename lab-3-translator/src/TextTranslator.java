import java.util.List;

public class TextTranslator {
    private final VocabularyBankFile vocabularyBankFile;
    private final TextByTokenReader textByTokenReader;

    public TextTranslator(VocabularyBankFile vocabularyBankFile, String text) {
        this.vocabularyBankFile = vocabularyBankFile;
        this.textByTokenReader = new TextByTokenReader(text);
    }

    public String translate() {
        var translated = new StringBuilder();

        while (textByTokenReader.readNext()) {
            if (textByTokenReader.isCurrentTokenSpacer()) {
                translated.append(textByTokenReader.current());
                continue;
            }

            var word = textByTokenReader.current().toLowerCase();

            var matchedPhrases = vocabularyBankFile.getMatched(word);

            if (matchedPhrases.isEmpty()) {
                translated.append(word);
                continue;
            }

            var bestMatched = foundBestMatched(matchedPhrases, word);
            if (bestMatched != null)
                translated.append(bestMatched.translated());
            else
                translated.append(word);
        }

        return translated.toString();
    }

    private VocabularyBankFile.TranslatedPhrase foundBestMatched(List<VocabularyBankFile.TranslatedPhrase> phrases, String phrase) {

        if (phrases.isEmpty())
            return null;

        var readerState = textByTokenReader.getState();
        if (textByTokenReader.readNextWordPhrase()) {
            var nextWordPhrase = phrase + " " + textByTokenReader.current();
            var nextWordMatched = phrases.stream()
                    .filter(f -> f.phrase().startsWith(nextWordPhrase))
                    .toList();

            var nextWordBest = foundBestMatched(nextWordMatched, nextWordPhrase);
            if (nextWordBest != null)
                return nextWordBest;

            textByTokenReader.loadState(readerState);
        }

        return phrases.stream()
                .filter(p -> p.phrase().equals(phrase))
                .findFirst().orElse(null);
    }
}
