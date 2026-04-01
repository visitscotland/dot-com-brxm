package com.visitscotland.brxm.favourites;

import com.visitscotland.brxm.components.content.service.FavouritesService;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.favourites.FavouritesCard;
import com.visitscotland.brxm.services.HippoUtilsService;
import org.hippoecm.hst.configuration.hosting.Mount;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.springframework.stereotype.Component;

import java.util.Locale;


/**
 * Mapper class to create favourites cards from supported page info
 */
@Component
public class FavouritesCardMapper {

    private static final boolean FULLY_QUALIFIED_URL = false;

    private static final String API = "/api/";
    private static final String HTTP = "http:";
    private static final String HTTPS = "https:";
    private static final String LOCALHOST = "localhost";
    private static final String FWD_SLASH = "/";

    private final FavouritesService favouritesService;

    public FavouritesCardMapper(FavouritesService favouritesService) {
        this.favouritesService = favouritesService;
    }

    public FavouritesCard getFavouritesCard(HippoBean bean) throws FavouritesException {
        Page page = getPage(bean);

        FavouritesCard card = new FavouritesCard();
        card.setUuid(page.getCanonicalHandleUUID());
        card.setTitle(page.getTitle());
        card.setTeaser(page.getTeaser());
        card.setImage(toURL(page.getHeroImage()));
        card.setUrl(toURL(page));

        return card;

    }

    private Page getPage(HippoBean bean) throws FavouritesException {

        if (bean == null) {
            throw new FavouritesException(String.format("Cannot find an item with UUID = %s", bean));
        }

        if (!favouritesService.isFavouritable(bean)) {
            throw new FavouritesException(String.format("Unsupported page type: %s", bean.getPath()));
        }

        if (bean instanceof Page) {
            return (Page) bean;
        } else {
            throw new FavouritesException(String.format("Only pages are supported at the moment. Document path =%s",
                    bean.getPath()));
        }

    }

    /**
     * returns fully qualified urls for pages and images
     *
     * @param document
     * @return url
     */
    private String toURL(final HippoBean document) throws FavouritesException {

        final HstRequestContext context = RequestContextProvider.get();
        final Mount mount;

        if (document instanceof HippoDocument){
            Locale locale = ((HippoDocument) document).getLocale();
            mount = new HippoUtilsService().getMountForLocale(locale);
        } else {
            mount = context.getResolvedMount().getMount();
        }

        String url = context.getHstLinkCreator().create(document.getNode(), mount)
                .toUrlForm(context, FULLY_QUALIFIED_URL);



        if (url == null) {
            throw new FavouritesException(String.format("Failed to get URL for document: %s", document.getPath()));
        } else if (url.endsWith("/content")){
            return url.substring(0, url.length()-8-1);
        }

//        if (isPageUrl) {
//            url = url.replace(API, FWD_SLASH);
//        }

        // TODO: This if block might not be required
//        if (!context.getVirtualHost().getHostName().contains(LOCALHOST)) {
//            url = url.replace(HTTP, HTTPS);
//        }

        return url;
    }




}
