package blackjack.domain.game;

import java.util.Arrays;

import org.assertj.core.util.TriFunction;

/**
 * 블랙잭 게임에서 플레이어가 가질 수 있는 4가지 전적
 */
public enum PlayRecord {
    /**플레이어의 패배.*/
    LOSS((isPlayerLoss, isSameScore, isBlackjack) -> isPlayerLoss),

    /**플레이어와 딜러가 동점.*/
    PUSH((isPlayerLoss, isSameScore, isBlackjack) -> !isPlayerLoss && isSameScore),

    /**플레이어의 블랙잭. 딜러가 동시에 블랙잭인 경우는 PUSH에 해당한다.*/
    BLACKJACK((isPlayerLoss, isSameScore, isBlackjack) -> isBlackjack),

    /**플레이어의 승리.*/
    WIN((isPlayerLoss, isSameScore, isBlackjack) -> true);

    private final TriFunction<Boolean, Boolean, Boolean, Boolean> isMatch;

    PlayRecord(TriFunction<Boolean, Boolean, Boolean, Boolean> isMatch) {
        this.isMatch = isMatch;
    }

    static PlayRecord of(boolean isPlayerLoss, boolean isSameScore, boolean isBlackjack) {
        return Arrays.stream(values())
            .filter(playRecord -> playRecord.isMatch.apply(isPlayerLoss, isSameScore, isBlackjack))
            .findFirst()
            .orElse(WIN);
    }
}
