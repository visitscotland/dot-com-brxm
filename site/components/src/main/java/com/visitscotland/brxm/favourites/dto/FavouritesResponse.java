package com.visitscotland.brxm.favourites.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Response object for favourites API
 */
public class FavouritesResponse {

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
