package com.visitscotland.brxm.factory;

import com.visitscotland.brxm.hippobeans.Stackla;
import com.visitscotland.brxm.model.UserGeneratedContentModule;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.pagebuilder.PageCompositionHelper;
import com.visitscotland.utils.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

@Component
public class UserGeneratedContentMapper implements ModuleMapper<Stackla, UserGeneratedContentModule> {

    private final ResourceBundleService bundle;
    private static final Logger logger = LoggerFactory.getLogger(UserGeneratedContentMapper.class);
    static final String BUNDLE_ID = "ugc";

    public UserGeneratedContentMapper(ResourceBundleService bundle) {
        this.bundle = bundle;
    }

    public UserGeneratedContentModule map(Stackla document, Locale locale) {
        logger.info("Creating user generated content Module for {}", document.getPath());
        UserGeneratedContentModule ugc = new  UserGeneratedContentModule();
        ugc.setTitle(document.getTitle());
        ugc.setCopy(document.getCopy());
        ugc.setStorystreamId(document.getStorystreamId());
        ugc.setHippoBean(document);
        ugc.setNoCookiesMessage(bundle.getResourceBundle(BUNDLE_ID, "ugc.no-cookies-message", locale));
        ugc.setNoJsMessage(bundle.getResourceBundle(BUNDLE_ID, "ugc.no-js-message", locale));
        if (!Contract.isEmpty(document.getStacklaId()) || !Contract.isEmpty(document.getStacklaHash())){
            ugc.addErrorMessage("Stackla id and Stackla hash are not valid fields, please remove the values");
        }
        return ugc;
    }

    @Override
    public void include(Stackla document, PageCompositionHelper page) {
        UserGeneratedContentModule module = map(document, page.getLocale());
        page.addModule(Optional.of(module));
        page.addAllSiteLabels("ucg");
    }
}
