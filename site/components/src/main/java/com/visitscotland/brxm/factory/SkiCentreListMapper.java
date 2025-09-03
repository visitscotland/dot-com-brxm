package com.visitscotland.brxm.factory;

import com.visitscotland.brxm.hippobeans.*;

import com.visitscotland.brxm.model.SkiListModule;
import com.visitscotland.brxm.model.SkiModule;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.services.DocumentUtilsService;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.brxm.utils.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.utils.pagebuilder.PageCompostionException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SkiCentreListMapper extends ModuleMapper<SkiCentreList, SkiListModule>{

    static final String BUNDLE_FILE = "ski";

    private final DocumentUtilsService documentUtils;
    private final LinkService linkService;
    private final SkiCentreMapper skiCentreMapper;

    private final ContentLogger contentLogger;

    public SkiCentreListMapper(DocumentUtilsService documentUtils, LinkService linkService, SkiCentreMapper skiCentreMapper, ContentLogger contentLogger) {
        this.documentUtils = documentUtils;
        this.linkService = linkService;
        this.skiCentreMapper = skiCentreMapper;
        this.contentLogger = contentLogger;
    }

    @Override
    void addLabels(PageCompositionHelper compositionHelper) throws MissingResourceException {
        compositionHelper.addAllSiteLabels(BUNDLE_FILE);
    }

    public SkiListModule map(SkiCentreList document, PageCompositionHelper compositionHelper){
        return map(document, compositionHelper.getLocale());
    }

    public SkiListModule map(SkiCentreList document, Locale locale){
        SkiListModule module = new SkiListModule();
        module.setHippoBean(document);

        module.setTitle(document.getTitle());
        module.setIntroduction(document.getCopy());
        module.setSkiCentres(new ArrayList<>());

        for (Page page:  document.getMegalinkItems()){
            Optional<EnhancedLink> link = linkService.createEnhancedLink(page, module,locale, false);

            if (!link.isPresent()){
                String message = String.format("Page for the document %s was not found.", page.getDisplayName());
                module.addErrorMessage(message);
                contentLogger.error("{} (Path={})", message, document.getPath());
            }  else {
                List<SkiCentre> skiDocument = documentUtils.getSiblingDocuments(page, SkiCentre.class, "visitscotland:SkiCentre");
                if (skiDocument.isEmpty()){

                    String message = String.format("Ski Centre Document wasn't found for %s.", page.getDisplayName());
                    module.addErrorMessage(message);
                    contentLogger.warn("{}. (Path={})", message, document.getPath());
                } else {
                    if (skiDocument.size() > 1) {
                        String message = String.format("Too Many Ski Centre Documents for %s. Only one document should be defined for this type of page.",
                                page.getDisplayName());
                        module.addErrorMessage(message);
                        contentLogger.error("{} (Path={})", message, document.getPath());
                    }
                    SkiModule skiModule = skiCentreMapper.map(skiDocument.get(0), locale);
                    //Title is overridden with the title of the page.
                    skiModule.setTitle(page.getTitle());
                    skiModule.setIntroduction(null);
                    skiModule.setCmsPage(link.get());

                    module.addSkiModule(skiModule);
                }
            }
        }
        return module;
    }
}
