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

class HitTest {
    State hit;

    @BeforeEach
    void setUp() {
        hit = FirstTurn.draw(Arrays.asList(CLUB_NINE, CLUB_NINE));
    }

    @Test
    void profit() {
        assertThatThrownBy(() -> {
            hit.profit(new Money(1000));
        })
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Hit 상태의 경우 stay 시 Stay 반환")
    @Test
    void stay() {
        hit = hit.stay();
        assertThat(hit.getClass()).isEqualTo(Stay.class);
    }

    @DisplayName("draw 결과가 Bust인 경우에는 Bust 클래스 반환")
    @Test
    void draw1() {
        State bust = hit.draw(Card.of(Suit.CLUB, Denomination.TEN));
        assertThat(bust.getClass()).isEqualTo(Bust.class);
    }

    @DisplayName("draw 결과가 Hit인 경우에는 Hit 클래스 반환")
    @Test
    void draw2() {
        hit = hit.draw(Card.of(Suit.CLUB, Denomination.TWO));
        assertThat(hit.getClass()).isEqualTo(Hit.class);
    }

    @DisplayName("draw 결과가 21과 같은 경우에는 Stay 클래스 반환")
    @Test
    void draw3() {
        State stay = hit.draw(Card.of(Suit.CLUB, Denomination.THREE));
        assertThat(stay.getClass()).isEqualTo(Stay.class);
    }
}