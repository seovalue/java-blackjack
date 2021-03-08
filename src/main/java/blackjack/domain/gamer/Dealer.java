package blackjack.domain.gamer;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Gamer {

    private static final String NAME = "딜러";
    private static final int ADD_CARD_BOUNDARY = 16;

    private Dealer(String name) {
        super(name);
    }

    public Dealer() {
        this(NAME);
    }

    @Override
    public boolean canDraw() {
        return (hands.calculate() <= ADD_CARD_BOUNDARY);
    }

    @Override
    public List<Card> showOpenHands() {
        return hands.getCardOf(1);
    }
}
