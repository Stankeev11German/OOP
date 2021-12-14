package com.company.Elements;

import java.util.ArrayList;
import java.util.Objects;

public class Player {
    String name;

    private ArrayList<Card> playerCards;

    public Player(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addCardToPlayer(Card c){
        if(!this.playerCards.contains(c)) {
            playerCards.add(c);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(getPlayerCards(), player.getPlayerCards());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getPlayerCards());
    }

    public ArrayList<Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(ArrayList<Card> playerCards) {
        this.playerCards = playerCards;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Player - ");
        sb.append(name);
        return sb.toString();
    }
}

