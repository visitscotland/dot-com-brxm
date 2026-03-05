package com.visitscotland.brxm.favourites;

import com.visitscotland.brxm.model.favourites.FavouritesCard;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO - include field for errors/failed UUIDs
 */
public class FavouritesCardResponse {

    private List<FavouritesCard> cards = new ArrayList<>();
    private List<String> failedUuids = new ArrayList<>(); // UUIDs of cards we failed to generate content for

    public List<FavouritesCard> getCards() {
        return cards;
    }

    public void setCards(List<FavouritesCard> cards) {
        this.cards = cards;
    }

    public List<String> getFailedUuids() {
        return failedUuids;
    }

    public void setFailedUuids(List<String> failedUuids) {
        this.failedUuids = failedUuids;
    }
}
