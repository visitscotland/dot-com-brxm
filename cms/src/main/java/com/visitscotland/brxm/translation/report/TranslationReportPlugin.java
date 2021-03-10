package com.visitscotland.brxm.translation.report;

import org.hippoecm.frontend.plugin.IPluginContext;
import org.hippoecm.frontend.plugin.config.IPluginConfig;
import org.onehippo.cms7.reports.AbstractExtRenderPlugin;
import org.wicketstuff.js.ext.ExtComponent;
import org.wicketstuff.js.ext.ExtContainer;

public class TranslationReportPlugin extends AbstractExtRenderPlugin {
    private final ExtComponent panel;

    public TranslationReportPlugin(IPluginContext context, IPluginConfig config) {
        super(context, config);
        this.panel = new TranslationListPanel(context, config);
        this.add(this.panel);
    }

    public ExtComponent getExtComponent() {
        return this.panel;
    }
}
