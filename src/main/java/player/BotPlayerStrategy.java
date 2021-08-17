package player;

import game.GameModel;

class BotPlayerStrategy extends PlayerStrategy {

    public BotPlayerStrategy(GameModel game, Player player) {
        super(game, player);
    }

    @Override
    public int getAceValue() {
        if (player.getTotalValueIgnoreNotSet() > 10) return 1;
        return 11;
    }

    @Override
    public boolean willDraw() {
        Player opponent;
        if (game.getDealer() == player) opponent = game.getPlayer();
        else opponent = game.getDealer();

        return opponent.getTotalVisibleValue() > player.getTotalVisibleValue();
    }
}
