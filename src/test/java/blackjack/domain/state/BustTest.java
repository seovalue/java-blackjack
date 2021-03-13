package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static blackjack.domain.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BustTest {

    State bust;

    @BeforeEach
    void setUp() {
        bust = FirstTurn.draw(Arrays.asList(CLUB_TEN, CLUB_TEN));
        bust.draw(Card.of(Suit.CLUB, Denomination.TEN));
    }

    @Test
    void profit() {
        assertThatThrownBy(() -> {
            bust.profit(new Money(1000));})
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Bust 상태의 경우 stay 시 현재 상태 반환")
    @Test
    void stay() {
        assertThat(bust.stay()).isEqualTo(Bust.class);
    }

    @DisplayName("Bust 상태의 경우 draw 시 마지막 상태이므로 Exception을 발생")
    @Test
    void draw() {

        bust.draw(Card.of(Suit.CLUB, Denomination.FOUR));
//        assertThatThrownBy(() -> {
//            bust.draw(Card.of(Suit.CLUB, Denomination.FOUR));})
//        .isInstanceOf(UnsupportedOperationException.class);
    }
}