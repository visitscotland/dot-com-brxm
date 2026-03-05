package com.visitscotland.brxm.favourites;

import com.visitscotland.brxm.model.favourites.FavouritesCard;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.utils.SiteProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * should extract pages and information to be used in creating favourite cards
 * TODO - Complete this
 */
@Component
public class FavouritesRepository {

    private final FavouritesCardMapper favouritesCardMapper;
    private final SiteProperties siteProperties;
    private final HippoUtilsService hippoUtilsService;

    // matches any hexadecimal character (a-f and 0-9)
    private static final String HEX_REGEX = "[a-fA-F0-9]";
    private static final String UUID_REGEX = HEX_REGEX + "{8}-" + HEX_REGEX + "{4}-"+ HEX_REGEX + "{4}-" + HEX_REGEX + "{4}-" + HEX_REGEX + "{12}";

    private static final Logger logger = LoggerFactory.getLogger(FavouritesRepository.class);


    public FavouritesRepository(FavouritesCardMapper favouritesCardMapper, SiteProperties siteProperties, HippoUtilsService hippoUtilsService) {
        this.favouritesCardMapper = favouritesCardMapper;
        this.siteProperties = siteProperties;
        this.hippoUtilsService = hippoUtilsService;
    }

    /**
     * get page info by uuid
     */
    public FavouritesCardResponse getFavouritesCards(FavouritesRequest favouritesRequest) {
        List<FavouritesCard> cards = new ArrayList<>();
        List<String> failedUuids = new ArrayList<>();
        FavouritesCardResponse response = new FavouritesCardResponse();

        if (favouritesRequest != null) {
            for (final String uuid : favouritesRequest.getUuids()) {
                if (uuid != null && uuid.matches(UUID_REGEX)) {
                    final FavouritesCard card = favouritesCardMapper.getFavouritesCard(hippoUtilsService.getContentByUuid(uuid));
                    if (card == null) {
                        logger.info("Unable to get favourites card content for UUID {}", uuid);
                        failedUuids.add(uuid);
                    } else {
                        cards.add(card);
                    }
               } else {
                    logger.info("Invalid uuid provided: {}", uuid);
                }
            }
        }
        response.setCards(cards);
        response.setFailedUuids(failedUuids);
        return response;
    }
}