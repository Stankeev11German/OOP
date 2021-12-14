package com.Stankeev11German;


import com.Stankeev11German.elements.Game;
import com.Stankeev11German.services.GameService;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        GameService svc = new GameService();
        svc.play(game, 5);
    }
}
