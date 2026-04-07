package com.visitscotland.brxm.pagebuilder;

import com.visitscotland.brxm.components.content.PageContentComponent;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.services.ResourceBundleService;
import org.hippoecm.hst.core.component.HstRequest;

import java.util.*;

import static com.visitscotland.brxm.components.content.PageContentComponent.LABELS;
import static com.visitscotland.brxm.components.content.PageContentComponent.PAGE_CONFIGURATION;
import static com.visitscotland.brxm.services.ResourceBundleService.GLOBAL_BUNDLE_FILE;

public class PageCompositionHelper {

    private final ResourceBundleService bundle;
    private final HstRequest request;
    private final CompositionModel model;

    public PageCompositionHelper(ResourceBundleService bundle, HstRequest request) {
        this.bundle = Objects.requireNonNull(bundle,  "bundle must not be null");
        this.request = Objects.requireNonNull(request, "request must not be null");
        this.model = new CompositionModel();
    }

    public Locale getLocale(){
        return request.getLocale();
    }

    public Page getPage() throws PageCompositionException {
        Object page = request.getAttribute(PageContentComponent.DOCUMENT);
        if (page == null){
            throw new PageCompositionException("The page document hasn't been defined");
        } else if (page  instanceof Page){
            return (Page) page;
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

    public void addRequestModel(String key, Object value) {
        request.setModel(key, value);
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
