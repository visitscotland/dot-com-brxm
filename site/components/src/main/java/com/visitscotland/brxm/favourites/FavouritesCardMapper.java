package com.visitscotland.brxm.favourites;

import com.google.common.collect.ImmutableList;
import com.visitscotland.brxm.components.content.GeneralContentComponent;
import com.visitscotland.brxm.hippobeans.General;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.favourites.FavouritesCard;
import org.hippoecm.hst.configuration.hosting.Mount;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Mapper class to create favourites cards from supported page info
 */
@Component
public class FavouritesCardMapper {

    private static final Logger logger = LoggerFactory.getLogger(FavouritesCardMapper.class);

    private static final String GENERAL_PAGE = "visitscotland:General";
    private static final String API = "/api/";
    private static final String HTTP = "http:";
    private static final String HTTPS = "https:";
    private static final String LOCALHOST = "localhost";
    private static final String FWD_SLASH = "/";

    // content types can be added here as they are supported (see also PageAssembler)
    private static final ImmutableList<String> FAVOURITABLE_CONTENT = ImmutableList.<String>builder()
            .add("visitscotland:Itinerary")
            .add("visitscotland:Destination")
            .add("visitscotland:Listicle")
            .build();


    public FavouritesCard getFavouritesCard(HippoBean bean) {

        if (bean == null) {
            logger.info("Cannot parse favourites card from bean.");
            return null;
        }

        final String contentType = bean.getContentType();
        if (contentType == null || contentType.isEmpty()) {
            logger.info("Cannot extract content type from bean.");
            return null;
        }

        if (!FAVOURITABLE_CONTENT.contains(contentType)) {
            if (!(bean instanceof General && GeneralContentComponent.STANDARD.equals((((General) bean).getTheme())))){
                logger.info("Unsupported content type: {}", bean.getContentType());
                if (contentType.equals(GENERAL_PAGE)) {
                    logger.info("General pages are only supported for 'Standard' layout.");
                }
                return null;
            }
        }

        final Page page = ((Page) bean);
        if (page == null) {
            logger.info("Could not parse page from HippoBean.");
            return null;
        }

        FavouritesCard card = new FavouritesCard();
        card.setUuid(page.getCanonicalHandleUUID());
        card.setTitle(page.getTitle());
        card.setTeaser(page.getTeaser());
        card.setImage(toURL(page.getHeroImage(), false));
        card.setUrl(toURL(page, true));

        return card;

    }

    /**
     * returns fully qualified urls for pages and images
     *
     * @param document
     * @return url
     */
    private String toURL(final HippoBean document, final boolean isPageUrl) {
        final boolean FULLY_QUALIFIED = true;
        final HstRequestContext context = RequestContextProvider.get();
        final Mount mount = context.getResolvedMount().getMount();

        String url = context.getHstLinkCreator().create(document.getNode(), mount)
                .toUrlForm(context, FULLY_QUALIFIED);
        if (isPageUrl) {
            url = url.replace(API, FWD_SLASH);
        }
        if (!context.getVirtualHost().getHostName().contains(LOCALHOST)) {
            url = url.replace(HTTP, HTTPS);
        }

        return url;
    }
}

