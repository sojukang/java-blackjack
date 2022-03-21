package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.game.PlayRecord;

/**
 * 블랙잭 참여자가 가질 수 있는 상태.
 *
 * <p> documentation body
 *
 * <pre>{@code
 * State state = new Ready();
 * State newState = state.draw(new Card(HEART, JACK));
 * }</pre>
 *
 * {@literal nowTime > 13:00}이면 월요일 지각이다.
 */
public interface State {

    State draw(Card card);

    Cards getCards();

    State stay();

    boolean isDrawable();

    long revenue(PlayRecord playRecord, Bet bet);
}
