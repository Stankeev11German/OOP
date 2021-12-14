package com.company.field;

import com.company.Elements.Card;
import com.company.Elements.Player;

import java.util.Stack;

public class Game {
    private Player firstPlayer;
    private Player secondPlayer;
    private Player attack;
    private Player defense;
    private boolean isPeak;
    private Card trump;
    private Stack<Card> coloda;
    private Card cardToDefine;

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public Card getTrump() {
        return trump;
    }

    public void setTrump(Card trump) {
        this.trump = trump;
    }

    public Stack<Card> getColoda() {
        return coloda;
    }

    public void setColoda(Stack<Card> coloda) {
        this.coloda = coloda;
    }

    public Player getAttack() {
        return attack;
    }

    public void setAttack(Player attack) {
        this.attack = attack;
    }

    public Player getDefense() {
        return defense;
    }

    public void setDefense(Player defense) {
        this.defense = defense;
    }

    public boolean isPeak() {
        return isPeak;
    }

    public void setPeak(boolean peak) {
        isPeak = peak;
    }

    public Card getCardToDefine() {
        return cardToDefine;
    }

    public void setCardToDefine(Card cardToDefine) {
        this.cardToDefine = cardToDefine;
    }
}
