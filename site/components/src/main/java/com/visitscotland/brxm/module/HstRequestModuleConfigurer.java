package com.visitscotland.brxm.module;

import org.hippoecm.hst.core.component.HstRequest;
import com.visitscotland.brxm.model.Module;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.HashSet;
import java.util.Set;

public class HstRequestModuleConfigurer implements HstRequestConfigurer {
    private HstRequest request;
    private final Set<Module<?>> modules;

    private static final String PAGE_ITEMS_MODEL = "pageItems";

    public HstRequestModuleConfigurer() {
        this.modules = new HashSet<>();
    }

    @Override
    public HstRequestConfigurer forRequest(HstRequest request) {
        this.request = request;
        return this;
    }

    @Override
    public <T extends Module<?>> HstRequestConfigurer registerModule(Supplier<T> supplier) {
        this.modules.add(supplier.get());
        return this;
    }

    @Override
    public <T extends Module<?>> HstRequestConfigurer registerModules(Collection<T> modules) {
        this.modules.addAll(modules);
        return this;
    }

    @Override
    public void configure() {
        this.request.setModel(PAGE_ITEMS_MODEL, modules);
    }
}