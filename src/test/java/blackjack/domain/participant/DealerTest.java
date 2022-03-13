package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.NoShuffleDeck;
import blackjack.domain.PlayStatus;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;

class DealerTest {

    @Test
    @DisplayName("조건에 만족할 때 까지 카드르 뽑는다. (버스트)")
    void drawCards_BUST() {
        // give
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck(new NoShuffleDeck());
        List<Card> cards = List.of(new Card(DIAMOND, QUEEN), new Card(CLUB, FIVE));
        dealer.init(cards);

        // when
        dealer.drawCards(cardDeck);
        PlayStatus actual = dealer.getStatus();

        // then
        assertThat(actual).isEqualTo(PlayStatus.BUST);
    }

    @Test
    @DisplayName("조건에 만족할 때 까지 카드르 뽑는다. (BUST X)")
    void drawCards_NOT_BUST() {
        // give
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck(new NoShuffleDeck());
        dealer.init(cardDeck);

        // when
        dealer.drawCards(cardDeck);
        PlayStatus actual = dealer.getStatus();

        // then
        assertThat(actual).isEqualTo(PlayStatus.HIT);
    }

    @Test
    @DisplayName("처음 받은 카드 중에 한 장의 카드를 공개한다.")
    void openCard() {
        // give
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck(new NoShuffleDeck());
        dealer.init(cardDeck);

        // when
        Card actual = dealer.openCard();

        // then
        assertThat(actual).isEqualTo(new Card(CLUB, KING));
    }
}