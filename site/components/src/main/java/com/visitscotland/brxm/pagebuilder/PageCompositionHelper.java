package com.visitscotland.brxm.pagebuilder;

import com.visitscotland.brxm.components.content.PageContentComponent;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.services.ResourceBundleService;
import org.hippoecm.hst.core.component.HstRequest;

import java.util.*;

import static com.visitscotland.brxm.components.content.PageContentComponent.LABELS;
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

    public void addAllSiteLabels(String bundleName) {
        labels().put(bundleName, bundle.getAllLabels(bundleName, getLocale()));
    }

    private Map<String, Map<String, String>> labels() {
        if (request.getModel(LABELS) == null) {
            Map<String, Map<String, String>> labels = new HashMap<>();
            request.setModel(LABELS, labels);
            return labels;
        }

        return request.getModel(LABELS);
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
