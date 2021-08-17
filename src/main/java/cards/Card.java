public class Card {

    protected String valueName;
    protected char suite;
    protected int value;

    public Card(String valueName, char suite, int value) {
        this.valueName = valueName;
        this.suite = suite;
        this.value = value;
    }

    public String getValueName() {
        return valueName;
    }

    public char getSuite() {
        return suite;
    }

    public int getValue() {
        return value;
    }

}
