package blackjack.domain.participant;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardCount;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.PlayStatus;

public class Dealer extends Participant {

    private static final int HIT_CONDITION = 16;
    private static final String NAME = "딜러";

    public Dealer() {
        super();
    }

    void init(List<Card> rawCards) {
        for (Card rawCard : rawCards) {
            cards.add(rawCard);
        }
    }

    public CardCount drawCards(CardDeck cardDeck) {
        int count = 0;
        while (getStatus() == PlayStatus.HIT && getScore() <= HIT_CONDITION) {
            hit(cardDeck.drawCard());
            count++;
        }

        return CardCount.of(count);
    }

    public Card openCard() {
        return cards.findFirst();
    }

    public String getName() {
        return NAME;
    }
}
