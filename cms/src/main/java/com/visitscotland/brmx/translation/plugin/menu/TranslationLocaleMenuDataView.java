package com.visitscotland.brmx.translation.plugin.menu;

import com.visitscotland.brmx.translation.plugin.TranslationWorkflowPlugin;
import com.visitscotland.brmx.translation.plugin.UntranslatedLocale;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.hippoecm.frontend.translation.ILocaleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranslationLocaleMenuDataView extends DataView<ILocaleProvider.HippoLocale> {
    private static final Logger LOG = LoggerFactory.getLogger(TranslationLocaleMenuDataView.class);
    private final IModel<String> languageModel;
    private TranslationWorkflowPlugin plugin;

    public TranslationLocaleMenuDataView(String id, TranslationWorkflowPlugin plugin, IModel<String> languageModel, MenuLocaleProvider menuItemProvider) {
        super(id, menuItemProvider);
        this.plugin = plugin;
        this.languageModel = languageModel;
        onPopulate();
    }

    @Override
    protected void populateItem(Item<ILocaleProvider.HippoLocale> item) {
        // Only want to display a menu item if there is an existing translation, or a translate option
        final ILocaleProvider.HippoLocale locale = item.getModelObject();
        final String language = locale.getName();
        final String currentDocumentLanguage = plugin.getCurrentlySelectedDocumentLocale();

        if (locale instanceof UntranslatedLocale) {
            if (currentDocumentLanguage.equals("en")) {
                item.add(new TranslationAction(plugin, "language", new LoadableDetachableModel<String>() {

                    @Override
                    protected String load() {
                        return locale.getDisplayName(getLocale());
                    }
                }, item.getModel()
                ));
            } else {
                LOG.debug("Not displaying clone menu item for not english document");
            }
        } else {
            item.add(new ViewTranslationAction(plugin, "language", new LoadableDetachableModel<String>() {

                @Override
                protected String load() {
                    return locale.getDisplayName(getLocale());
                }
            }, item.getModel(), language, languageModel
            ));
        }
    }

    @Override
    protected void onDetach() {
        languageModel.detach();
        super.onDetach();
    }
}
