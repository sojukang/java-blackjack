package blackjack.controller;

import static blackjack.view.InputView.*;
import static blackjack.view.OutputView.*;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;

import blackjack.domain.Betting;
import blackjack.domain.BettingTable;
import blackjack.domain.Game;
import blackjack.domain.PlayRecord;
import blackjack.domain.PlayStatus;
import blackjack.domain.RecordFactory;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.deckstrategy.RandomDeck;
import blackjack.domain.participant.DrawCount;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.dto.ParticipantDto;

public class GameController {

    public void play() {
        List<Name> names = getNames();
        List<Betting> bettings = getBettings(names);
        Game game = initPlay(names);

        drawPlayerCards(game);
        drawDealerCards(game);

        finalParticipantsCards(game);
        finalRevenue(game, bettings);
    }

    private List<Name> getNames() {
        return requestPlayerNames().stream()
            .map(Name::of)
            .collect(toUnmodifiableList());
    }

    private List<Betting> getBettings(List<Name> names) {
        return names.stream()
            .map(name -> new Betting(name, inputBettingMoney(name)))
            .collect(toUnmodifiableList());
    }

    private Game initPlay(List<Name> names) {
        Game game = new Game(new CardDeck(new RandomDeck()), names);

        printInitResult(names);
        printDealerFirstCard(game.dealerFirstCard());

        for (Player player : game.getPlayers()) {
            printPlayerCards(convertToDto(player));
        }
        printEmptyLine();
        return game;
    }

    private ParticipantDto convertToDto(Participant participant) {
        return ModelMapper.map(participant);
    }

    private void drawPlayerCards(Game game) {
        validatePlayersPresent(game.getPlayers());

        while (game.findHitPlayer().isPresent()) {
            Player player = game.findHitPlayer().get();
            PlayStatus hitOrStay = requestHitOrStay(player.getName());

            game.drawPlayerCard(player, hitOrStay);

            printPlayerCards(convertToDto(player));
        }
    }

    private void validatePlayersPresent(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalStateException("플레이어가 존재하지 않습니다.");
        }
    }

    private void drawDealerCards(Game game) {
        DrawCount drawCount = game.drawDealerCards();
        printDealerDrawCardCount(drawCount);
    }

    private void finalParticipantsCards(Game game) {
        printParticipantCardsWithScore(convertToDto(game.getDealer()));
        for (Player player : game.getPlayers()) {
            printParticipantCardsWithScore(convertToDto(player));
        }
    }

    private void finalRevenue(Game game, List<Betting> bettings) {
        RecordFactory recordFactory = new RecordFactory(game.getDealerScore());
        Map<Name, PlayRecord> recordMap = recordFactory.getPlayerRecords(game.getPlayers());

        BettingTable bettingTable = new BettingTable(bettings);
        printFinalRevenues(bettingTable.getRevenues(recordMap));
    }
}