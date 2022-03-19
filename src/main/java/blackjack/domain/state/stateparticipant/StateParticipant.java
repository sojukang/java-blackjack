package blackjack.domain.state.stateparticipant;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;
import blackjack.domain.participant.Name;
import blackjack.domain.state.State;
import blackjack.domain.state.started.Ready;

public abstract class StateParticipant {

    private State state;

    StateParticipant() {
        this.state = new Ready();
    }

    public final void init(Card firstCard, Card secondCard) {
        draw(firstCard);
        draw(secondCard);
    }

    public final State getState() {
        return state;
    }

    public final void draw(Card card) {
        this.state = state.draw(card);
    }

    public abstract Name getName();

    public final Cards getCards() {
        return state.getCards();
    }
}
