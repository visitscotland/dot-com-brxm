package com.visitscotland.brxm.favourites;

import com.google.common.collect.ImmutableList;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.favourites.FavouritesCard;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Mapper class to create favourites cards from supported page info
 */
@Component
public class FavouritesCardMapper {

    private static final Logger logger = LoggerFactory.getLogger(FavouritesCardMapper.class);

    // content types can be added here as they are supported (see also PageAssembler)
    private static final ImmutableList<String> FAVOURITABLE_CONTENT = ImmutableList.<String>builder()
            .add("visitscotland:Itinerary")
            .add("visitscotland:Destination")
            .add("visitscotland:Listicle")
            .build();

    public FavouritesCard getFavouritesCard(HippoBean bean){

        if (bean == null) {
            logger.info("Cannot parse favourites card from bean.");
            return null;
        }

        final Page page = ((Page) bean);
        if (page == null) {
            logger.info("Could not parse page from HippoBean.");
            return null;
        }

        final String contentType = bean.getContentType();

        if (contentType != null && FAVOURITABLE_CONTENT.contains(contentType)) {
           FavouritesCard card = new FavouritesCard();
           card.setUuid(bean.getCanonicalUUID());
           card.setTitle(page.getTitle());
           card.setIntroduction(page.getIntroduction().getContent());
           card.setImage(page.getHeroImage().getPath());
           card.setUrl(page.getPath());

            return card;

        } else {
            logger.info("Content type {} is not supported for favourite content.", bean.getContentType());
            return null;
        }

    }
}
