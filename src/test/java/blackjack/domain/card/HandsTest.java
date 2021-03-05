package blackjack.domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandsTest {

    @DisplayName("Cards 객체 생성")
    @Test
    void create() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.create(Suit.CLUB, Denomination.KING));
        cards.add(Card.create(Suit.CLUB, Denomination.QUEEN));
        assertThatCode(() -> new Hands(cards)).doesNotThrowAnyException();
    }

    @DisplayName("Cards에 Card 객체 추가")
    @Test
    void add() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.create(Suit.CLUB, Denomination.KING));
        cards.add(Card.create(Suit.CLUB, Denomination.QUEEN));
        Hands hands = new Hands(cards);

        assertThat(hands.toList().size()).isEqualTo(2);
    }

    @DisplayName("포인트 계산 성공 : Ace 존재하고 최대값 21 넘을 때")
    @Test
    void calculate_containsA_maxExceed() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.create(Suit.CLUB, Denomination.ACE));
        cards.add(Card.create(Suit.CLUB, Denomination.TWO));
        Hands hands = new Hands(cards);
        hands.addCard(Card.create(Suit.CLUB, Denomination.KING));

        assertThat(hands.calculate()).isEqualTo(13);
    }

    @DisplayName("포인트 계산 성공 : Ace 존재하고 최대값 21 넘지 않을 때")
    @Test
    void calculate_containsA_maxNotExceed() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.create(Suit.CLUB, Denomination.ACE));
        cards.add(Card.create(Suit.CLUB, Denomination.TWO));
        Hands hands = new Hands(cards);
        hands.addCard(Card.create(Suit.CLUB, Denomination.THREE));

        assertThat(hands.calculate()).isEqualTo(16);
    }

    @DisplayName("포인트 계산 성공 : Ace 존재하지 않을 때")
    @Test
    void calculate_excludeA() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.create(Suit.HEART, Denomination.TWO));
        cards.add(Card.create(Suit.CLUB, Denomination.TWO));
        Hands hands = new Hands(cards);
        hands.addCard(Card.create(Suit.CLUB, Denomination.THREE));

        assertThat(hands.calculate()).isEqualTo(7);
    }

    @DisplayName("포인트 계산 성공 : 특수 케이스 A,2,8")
    @Test
    void calculate_with_A_2_8() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.create(Suit.HEART, Denomination.ACE));
        cards.add(Card.create(Suit.CLUB, Denomination.TWO));
        Hands hands = new Hands(cards);
        hands.addCard(Card.create(Suit.CLUB, Denomination.EIGHT));

        assertThat(hands.calculate()).isEqualTo(21);
    }

    @DisplayName("포인트 계산 성공 : 특수 케이스 10,3,A,A,A,Q")
    @Test
    void calculate_with_10_3_A_A_A_Q() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.create(Suit.HEART, Denomination.TEN));
        cards.add(Card.create(Suit.HEART, Denomination.THREE));
        Hands hands = new Hands(cards);
        hands.addCard(Card.create(Suit.SPADE, Denomination.ACE));
        hands.addCard(Card.create(Suit.CLUB, Denomination.ACE));
        hands.addCard(Card.create(Suit.HEART, Denomination.ACE));
        hands.addCard(Card.create(Suit.CLUB, Denomination.QUEEN));

        assertThat(hands.calculate()).isEqualTo(26);
    }

    @DisplayName("Ace를 가지고 있는지 확인")
    @Test
    void containsAce() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.create(Suit.HEART, Denomination.ACE));
        cards.add(Card.create(Suit.CLUB, Denomination.TWO));
        Hands hands = new Hands(cards);
        hands.addCard(Card.create(Suit.CLUB, Denomination.THREE));

        assertTrue(hands.containsAce());
    }

    @DisplayName("number만큼 카드 가지고 오기")
    @Test
    void getCardOf() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.create(Suit.HEART, Denomination.TWO));
        cards.add(Card.create(Suit.CLUB, Denomination.TWO));
        Hands hands = new Hands(cards);
        hands.addCard(Card.create(Suit.CLUB, Denomination.THREE));

        assertThat(hands.getCardOf(2).size()).isEqualTo(2);
    }

    @DisplayName("blackjack인지 확인")
    @Test
    void isBlackjack() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.create(Suit.HEART, Denomination.ACE));
        cards.add(Card.create(Suit.CLUB, Denomination.TEN));
        Hands hands = new Hands(cards);

        assertTrue(hands.isBlackjack());
    }
}
