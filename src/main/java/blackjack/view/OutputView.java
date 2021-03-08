package blackjack.view;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.MatchResult;
import blackjack.domain.result.MatchResultType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String PLAYER_NAMES_INPUT_GUIDE_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String DRAW_AT_FIRST_GUIDE_MESSAGE = "달러와 %s 에게 2장의 카드를 나누었습니다.";
    private static final String DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String HIT_GUIDE_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y,아니오는 n)";

    private OutputView() {
    }

    public static void printPlayerNameInputGuideMessage() {
        System.out.println(PLAYER_NAMES_INPUT_GUIDE_MESSAGE);
    }

    public static void printAfterDrawAtFirstGuideMessage(Players players) {
        String playerNames = players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));

        System.out.println(String.format(DRAW_AT_FIRST_GUIDE_MESSAGE, playerNames));
    }

    public static void printHitGuideMessage(Player player) {
        System.out.println(player.getName() + HIT_GUIDE_MESSAGE);
    }

    public static void printDealerHitMessage() {
        System.out.println(DEALER_HIT_MESSAGE);
    }

    public static void printCardsAndScore(Dealer dealer, Players players) {
        System.out.println(dealer.getName() + " 카드: " + dealer.getCards() + "- 결과: " + dealer.calculateScore());
        players.getPlayers()
                .forEach(player ->
                        System.out.println(player.getName() + " 카드: " + player.getCards() + "- 결과: " + player.calculateScore())
                );
    }

    public static void printMatchTypeResult(List<Integer> matchResultTypeCount, MatchResult result) {
        System.out.println("## 최종 승패");
        System.out.println("딜러: " + matchResultTypeCount.get(2) + "승" + matchResultTypeCount.get(1) + "무" + matchResultTypeCount.get(0) + "패");
        Map<Player, MatchResultType> matchResult = result.getMatchResult();
        matchResult.forEach((player, status) -> System.out.println(player.getName() + ": " + status.toString()));
    }

    public static void printParticipantsCardAtFirst(Dealer dealer, Players players) {
        System.out.println(dealer.getName() + "카드: " + dealer.showCardsAtFirst());

        for (Player player : players.getPlayers()) {
            System.out.println(player.getName() + "카드: " + player.showCardsAtFirst());
        }
    }

    public static void printPlayerCards(Player player) {
        System.out.println(player.getName() + "카드: " + player.getCards());
    }

    public static void printErrorMessage(IllegalArgumentException exception) {
        System.out.println("[ERROR] " + exception.getMessage());
    }
}