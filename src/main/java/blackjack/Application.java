package blackjack;

import java.util.concurrent.ThreadLocalRandom;

import blackjack.controller.GameController;

public class Application {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.play();
    }
}
