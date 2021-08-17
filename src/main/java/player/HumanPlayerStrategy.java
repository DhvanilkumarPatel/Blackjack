package player;

import game.GameModel;

class HumanPlayerStrategy extends PlayerStrategy {

    public HumanPlayerStrategy(GameModel game, Player player) {
        super(game, player);
    }

    @Override
    public int getAceValue() {
        int sum = player.getTotalValueIgnoreNotSet();
        if (sum + 11 > 21) {
            return 1;
        } else if (sum + 11 == 21) {
            return 11;
        }
        return game.getHumanAceDecision();
    }

    @Override
    public boolean willDraw() {
        return game.getHumanDrawDecision();
    }
}
