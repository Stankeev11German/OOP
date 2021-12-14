package com.company.Elements;


import java.util.Comparator;

public class Card implements Comparable<Card> {
    private Suit suit;
    private Face face;

    public Card(Suit suit, Face face) {
        this.suit = suit;
        this.face = face;
    }

    public Face getFace() {
        return face;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(face.toString());
        sb.append(" ");
        sb.append(suit.toString());
        return sb.toString();
    }

    @Override
    public int compareTo(Card o) {
        return this.face.getRank() - o.face.getRank();
    }
}

