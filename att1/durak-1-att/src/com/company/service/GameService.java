package com.company.service;

import com.company.Elements.*;
import com.company.field.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;


public class GameService {
    private final Scanner sc = new Scanner(System.in);
    private boolean isFirstRound = true;

    public void play(Game g) {
        setup(g);
        game(g);
    }

    private void game(Game g) {
        while (isAlive(g)) {
            Player attack;
            Player defense;
            if(isFirstRound){
                attack = getFirstAttacker(g);
                defense = g.getDefense();
            }else {
                attack = getAttack(g);
                defense = getDefense(g);
            }

            System.out.println("Attack' cards Player: " + attack.getName()  + attack.getPlayerCards().toString());
            System.out.println("Defense' cards Player " + defense.getName() + defense.getPlayerCards().toString());
            System.out.println();
            System.out.println();
            System.out.println("The attack move Player:" + attack.getName() +"\nChose the card (number in your coloda):\n" + attack.getPlayerCards().toString());
            attackMove(g);
            System.out.println("The defense move Player:" + defense.getName() + "\nChose the card to define (number in your coloda):\n" + defense.getPlayerCards().toString());
            defenseMove(g);
            getCard(g);
        }
    }

    private void getCard(Game g) {
        Stack<Card> coloda = g.getColoda();
        ArrayList<Card> f1 = g.getAttack().getPlayerCards();
        ArrayList<Card> s1 = g.getDefense().getPlayerCards();
            if(f1.size() < 6) {
            f1.add(coloda.pop());
            g.getAttack().setPlayerCards(f1);
        }
        if(s1.size() < 6) {
            s1.add(coloda.pop());
            g.getDefense().setPlayerCards(s1);
        }
        g.setColoda(coloda);
    }

    private boolean defenseMove(Game g) {
        int num = sc.nextInt();
        ArrayList<Card> defenseList = g.getDefense().getPlayerCards();
        Card cardToDefine = defenseList.get(num - 1);
        System.out.println("Define: " + cardToDefine.toString());
        Card attackCard = g.getCardToDefine();

        if(cardToDefine.compareTo(attackCard) > 0) {
            g.setPeak(false);
            g.getDefense().setPlayerCards(defenseList);
            System.out.println("Defense player didn't pick up the card");
            return true;
        }else if(cardToDefine.getSuit() == g.getTrump().getSuit() && attackCard.getSuit() != g.getTrump().getSuit() && cardToDefine.compareTo(attackCard) < 0){
            g.setPeak(false);
            g.getDefense().setPlayerCards(defenseList);
            System.out.println("Defense player didn't pick up the card");
            return true;
        }else if(cardToDefine.compareTo(attackCard) < 0) {
            g.setPeak(true);
            defenseList.add(attackCard);
            g.getDefense().setPlayerCards(defenseList);
            System.out.println("Defense player picked up the card");
            return false;
        }
        return false;
    }

    private void attackMove(Game g) {
        int num = sc.nextInt();
        ArrayList<Card> attackList = g.getAttack().getPlayerCards();
        Card attackCard = attackList.get(num - 1);
        System.out.println("Attack: " + attackCard.toString());
        attackList.remove(num - 1);
        g.setCardToDefine(attackCard);
        g.getAttack().setPlayerCards(attackList);
    }

    private Player getDefense(Game g) {
        if(g.getAttack().equals(g.getFirstPlayer())) {
            g.setDefense(g.getSecondPlayer());
            return g.getDefense();
        }else if (g.getAttack().equals(g.getSecondPlayer())){
            g.setDefense(g.getFirstPlayer());
            return g.getDefense();
        }
        return null;
    }

    private Player getAttack(Game g) {
        if(g.getFirstPlayer().equals(g.getDefense()) && !g.isPeak()) {
            g.setAttack(g.getFirstPlayer());
            return g.getFirstPlayer();
        }else if (g.getFirstPlayer().equals(g.getDefense()) && g.isPeak()) {
            g.setAttack(g.getSecondPlayer());
            return g.getSecondPlayer();
        }else if(g.getSecondPlayer().equals(g.getDefense()) && !g.isPeak()) {
            g.setAttack(g.getSecondPlayer());
            return g.getSecondPlayer();
        }else if(g.getSecondPlayer().equals(g.getDefense()) && g.isPeak()) {
            g.setAttack(g.getFirstPlayer());
            return g.getFirstPlayer();
        }
        return null;
    }

    private Player getFirstAttacker(Game g) {
        this.isFirstRound = false;
        Card f1 = g.getFirstPlayer().getPlayerCards().get(0);
        Card s1 = g.getSecondPlayer().getPlayerCards().get(0);
        Player attack;
        Player defense;
        if(f1.compareTo(s1) > 0 || f1.compareTo(s1) == 0) {
            attack = g.getFirstPlayer();
            defense = g.getSecondPlayer();
        }else {
            attack = g.getSecondPlayer();
            defense = g.getFirstPlayer();
        }
        g.setAttack(attack);
        g.setDefense(defense);
        return attack;
    }

    private boolean isAlive(Game g) {
        return !((g.getFirstPlayer().getPlayerCards().size() == 0 && g.getColoda().size() == 0) || (g.getSecondPlayer().getPlayerCards().size() == 0 && g.getColoda().size() == 0));
    }

    private void setup(Game g) {
        System.out.println("We are playing the game 'Дурак'");
        System.out.print("Fill in the name of the first player: ");
        String firstPlayer = sc.nextLine();
        System.out.print("\nFill in the name of the second player: ");
        System.out.println();
        String secondPlayer = sc.nextLine();
        createPlayers(firstPlayer, secondPlayer, g);
        createCards(g);
        handOutCards(g);
    }

    private void createPlayers(String fPlayer, String sPlayer, Game g) {
        g.setFirstPlayer(new Player(fPlayer));
        g.setSecondPlayer(new Player(sPlayer));
    }

    private void createCards(Game g) {
        Stack<Card> deck = new Stack<>();
        Stack<Card> playDeck = new Stack<>();
        for (Suit suit : Suit.items) {
            for (Face face : Face.items) {
                deck.push(new Card(suit, face));
            }
        }
        shuffle(deck);
        Card trump = deck.remove(35);
        g.setTrump(trump);
        playDeck.push(trump);
        for (Card card : deck) {
            playDeck.push(card);
        }
        g.setColoda(playDeck);
    }

    private void shuffle(Stack<Card> cards) {
        Collections.shuffle(cards);
    }

    private void handOutCards(Game g) {
        g.getFirstPlayer().setPlayerCards(takeCardsFromColoda(6, g));
        g.getSecondPlayer().setPlayerCards(takeCardsFromColoda(6, g));
        sortCards(g);
    }

    private void sortCards(Game g) {
        ArrayList<Card> fcards = g.getFirstPlayer().getPlayerCards();
        fcards.sort(new CardComparatorByFaceSuit());
        g.getFirstPlayer().setPlayerCards(fcards);
        ArrayList<Card> scards = g.getSecondPlayer().getPlayerCards();
        scards.sort(new CardComparatorByFaceSuit());
        g.getSecondPlayer().setPlayerCards(scards);
    }

    private ArrayList<Card> takeCardsFromColoda(int amountCards, Game g) {
        Stack<Card> coloda = g.getColoda();
        ArrayList<Card> taking = new ArrayList<>();
        for(int i = 0; i < amountCards; i++) {
            taking.add(coloda.pop());
        }
        g.setColoda(coloda);
        return taking;
     }

}
