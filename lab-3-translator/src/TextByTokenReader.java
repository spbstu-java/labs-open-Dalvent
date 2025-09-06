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

        boolean isLetterOrDigitToken = Character.isLetterOrDigit(text.charAt(i));

        i = Math.min(i + 1, text.length());
        while (i < text.length()) {
            if (isLetterOrDigitToken != Character.isLetterOrDigit(text.charAt(i)))
                break;

            i++;
        }

        startIndex = startIndex + count;
        count = i - startIndex;

        return true;
    }

    public boolean readNextWordPhrase() {
        var cancelReadState = getState();

        boolean res = readNext();
        if (!res)
            return false;

        if (isCurrentTokenSpacerWithDelimiter()) {
            loadState(cancelReadState);
            return false;
        }

        if (isCurrentTokenSpacer())
            return readNext();

        return true;
    }

    public boolean isCurrentTokenSpacer() {
        return !Character.isLetterOrDigit(text.charAt(startIndex));
    }

    private boolean isCurrentTokenSpacerWithDelimiter() {
        if (!isCurrentTokenSpacer())
            return false;

        for (int i = 0; i < count; i++) {
            char c = text.charAt(startIndex + i);
            if (!Character.isWhitespace(c))
                return true;
        }
        return false;
    }
}