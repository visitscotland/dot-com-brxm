package com.visitscotland.brxm.favourites;

import com.visitscotland.brxm.model.favourites.FavouritesCard;
import com.visitscotland.brxm.utils.SiteProperties;
import org.hippoecm.hst.component.support.bean.BaseHstComponent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * should extract pages and information to be used in creating favourite cards
 * TODO - Complete this
 */
@Component
public class FavouritesRepository extends BaseHstComponent {

    private final FavouritesHstQueryService favouritesHstQueryService;
    private final FavouritesCardMapper favouritesCardMapper;
    private final SiteProperties siteProperties;

    protected FavouritesRepository(FavouritesHstQueryService favouritesHstQueryService, FavouritesCardMapper favouritesCardMapper,
                              SiteProperties siteProperties) {
        this.favouritesHstQueryService = favouritesHstQueryService;
        this.favouritesCardMapper = favouritesCardMapper;
        this.siteProperties = siteProperties;
    }

    // convert uuids to list of favourites card

    public List<FavouritesCard> getFavouritesCards(List<String> uuids) {
        favouritesCardMapper.getFavouritesCardsByUuid(uuids);
        return null;
    }


}
