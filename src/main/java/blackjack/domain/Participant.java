package blackjack.domain;

import java.util.ArrayList;

public abstract class Participant {
    protected Cards cards = new Cards(new ArrayList<>());

    public Participant() {
    }

    public void distribute(Cards cards) {
        this.cards = cards;
    }

    public abstract boolean isDrawable();

    public void draw(){
        this.cards.combine(Deck.popOne());
    }

    public Cards showCards(){
        return this.cards;
    }
}
