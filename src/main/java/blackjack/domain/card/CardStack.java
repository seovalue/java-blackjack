package blackjack.domain.card;

import java.util.*;

public class CardStack {

    private final Stack<Card> cards;

    private CardStack(Stack<Card> cards) {
        Collections.shuffle(cards);
        this.cards = cards;
    }

    public static CardStack create() {
        return new CardStack(CardFactory.generate());
    }

    public List<Card> getTwoCards() {
        return new ArrayList<>(
                Arrays.asList(cards.pop(), cards.pop())
        );
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card getSingleCard() {
        return cards.pop();
    }
}
