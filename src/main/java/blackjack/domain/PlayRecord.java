package blackjack.domain;

import static blackjack.domain.PlayStatus.*;

public enum PlayRecord {

    WIN("승"),
    PUSH("무"),
    LOSS("패");

    private final String name;

    PlayRecord(String name) {
        this.name = name;
    }

    public static PlayRecord of(int dealerScore, int score) {
        if (isPlayerLoss(dealerScore, score)) {
            return LOSS;
        }

        if (dealerScore == score) {
            return PUSH;
        }

        return WIN;
    }

    private static boolean isPlayerLoss(int dealerScore, int score) {
        return isBust(score) || (!isBust(dealerScore) && score < dealerScore);
    }

    public String getName() {
        return name;
    }

    PlayRecord getOpposite(PlayRecord record) {
        if (record == LOSS) {
            return WIN;
        }

        if (record == WIN) {
            return LOSS;
        }

        return PUSH;
    }
}