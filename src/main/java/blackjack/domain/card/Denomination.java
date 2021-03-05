package blackjack.domain.card;

public enum Denomination {
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K"),
    ACE(1, "A");

    private final int value;
    private final String name;

    Denomination(final int value, final String name) {
        this.value = value;
        this.name = name;
    }

    public static boolean isAce(Denomination denomination) {
        return ACE.equals(denomination);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
