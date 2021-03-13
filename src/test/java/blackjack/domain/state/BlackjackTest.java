package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static blackjack.domain.Fixtures.CLUB_ACE;
import static blackjack.domain.Fixtures.CLUB_TEN;
import static org.assertj.core.api.Assertions.*;

class BlackjackTest {
    @Test
    void earningRate() {
        final Finished blackjack = (Finished) FirstTurn.draw(Arrays.asList(CLUB_ACE, CLUB_TEN));
        assertThat(blackjack.earningRate()).isEqualTo(Blackjack.EARNING_RATE);
    }

    @DisplayName("블랙잭 상태의 경우 stay 시 현재 상태 반환")
    @Test
    void stay() {
        final Finished blackjack = (Finished) FirstTurn.draw(Arrays.asList(CLUB_ACE, CLUB_TEN));
        assertThat(blackjack.stay()).isEqualTo(blackjack);
    }

    @DisplayName("블랙잭 상태의 경우 draw 시 마지막 상태이므로 Exception을 발생")
    @Test
    void draw() {
        final State blackjack = FirstTurn.draw(Arrays.asList(CLUB_ACE, CLUB_TEN));
        assertThatThrownBy(() ->
                blackjack.draw(Card.of(Suit.CLUB, Denomination.TEN)));
    }
}