public class WildCard extends Card {

    private final static int DEFAULT_VALUE = 1;
    private final int[] possibleValues;

    public WildCard(String valueName, char suite, int[] possibleValues) {
        super(valueName, suite, DEFAULT_VALUE);
        this.possibleValues = possibleValues;
    }

    public int getValue() {
        return value;
    }

}
