package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Betting;
import blackjack.domain.participant.DrawCount;
import blackjack.domain.participant.Name;
import blackjack.domain.state.stateparticipant.Dealer;
import blackjack.domain.state.stateparticipant.Participant;
import blackjack.domain.state.stateparticipant.Player;

public class Game {

    private static final String HIT_REQUEST = "y";

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final List<Player> players;

    public Game(CardDeck cardDeck, List<Name> playerNames, List<Betting> bettings) {
        this.cardDeck = cardDeck;
        this.dealer = new Dealer(bettings);
        this.players = List.copyOf(playerNames).stream()
            .map(Player::new)
            .collect(Collectors.toUnmodifiableList());
        init();
    }

    private void init() {
        dealer.init(cardDeck.drawCard(), cardDeck.drawCard());
        players.forEach(player -> player.init(cardDeck.drawCard(), cardDeck.drawCard()));
    }

    public Optional<Player> findHitPlayer() {
        return players.stream()
            .filter(player -> !player.isFinished())
            .findFirst();
    }

    public void drawPlayerCard(Player player, String hitOrStay) {
        if (hitOrStay.equalsIgnoreCase(HIT_REQUEST)) {
            player.draw(cardDeck.drawCard());
            return;
        }
        player.stay();
    }

    public DrawCount drawDealerCards() {
        int count = 0;
        while (dealer.isDrawable()) {
            dealer.draw(cardDeck.drawCard());
            count++;
        }

        return DrawCount.of(count);
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public Map<Name, Long> getRevenues() {
        return dealer.getRevenues(PlayRecord.createPlayRecords(players, dealer));
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>(players);
        participants.add(0, dealer);
        return participants;
    }
}