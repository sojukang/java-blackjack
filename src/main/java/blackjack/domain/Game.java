package blackjack.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardCount;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Status;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class Game {

    private final CardFactory cardFactory;
    private final Dealer dealer;
    private final List<Player> players;

    public Game(CardFactory cardFactory, List<String> playerNames) {
        this.cardFactory = cardFactory;
        this.dealer = new Dealer();
        this.players = playerNames.stream()
            .map(Player::new)
            .collect(Collectors.toList());
    }

    public void init() {
        dealer.init(cardFactory);
        players.forEach(player -> player.init(cardFactory));
    }

    public Card openCard() {
        return dealer.openCard();
    }

    public Optional<Player> findHitPlayer() {
        return players.stream()
            .filter(Player::isHit)
            .findFirst();
    }

    public void drawPlayerCard(Player player, Status status) {
        if (status == Status.HIT) {
            player.hit(cardFactory);
            return;
        }
        player.stay();
    }

    public CardCount drawDealerCard() {
        return dealer.drawCards(cardFactory);
    }

    public int getDealerScore() {
        return dealer.getScore();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}