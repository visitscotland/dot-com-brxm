package com.visitscotland.brxm.pagebuilder;

import com.visitscotland.brxm.components.content.PageContentComponent;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;
import com.visitscotland.brxm.pagebuilder.page.PageIntroAssembler;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.core.component.HstRequest;

import java.util.*;
import java.util.stream.Collectors;


import static com.visitscotland.brxm.services.ResourceBundleService.GLOBAL_BUNDLE_FILE;

public class PageCompositionHelper {

    public static final String LABELS = "labels";
    public static final String PAGE_CONFIGURATION = PageContentComponent.PAGE_CONFIGURATION;
    public static final String PAGE_TEMPLATE = "pageTemplate";

    private final PageIntroAssembler pageIntroAssembler;
    private final ResourceBundleService bundle;
    private final HstRequest request;
    private final CompositionModel model;
    private PageIntro pageTemplate;

    public PageCompositionHelper(ResourceBundleService bundle, PageIntroAssembler pageIntroAssembler, HstRequest request) {
        this.bundle = Objects.requireNonNull(bundle,  "bundle must not be null");
        this.pageIntroAssembler = pageIntroAssembler;
        this.request = Objects.requireNonNull(request, "request must not be null");
        this.model = new CompositionModel();
    }

    public Locale getLocale(){
        return request.getLocale();
    }

    public void initPageTemplate() {
        getPageTemplate();
    }

    //TODO: Review usage of this method
    public PageIntro getPageTemplate() {
        if (pageTemplate != null) {
            return pageTemplate;
        } else if (request.getModel(PAGE_TEMPLATE) != null) {
            pageTemplate = request.getModel(PAGE_TEMPLATE);
            return request.getModel(PAGE_TEMPLATE);
        } else {
            return setupPageIntro();
        }
    }


    /**
     * Sets up the page intro template by assembling it from the page document.
     * If the pageIntroAssembler is null, creates a basic PageIntro with an error message.
     * Caches the result in both "pageIntro" and PAGE_TEMPLATE model attributes.
     *
     * @return the assembled PageIntro template
     */
    private PageIntro setupPageIntro() {
        PageIntro template;
        Page page = null;

        try {
            page = getPage();

            if (pageIntroAssembler == null) {
                template = new PageIntro(page);
                //TODO: Log a more descriptive error message and add it to the logger
                template.addErrorMessage("There has been an internal error");
            } else {
                template = pageIntroAssembler.from(this);
            }
        } catch (PageCompositionException e) {
            template = new PageIntro(page);
            template.addErrorMessage(e.getMessage());
        }

        //TODO: pageIntro might be replaced by pageTemplate
        request.setModel("pageIntro", template);
        request.setModel(PAGE_TEMPLATE, template);

        this.pageTemplate = template;

        return template;
    }

    @SuppressWarnings("unchecked")
    public <P extends Page> P getPage() throws PageCompositionException {
        Object page = request.getAttribute(PageContentComponent.DOCUMENT);
        if (page == null){
            throw new PageCompositionException("The page document hasn't been defined");
        } else if (page instanceof Page){
            return (P) page;
        } else  {
            throw new PageCompositionException("The main document is not a Page instance. Class = " + page.getClass().getSimpleName());
        }
    }

    /**
     * @deprecated This method has been added to simplify the transition to the delivery API
     */
    @Deprecated(forRemoval = true)
    public HstRequest getRequest() {
        return request;
    }

    public void addModule(Module<?> module){
        model.addModule(module);
    }

    Optional<Module<?>> getFirstModule(){
        if (getModules().isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(getModules().get(0));
        }
    }

    public List<Module<?>> getModules(){
        return Collections.unmodifiableList(model.getModules());
    }

    public void addGlobalLabel(String key) {
        labels().computeIfAbsent(GLOBAL_BUNDLE_FILE, k -> new HashMap<>());
        labels().get(GLOBAL_BUNDLE_FILE)
                .put(key, bundle.getResourceBundle(GLOBAL_BUNDLE_FILE, key, getLocale()));
    }

    /**
     * Add all the labels related to a Resource Bundle file from the CMS
     *
     * @param bundleName the name of the resource bundle to process
     */
    public void addAllSiteLabels(String bundleName) {
        labels().put(bundleName, bundle.getAllLabels(bundleName, getLocale()));
    }
    public void addAllLabelsSpecificName(String bundleName, String nodeName) {
        labels().put(nodeName, bundle.getAllLabels(bundleName, getLocale()));
    }

    /**
     * Adds only the keys and values explicitly defined by the site, without falling back to the default version.
     *
     * <p>In contrast, {@code addAllSiteLabels} reads keys from the default version and values from the site.
     * If a value does not exist for a key, it falls back to the default version, ensuring a text is always available.
     *
     * <p>In some edge cases (such as social channels), the absence of a specific label indicates it is not applicable
     * to the site, so the fallback value must not be used.
     *
     * @param bundleName the name of the resource bundle to process
     */
    public void addSiteSpecificLabels(String bundleName) {
        labels().put(bundleName, bundle.getSiteSpecificLabels(bundleName, getLocale()));
    }

    private Map<String, Map<String, String>> labels() {
        Map<String, Map<String, String>> labels = request.getModel(LABELS);

        if (labels == null) {
            labels = new HashMap<>();
            request.setModel(LABELS, labels);
        }

        return labels;
    }

    //TODO review and move to Cludoservice if possible
    public Map<String, String> addValueListLabels(String bundleName, Map<String, String> valueList, String nodeName) {
        Map<String, String> resolvedLabels = new HashMap<>();
        if (valueList == null || valueList.isEmpty()) {
            return resolvedLabels;
        }

        for (Map.Entry<String, String> entry : valueList.entrySet()) {
            String value = entry.getValue();
            String label = bundle.getResourceBundle(bundleName, entry.getKey(), getLocale());

            if (Contract.isEmpty(label)) {
                label = value;
            }

            resolvedLabels.put(value, label);
        }

        Map<String, String> sorted = resolvedLabels.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        labels().put(nodeName, sorted);
        return sorted;
    }

    private Map<String, Object> pageConfiguration() {
        Map<String, Object> cfg = request.getModel(PAGE_CONFIGURATION);

        if (cfg == null) {
            cfg = new HashMap<>();
            request.setModel(PAGE_CONFIGURATION, cfg);
        }

        return cfg;
    }

    public void addProperty(String key, Object value) {
        if (value == null) {
            pageConfiguration().remove(key);
        } else {
            pageConfiguration().put(key, value);
        }
    }

    public String calculateAlignment(){
        return model.calculateAlignment();
    }

    public int calculateThemeIndex(boolean increment){
        return model.calculateThemeIndex(increment);
    }

    public boolean isEditMode() {
        return Boolean.TRUE.equals(request.getAttribute(PageContentComponent.EDIT_MODE));
    }

}
