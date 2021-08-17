package cards;

import player.Player;

public class Card {

    public static final String DEFAULT_HIDDEN_NAME = "X";
    public static final char DEFAULT_HIDDEN_SUITE = 'X';

    protected Player owner;
    protected String valueName;
    protected char suite;
    protected int value;
    protected boolean visible;
    protected boolean valueSet = true;

    public Card(String valueName, char suite, int value, boolean visable) {
        this.valueName = valueName;
        this.suite = suite;
        this.value = value;
        this.visible = visable;
    }

    public boolean isVisible() {
        return visible;
    }

    public Card setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public String getActualValueName() {
        return valueName;
    }

    public String getName() {
        return getValueName() + getSuite();
    }

    public String getValueName() {
        if (visible) return  valueName;
        else return DEFAULT_HIDDEN_NAME;
    }

    public char getActualSuite() {
        return suite;
    }

    public char getSuite() {
        if (visible) return suite;
        else return DEFAULT_HIDDEN_SUITE;
    }

    public int getValue() {
        return value;
    }

    public Card setOwner(Player owner) {
        this.owner = owner;
        return this;
    }

    public boolean isValueSet() {
        return valueSet;
    }

}
