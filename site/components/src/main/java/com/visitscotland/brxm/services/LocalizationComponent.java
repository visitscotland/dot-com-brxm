package com.visitscotland.brxm.services;

import com.visitscotland.brxm.components.content.ContentComponent;
import org.apache.commons.lang3.LocaleUtils;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.core.internal.HstMutableRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Locale;

@Component
//TODO: Move class to a coherent location
public class LocalizationComponent {

    private static final Logger logger = LoggerFactory.getLogger(ContentComponent.class);

    public void setLocale() {
        HstMutableRequestContext requestContext = (HstMutableRequestContext) RequestContextProvider.get();
        String mountLocale = requestContext.getResolvedMount().getMount().getLocale();

        try {
            Locale currentLocale = requestContext.getPreferredLocale();
            Locale requestLocale = LocaleUtils.toLocale(mountLocale);

            if (!requestLocale.equals(currentLocale)) {
                logger.debug("Changing request locale from {} to {} ", currentLocale.getLanguage(), requestLocale.getLanguage());

                requestContext.setPreferredLocale(requestLocale);
                requestContext.setLocales(Collections.singletonList(requestLocale));

            }
        } catch (IllegalArgumentException e) {
            logger.error("Mount has invalid locale {}", mountLocale);
        }
    }
}
