package com.visitscotland.brxm.favourites;

import com.visitscotland.brxm.model.favourites.FavouritesCard;

import java.util.List;

public class FavouritesCardResponse {

    private List<FavouritesCard> cards;

    public List<FavouritesCard> getCards() {
        return cards;
    }

    public void setCards(List<FavouritesCard> cards) {
        this.cards = cards;
    }
}
