package com.company.Elements;

import java.util.Comparator;

public class CardComparatorByFaceSuit implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {
        return o1.getFace().getRank() - o2.getFace().getRank();
    }
}
