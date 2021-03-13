package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static blackjack.domain.Fixtures.CLUB_NINE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StayTest {
    State stay;

    @BeforeEach
    void setUp() {
        stay = FirstTurn.draw(Arrays.asList(CLUB_NINE, CLUB_NINE));
        stay = stay.draw(Card.of(Suit.CLUB, Denomination.THREE));
    }

    @Test
    void profit() {
        assertThat(stay.profit(new Money(1000))).isEqualTo(1000.0);
    }

    @DisplayName("Stay 상태의 경우 stay 시 Stay 반환")
    @Test
    void stay() {
        stay = stay.stay();
        assertThat(stay.getClass()).isEqualTo(Stay.class);
    }

    @DisplayName("Stay의 경우 draw시 Exception 발생")
    @Test
    void draw() {
        assertThatThrownBy(() -> {
            stay.draw(Card.of(Suit.CLUB, Denomination.TEN));
        }).isInstanceOf(UnsupportedOperationException.class);
    }
}