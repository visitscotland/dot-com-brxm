package com.visitscotland.brxm.pagebuilder.page.adapter;

import com.visitscotland.brxm.hippobeans.Destination;
import com.visitscotland.brxm.hippobeans.General;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.mapper.page.CategoryCardsMapper;
import com.visitscotland.brxm.mapper.page.KeyInformationMapper;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.model.PageTemplate;
import com.visitscotland.brxm.pagebuilder.page.PageTemplateInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DestinationPageAdapter implements PageAdapter {

    private final Logger log = LoggerFactory.getLogger(DestinationPageAdapter.class);

    private final KeyInformationMapper keyInformationMapper;
    private final PageTemplateInitializer pageTemplateInitializer;

    public DestinationPageAdapter(PageTemplateInitializer pageTemplateInitializer, KeyInformationMapper keyInformationMapper) {
        this.keyInformationMapper = keyInformationMapper;
        this.pageTemplateInitializer = pageTemplateInitializer;
    }

    @Override
    public Optional<PageTemplate> getPageIntro(PageCompositionHelper pageConfig) {
        try {
            PageTemplate template = pageTemplateInitializer.getPageTemplate(pageConfig);
            Destination page = pageConfig.getPage();

            if (page.getKeyInformation() != null) {
                template.setKeyInformationPanel(
                        keyInformationMapper.getKeyInformationModule(page.getKeyInformation(), pageConfig.getLocale()));
            }
            return Optional.of(template);
        } catch (PageCompositionException e) {
            log.error("Error while composing page intro for Destination page: {}" , e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean supports(Page page) {
        return page instanceof Destination;
    }
}
