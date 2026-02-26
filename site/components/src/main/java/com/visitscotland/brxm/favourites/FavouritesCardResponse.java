package com.visitscotland.brxm.favourites;

import com.visitscotland.brxm.model.favourites.FavouritesCard;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO - include field for errors/failed UUIDs
 */
public class FavouritesCardResponse {

    private List<FavouritesCard> cards = new ArrayList<>();

    public List<FavouritesCard> getCards() {
        return cards;
    }

    public void setCards(List<FavouritesCard> cards) {
        this.cards = cards;
    }
}
