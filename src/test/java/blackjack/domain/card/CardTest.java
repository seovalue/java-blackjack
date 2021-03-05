package blackjack.domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @DisplayName("카드 객체 생성")
    @Test
    void create() {
        assertThatCode(() -> Card.create(Suit.CLUB, Denomination.KING))
                .doesNotThrowAnyException();
    }
}
