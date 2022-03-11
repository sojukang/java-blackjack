package blackjack.domain.card;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardDeck {

    private final Stack<Card> deck;

    private CardDeck(Stack<Card> deck) {
        this.deck = deck;
    }

    public static CardDeck create() {
        List<Card> list = getCards();
        Collections.shuffle(list);

        Stack<Card> deck = new Stack<>();
        deck.addAll(list);

        return new CardDeck(deck);
    }

    public static CardDeck createNoShuffle() {
        List<Card> list = getCards();

        Stack<Card> deck = new Stack<>();
        deck.addAll(list);

        return new CardDeck(deck);
    }

    private static List<Card> getCards() {
        return Arrays.stream(CardSymbol.values())
            .flatMap(symbol -> createSymbolCards(symbol).stream())
            .collect(toList());
    }

    private static List<Card> createSymbolCards(CardSymbol symbol) {
        return Arrays.stream(CardNumber.values())
            .map(cardNumber -> new Card(symbol, cardNumber))
            .collect(toUnmodifiableList());
    }

    public Card drawCard() {
        return deck.pop();
    }
}
