package com.company;

import com.company.field.Game;
import com.company.service.GameService;

public class Main {

    public static void main(String[] args) {
	    Game g = new Game();
        GameService gs = new GameService();
        gs.play(g);
    }
}
