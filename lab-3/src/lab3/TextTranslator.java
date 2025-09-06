package lab3;

import java.util.List;

public class Translator {
    private record TranslatedPhraseWords(String[] words, String translated) {}

    private final VocabularyBankFile vocabularyBankFile;
    private final String text;

    private int textTranslatingCharIndex;
    private String translatedText;

    public Translator(VocabularyBankFile vocabularyBankFile, String text) {
        this.vocabularyBankFile = vocabularyBankFile;
        this.text = text;
    }

    String Translate() {
        var translated = new StringBuilder();
        int charIndex = 0;

        while (charIndex < text.length()) {
            char c = text.charAt(charIndex);

            if (Character.isWhitespace(c)) {
                translated.append(c);
                charIndex++;
                continue;
            }

            int start = charIndex;
            while (charIndex < text.length() && !Character.isWhitespace(text.charAt(charIndex))) {
                charIndex++;
            }
            String word = text.substring(start, charIndex).toLowerCase();

            var matchedPhrases = vocabularyBankFile.getMatched(word);

            if (matchedPhrases.isEmpty() || !matchedPhrases.get(0).phrase().equals(word)) {
                translated.append(word);
                continue;
            }

            if (matchedPhrases.size() == 1) {
                translated.append(matchedPhrases.get(0));
                continue;
            }

            var translatedPhraseWords = matchedPhrases.stream()
                    .map(p -> new TranslatedPhraseWords(p.phrase().split("\\s+"), p.translated()))
                    .toList();

            for ()

            int phraseCharIndex = charIndex;
            while (Character.isWhitespace(text.charAt(phraseCharIndex))) {
                phraseCharIndex++;
            }

            start = phraseCharIndex;
            while (!Character.isWhitespace(text.charAt(phraseCharIndex))) {
                phraseCharIndex++;
            }

            String nextWord = text.substring(start, phraseCharIndex).toLowerCase();

            String fullPhrase = word + " " + nextWord;

            long matchedMatcherPhrase = matchedPhrases.stream()
                    .filter(p -> p.phrase().startsWith(fullPhrase)).count();


            if (!span.isEmpty()) {
                var best = span.stream()
                        .max(Comparator.comparingInt(p -> p.phrase().length()))
                        .get();
                result.append(best.translated());
            } else {
                result.append(word); // без перевода
            }
        }

    }

    private String FoundMatchedTranslatedPhrase(List<VocabularyBankFile.TranslatedPhrase> phrases, String text, )
}
