package com.visitscotland.brxm.favourites;

import com.visitscotland.brxm.model.favourites.FavouritesCard;
import com.visitscotland.brxm.services.HippoUtilsService;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Extract pages and information to be used in creating favourite cards
 */
@Component
public class FavouritesRepository {

    private static final Logger logger = LoggerFactory.getLogger(FavouritesRepository.class);

    // matches any hexadecimal character (a-f and 0-9)
    private static final String HEX_REGEX = "[a-fA-F0-9]";
    private static final String UUID_REGEX = HEX_REGEX + "{8}-" + HEX_REGEX + "{4}-"+ HEX_REGEX + "{4}-" + HEX_REGEX + "{4}-" + HEX_REGEX + "{12}";

    private final FavouritesCardMapper favouritesCardMapper;
    private final HippoUtilsService hippoUtilsService;

    public FavouritesRepository(FavouritesCardMapper favouritesCardMapper, HippoUtilsService hippoUtilsService) {
        this.favouritesCardMapper = favouritesCardMapper;
        this.hippoUtilsService = hippoUtilsService;
    }

    /**
     * get page info by uuid
     */
    public FavouritesCardResponse getFavouritesCards(final FavouritesRequest favouritesRequest) {
        List<FavouritesCard> cards = new ArrayList<>();
        List<String> failedUuids = new ArrayList<>();
        FavouritesCardResponse response = new FavouritesCardResponse();

        if (favouritesRequest != null) {
            for (final String uuid : favouritesRequest.getUuids()) {
                if (uuid != null && uuid.matches(UUID_REGEX)) {
                    try {
                        final HippoBean page = hippoUtilsService.getContentByUuid(uuid);
                        final FavouritesCard card = favouritesCardMapper.getFavouritesCard(page);
                        cards.add(card);
                    } catch (FavouritesException e) {
                        logger.info("Unable to get favourites card content for UUID {}: {}}", uuid, e.getMessage());
                        failedUuids.add(uuid);
                    }
               } else {
                    logger.info("Invalid uuid provided: {}", uuid);
                    failedUuids.add(uuid);
                }
            }
        } else {
            logger.info("FavouritesRequest is null.");
            return null;
        }
        response.setCards(cards);
        response.setFailedUuids(failedUuids);
        return response;
    }
}