package com.visitscotland.brxm.utils.pagebuilder;

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
        this.bundle = bundle;
        this.request = request;
        this.model = new CompositionModel();
    }

    public Locale getLocale(){
        return request.getLocale();
    }

    public void addModule(Optional<Module<?>> module){
        module.ifPresent(value -> model.getModules().add(value));
    }

    /**
     * This method has been created for backwards compatibility. Please use {@code addModule(Optional<Module>)} instead
     * @param module
     */
    @Deprecated
    void addModule(Module<?> module){
        addModule(Optional.ofNullable(module));
    }

    Optional<Module<?>> getFirstModule(){
        if (getModules().isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(getModules().get(0));
        }
    }

    List<Module<?>> getModules(){
        return model.getModules();
    }

    void addGlobalLabel(String key) {
        labels(request).computeIfAbsent(GLOBAL_BUNDLE_FILE, k -> new HashMap<>());
        labels(request).get(GLOBAL_BUNDLE_FILE)
                .put(key, bundle.getSiteResourceBundle(GLOBAL_BUNDLE_FILE, key, getLocale()));
    }

    public void addAllSiteLabels(String bundleName) {
        labels(request).put(bundleName, bundle.getAllSiteLabels(bundleName, getLocale()));
    }

    private Map<String, Map<String, String>> labels(HstRequest request) {
        if (request.getModel(LABELS) == null) {
            Map<String, Map<String, String>> labels = new HashMap<>();
            request.setModel(LABELS, labels);
            return labels;
        }

        return request.getModel(LABELS);
    }

    String calculateAlignment(){
        return model.calculateAlignment();
    }

    int calculateThemeIndex(boolean increment){
        return model.calculateThemeIndex(increment);
    }

}
