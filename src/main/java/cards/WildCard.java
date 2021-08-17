package cards;

import player.Player;

public class WildCard extends Card {

    private final static int DEFAULT_VALUE = 1;
    private final int[] possibleValues;

    public WildCard(String valueName, char suite, boolean visable, int[] possibleValues) {
        super(valueName, suite, DEFAULT_VALUE, visable);
        this.possibleValues = possibleValues;
        valueSet = false;
    }

    public int getValue() {
        if (valueSet) {
            return value;
        } else if (owner == null) {
            return DEFAULT_VALUE;
        } else {
            value = owner.getAceValue();
            valueSet = true;
            return value;
        }
    }

}
