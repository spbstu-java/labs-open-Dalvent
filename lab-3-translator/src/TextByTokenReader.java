package lab3;

public class TextByTokenReader {
    public record State(int startIndex, int count) {}

    private final String text;
    private int startIndex = 0;
    private int count = 0;

    public TextByTokenReader(String text) {
        this.text = text;
    }

    public String current() {
        return text.substring(startIndex, startIndex + count);
    }

    public void loadState(State state) {
        startIndex = state.startIndex;
        count = state.count;
    }

    public State getState() {
        return new State(startIndex, count);
    }

    public boolean readNext() {
        int i = startIndex + count;
        if (i >= text.length())
            return false;

        char c1 = text.charAt(i);
        boolean isDelimiterToken = Character.isWhitespace(c1) || !Character.isLetterOrDigit(c1);
        while (i < text.length()) {
            char c = text.charAt(i);
            if (!((Character.isWhitespace(c) || !Character.isLetterOrDigit(c)) == isDelimiterToken)) break;
            i++;
        }

        startIndex = startIndex + count;
        count = i - startIndex;

        return true;
    }

    public boolean readNextWordPhrase() {
        boolean res = readNext();
        if (!res)
            return false;

        if (isCurrentDelimiterSpace())
            return false;

        if (isCurrentWhiteSpace())
            return readNext();

        return true;
    }

    public boolean isCurrentWhiteSpace() {
        for (int i = 0; i < count; i++) {
            if (!Character.isWhitespace(text.charAt(startIndex + i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isCurrentDelimiterSpace() {
        for (int i = 0; i < count; i++) {
            char c = text.charAt(startIndex + i);
            if (Character.isLetterOrDigit(c) || Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }
}