package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.hippobeans.GeneralBSH;
import com.visitscotland.brxm.hippobeans.Megalinks;
import com.visitscotland.brxm.hippobeans.MegalinksBSH;
import com.visitscotland.brxm.hippobeans.OTYML;
import com.visitscotland.brxm.mapper.ImageMapper;
import com.visitscotland.brxm.model.megalinks.*;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.services.EnhancedLinkService;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.AnchorFormatter;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.utils.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MegalinkMapper extends ModuleMapper<Megalinks, LinksModule<EnhancedLink>> {

    public enum MegalinkLayout {
        HORIZONTAL_LINKS("Horizontal Links"),
        LIST_LAYOUT("List"),
        DEFAULT("Default"),
        GRID("Grid"),
        GRID_3("Grid 3"),
        GRID_4("Grid 4");

        private static final Map<String, MegalinkLayout> BY_VALUE =
                Arrays.stream(values()).collect(Collectors.toMap(MegalinkLayout::getValue, e -> e));

        private final String value;

        MegalinkLayout(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Optional<MegalinkLayout> fromValue(String value) { return Optional.ofNullable(BY_VALUE.get(value)); }

        @Deprecated(since = "2.12.0")
        public static boolean isCardGroup(MegalinkLayout layout) {
            return GRID_3 == layout || GRID_4 == layout || GRID == layout ;
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(MegalinkMapper.class);

    public static final int MAX_ITEMS = 6;
    public static final int MIN_ITEMS_CAROUSEL = 5;

    public static final String OTYML = "otyml";

    private final LinkService linkService;
    private final ResourceBundleService bundle;
    private final ImageMapper imageMapper;
    private final ContentLogger contentLogger;
    private final AnchorFormatter anchorFormatter;
    private final EnhancedLinkService enhancedLinkService;

    public MegalinkMapper(LinkService linkService, ResourceBundleService bundle, ImageMapper imageMapper,
                          ContentLogger contentLogger, AnchorFormatter anchorFormatter,
                          EnhancedLinkService enhancedLinkService) {
        this.linkService = linkService;
        this.bundle = bundle;
        this.imageMapper = imageMapper;
        this.contentLogger = contentLogger;
        this.anchorFormatter = anchorFormatter;
        this.enhancedLinkService = enhancedLinkService;
    }

    @Override
    void addLabels(PageCompositionHelper compositionHelper) throws MissingResourceException {
        compositionHelper.addGlobalLabel("third-party-error");
    }

    @Override
    LinksModule<EnhancedLink> map(Megalinks document, PageCompositionHelper compositionHelper) throws PageCompositionException {
        return processMegalinks(document, compositionHelper);
    }

    /**
     * Creates a LinkModule from a Megalinks document
     */
    private LinksModule<EnhancedLink> processMegalinks(Megalinks item, PageCompositionHelper compositionHelper) throws PageCompositionException {
        if (item.getPersonalization().isEmpty()) {
            LinksModule<EnhancedLink> al = getMegalinkModule(item, compositionHelper.getLocale());

            validateLinks(al);
            calculateAlignment(al, compositionHelper);
            calculateTheme(al, compositionHelper);

            return al;
        } else {
            //TODO: Create an implementation for personalization
            throw new PageCompositionException(item.getPath(), "Personalization is not currently supported");
        }
    }

    /**
     * Validates that the module has at least one link to render
     * @param module
     * @throws PageCompositionException
     */
    private void validateLinks(LinksModule<?> module) throws PageCompositionException {
        int numLinks = module.getLinks().size();
        if (numLinks == 0 && module instanceof MultiImageLinksModule) {
            numLinks = ((MultiImageLinksModule) module).getFeaturedLinks().size();
        }
        if (numLinks == 0) {
            throw new PageCompositionException(module.getDocumentPath(), "Megalinks module does not contain any valid items");
        }
    }

    /**
     * Calculate the alignment for the module
     * @param module
     * @param compositionHelper
     */
    private void calculateAlignment(LinksModule<?> module, PageCompositionHelper compositionHelper) {
        if (module instanceof SingleImageLinksModule) {
            module.setAlignment(compositionHelper.calculateAlignment());
            if (Contract.isEmpty(module.getAlignment())) {
                logger.warn("The Single Image Megalink module for {} does not have the alignment field defined", module.getDocumentPath());
            }
        }
    }

    /**
     * Calculate the index for the background colour
     * @param module
     * @param compositionHelper
     */
    private void calculateTheme(LinksModule<?> module, PageCompositionHelper compositionHelper) {
        module.setThemeIndex(compositionHelper.calculateThemeIndex(!Contract.isEmpty(module.getTitle())));
    }

    public LinksModule<EnhancedLink> getMegalinkModule(Megalinks doc, Locale locale)  {
        var layout = MegalinkLayout.fromValue(doc.getLayout()).orElse(MegalinkLayout.DEFAULT);
        if (MegalinkLayout.fromValue(doc.getLayout()).isEmpty()) {
            logger.warn("The Megalinks layout hasn't been set for {}", doc.getPath());
        }

        if (MegalinkLayout.isCardGroup(layout)) {
            return getCardGroupModule(doc, locale, layout);
        } else if (layout == MegalinkLayout.HORIZONTAL_LINKS) {
            if (doc.getMegalinkItems().size() >= MIN_ITEMS_CAROUSEL) {
                return horizontalListLayout(doc, locale);
            } else {
                return listLayout(doc, locale);
            }
        } else if (layout == MegalinkLayout.LIST_LAYOUT || doc.getMegalinkItems().size() > MAX_ITEMS) {
            return listLayout(doc, locale);
        } else if (doc.getSingleImageModule() != null) {
            return singleImageLayout(doc, locale);
        }

        return multiImageLayout(doc, locale);
    }

    public LinksModule<EnhancedLink> getCardGroupModule(Megalinks doc, Locale locale, MegalinkLayout layout) {

        if (layout != MegalinkLayout.GRID) {
            //TODO: This if block can be removed after version 2.12.0
            return buildCardGroupModule(doc, locale, MegalinkLayout.fromValue(doc.getLayout()).orElseThrow());
        } else if (doc.getMegalinkItems().size() > 4) {
            return listLayout(doc, locale);
        } else if (doc.getMegalinkItems().size() == 4) {
            return buildCardGroupModule(doc, locale, MegalinkLayout.GRID_4);
        } else {
            CardGroupModule module = buildCardGroupModule(doc, locale, MegalinkLayout.GRID_3);
            if (doc.getMegalinkItems().size() < 3) {
                contentLogger.warn("The Megalinks document located at {} has less than 3 items", doc.getPath());
            }
            return module;
        }
    }

    public CardGroupModule buildCardGroupModule(Megalinks doc, Locale locale, MegalinkLayout layout) {
        CardGroupModule module = new CardGroupModule();
        populateCommonFields(module, doc, locale);
        module.setLayout(layout.getValue());

        module.setLinks(enhancedLinkService.convert(module, doc.getMegalinkItems(), locale, false));
        return module;
    }

    /**
     * Converts a Megalinks module into a {@code ListLinksModule} Object for easier the consumption of the data on the front
     * end.
     *
     * @param doc    Megalink module to be converted.
     * @param locale Locale of the request
     * @return {@code ListLinksModule} containing the relevant information from the Megalinks module
     */
    public ListLinksModule listLayout(Megalinks doc, Locale locale) {
        ListLinksModule ll = new ListLinksModule();
        populateCommonFields(ll, doc, locale);

        ll.setTeaserVisible(doc.getTeaserVisible());
        ll.setLinks(enhancedLinkService.convert(ll, doc.getMegalinkItems(), locale, false));
        return ll;
    }

    public HorizontalListLinksModule horizontalListLayout(Megalinks doc, Locale locale) {
        HorizontalListLinksModule hll = new HorizontalListLinksModule();
        populateCommonFields(hll, doc, locale);

        hll.setTeaserVisible(doc.getTeaserVisible());
        hll.setLinks(enhancedLinkService.convert(hll, doc.getMegalinkItems(), locale, false));

        return hll;
    }

    public HorizontalListLinksModule horizontalListLayout(OTYML doc, Locale locale) {
        HorizontalListLinksModule hll = new HorizontalListLinksModule();
        hll.setTitle(Contract.isEmpty(doc.getTitle()) ? (bundle.getResourceBundle(OTYML, "otyml.title.default", locale)) : doc.getTitle());
        hll.setIntroduction(doc.getIntroduction());
        hll.setLinks(enhancedLinkService.convert(hll, doc.getMegalinkItems(), locale, true, false));

        return hll;
    }

    public HorizontalListLinksModule horizontalListLayout(GeneralBSH page, Locale locale) {
        HorizontalListLinksModule hll = new HorizontalListLinksModule();
        hll.setTitle(bundle.getResourceBundle(OTYML, "otyml.title.default", locale));
        hll.setLinks(enhancedLinkService.convert(hll, page.getLinks(), locale, true, false));

        return hll;
    }

    /**
     * Converts a Megalinks module into a {@code SingleImageLinksModule} Object for easier the consumption of the data on the front
     * end.
     *
     * @param doc    Megalink module to be converted.
     * @param locale Locale of the request
     * @return {@code SingleImageLinksModule} containing the relevant information from the Megalinks module
     */
    public SingleImageLinksModule singleImageLayout(Megalinks doc, Locale locale) {
        SingleImageLinksModule sil = new SingleImageLinksModule();
        populateCommonFields(sil, doc, locale);

        sil.setInnerTitle(doc.getSingleImageModule().getTitle());
        sil.setInnerIntroduction(doc.getSingleImageModule().getIntroduction());
        sil.setImage(imageMapper.createImage(doc.getSingleImageModule().getImage(), sil, locale));
        if (doc.getSingleImageModule().getImage() == null) {
            sil.addErrorMessage(String.format("The image selected for '%s' is not available. Please select a valid image for the single image document '%s' at: %s", sil.getTitle(), doc.getDisplayName(), doc.getPath()));
        }
        sil.setLinks(enhancedLinkService.convert(sil, doc.getMegalinkItems(), locale, false));

        return sil;
    }

    /**
     * Converts a Megalinks module into a MultiImageLinksModule Object for easier the consumption of the data on the front
     * end.
     * <p>
     * The number of featured items marked on the module might change depending on the following rules:
     * <ul>
     * <li>Megalinks with 2 or less featured items won't have featured items</li>
     * <li>Megalinks with 3 items might have up to one featured items</li>
     * <li>Megalinks with 4 or more items will have between 1 and 2 featured items</li>
     * <li>When the number of featured items exceed what is expected, only the first items will remain as Featured</li>
     * <li>When the number of featured items is inferior to the expected, the first non-featured items will be promoted
     * to featured items</li>
     * </ul>
     *
     * @param doc    Megalink module to be converted.
     * @param locale Locale of the request
     * @return MultiImageLinksModule containing the relevant information from the Megalinks module
     */
    public MultiImageLinksModule multiImageLayout(Megalinks doc, Locale locale) {
        MultiImageLinksModule fl = new MultiImageLinksModule();
        populateCommonFields(fl, doc, locale);
        fl.setTeaserVisible(doc.getTeaserVisible());

        fl.setLinksSize(doc.getMegalinkItems().size());
        fl.setLinks(enhancedLinkService.convert(fl, doc.getMegalinkItems(), locale, false));

        if (fl.getLinks().size() == 1) {
            //If the megalinks only have one item, that one is featured
            fl.setFeaturedLinks(fl.getLinks());
            fl.setLinks(Collections.emptyList());
        } else if (fl.getLinks().size() > 2) {
            //For 3 links the maximum of 1 featured item.  From 4 on the maximum is 2 featured items.
            fl.setFeaturedLinks(fl.getLinks().stream()
                    .filter(EnhancedLink::isFeatured)
                    .limit(fl.getLinks().size() == 3 ? 1 : 2)
                    .collect(Collectors.toCollection(ArrayList::new)));

            //When there is more than 3 items and no featured item the first item is promoted as featured.
            if (fl.getFeaturedLinks().isEmpty() && fl.getLinks().size() > 3) {
                fl.getFeaturedLinks().add(fl.getLinks().get(0));
            }

            //Links added to the Featured list MUST be removed from the original list
            fl.getLinks().removeAll(fl.getFeaturedLinks());
        } else {
            fl.setFeaturedLinks(Collections.emptyList());
        }

        return fl;
    }

    /**
     * Populate the common fields among all layouts
     *
     * @param target target module to be populated
     * @param doc    Megalinks module with the data source
     * @param locale consumer language.
     */
    private void populateCommonFields(LinksModule<?> target, Megalinks doc, Locale locale) {
        target.setHippoBean(doc);
        target.setTitle(doc.getTitle());
        target.setIntroduction(doc.getIntroduction());

        addSpecialFields(doc, target, locale);

        if (doc.getProductItem() != null) {
            target.setCta(linkService.createFindOutMoreLink(target, locale, doc.getProductItem()));
        }
    }

    private void addSpecialFields(Megalinks doc, LinksModule<?> module, Locale locale) {
        if (doc instanceof MegalinksBSH) {
            MegalinksBSH megalinksBSH = (MegalinksBSH) doc;
            module.setNested(Boolean.TRUE.equals(megalinksBSH.getNested()));
            module.setAnchor(anchorFormatter.getAnchorOrFallback(megalinksBSH.getAnchor(), megalinksBSH::getTitle));
            if (megalinksBSH.getProductsCMS() != null) {
                module.setCta(linkService.createFindOutMoreLink(module, locale, (megalinksBSH.getProductsCMS())));
            }
        }
    }
}
