/*
 *  Copyright 2010-2019 Hippo B.V. (http://www.onehippo.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.visitscotland.brmx.translation.plugin;

import com.visitscotland.brmx.translation.plugin.menu.MenuLocaleProvider;
import com.visitscotland.brmx.translation.plugin.menu.SendForTranslationAction;
import com.visitscotland.brmx.translation.plugin.menu.TranslationLocaleMenuDataView;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.resource.ResourceReference;
import org.hippoecm.addon.workflow.MenuDescription;
import org.hippoecm.addon.workflow.WorkflowDescriptorModel;
import org.hippoecm.frontend.model.JcrNodeModel;
import org.hippoecm.frontend.plugin.IPluginContext;
import org.hippoecm.frontend.plugin.config.IPluginConfig;
import org.hippoecm.frontend.plugins.reviewedactions.PublicationWorkflowPlugin;
import org.hippoecm.frontend.plugins.standards.icon.HippoIconStack;
import org.hippoecm.frontend.plugins.standards.image.CachingImage;
import org.hippoecm.frontend.service.IBrowseService;
import org.hippoecm.frontend.service.IconSize;
import org.hippoecm.frontend.service.render.RenderPlugin;
import org.hippoecm.frontend.session.UserSession;
import org.hippoecm.frontend.translation.DocumentTranslationProvider;
import org.hippoecm.frontend.translation.ILocaleProvider;
import org.hippoecm.frontend.translation.ILocaleProvider.HippoLocale;
import org.hippoecm.frontend.translation.ILocaleProvider.LocaleState;
import org.hippoecm.frontend.translation.TranslationUtil;
import org.hippoecm.repository.HippoStdNodeType;
import org.hippoecm.repository.api.WorkflowDescriptor;
import org.hippoecm.repository.api.WorkflowException;
import org.hippoecm.repository.api.WorkflowManager;
import org.hippoecm.repository.translation.HippoTranslatedNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TranslationWorkflowPlugin extends RenderPlugin {
    public static final String ID_CONTENT = "content";
    public static final String ID_CURRENT_LANGUAGE = "current-language";
    public static final String ID_LANGUAGE = "language";
    public static final String ID_LANGUAGES = "languages";
    public static final String ID_LABEL = "label";
    private static Logger log = LoggerFactory.getLogger(TranslationWorkflowPlugin.class);
    private final IModel<Boolean> canTranslateModel;
    private DocumentTranslationProvider translationProvider;
    private HippoTranslatedNodeFactory translatedNodeFactory;

    public TranslationWorkflowPlugin(IPluginContext context, IPluginConfig config) {
        this(context, config, new HippoTranslatedNodeFactory());
    }

    protected TranslationWorkflowPlugin(IPluginContext context, IPluginConfig config, HippoTranslatedNodeFactory translatedNodeFactory) {
        super(context, config);
        this.translatedNodeFactory = translatedNodeFactory;

        final IModel<String> languageModel = new LanguageModel(this);
        final ILocaleProvider localeProvider = getLocaleProvider();

        Node documentNode = null;
        DocumentTranslationProvider docTranslationProvider = null;
        try {
            documentNode = getDocumentNode();
            docTranslationProvider = new DocumentTranslationProvider(new JcrNodeModel(documentNode),
                    localeProvider);
        } catch (RepositoryException e) {
            log.warn("Unable to find document node");
        }
        translationProvider = docTranslationProvider;

        // lazily determine whether the document can be translated
        canTranslateModel = new LoadableDetachableModel<Boolean>() {
            @Override
            protected Boolean load() {
                WorkflowDescriptor descriptor = getModelObject();
                if (descriptor != null) {
                    try {
                        Map<String, Serializable> hints = descriptor.hints();
                        if (hints.containsKey("addTranslation") && hints.get("addTranslation").equals(Boolean.FALSE)) {
                            return false;
                        }

                    } catch (RepositoryException e) {
                        log.error("Failed to analyze hints for translations workflow", e);
                    }
                }
                return true;
            }
        };

        if (documentNode == null) {
            return;
        }

        try {
            if (!TranslationUtil.isNtTranslated(documentNode.getParent().getParent()) &&
                    (!TranslationUtil.isNtTranslated(documentNode) || !localeProvider.isKnown(languageModel.getObject()))) {
                return;
            }
        } catch (RepositoryException e) {
            log.warn("Could not determine translations status of document", e);
        }

        add(new EmptyPanel("content"));

        add(new MenuDescription() {
            @Override
            public Component getLabel() {
                Fragment fragment = new Fragment(ID_LABEL, ID_LABEL, TranslationWorkflowPlugin.this);
                HippoLocale locale = localeProvider.getLocale(languageModel.getObject());
                ResourceReference resourceRef = locale.getIcon(IconSize.M, LocaleState.EXISTS);
                fragment.add(new CachingImage("img", resourceRef));
                fragment.add(new Label(ID_CURRENT_LANGUAGE, locale.getDisplayName(getLocale())));
                return fragment;
            }

            @Override
            public MarkupContainer getContent() {
                Fragment fragment = new Fragment(ID_CONTENT, ID_LANGUAGES, TranslationWorkflowPlugin.this);
                fragment.add(new SendForTranslationAction(TranslationWorkflowPlugin.this, ID_LANGUAGE));
                fragment.add(new TranslationLocaleMenuDataView(ID_LANGUAGES, TranslationWorkflowPlugin.this, languageModel, new MenuLocaleProvider(TranslationWorkflowPlugin.this)));
                TranslationWorkflowPlugin.this.addOrReplace(fragment);
                return fragment;
            }
        });

    }

    public boolean isChangePending() {
        try {
            JcrDocument jcrDocument = new JcrDocument(getDocumentNode());
            Node unpublishedVariant = jcrDocument.getVariantNode(JcrDocument.VARIANT_UNPUBLISHED);
            if (unpublishedVariant.hasProperty(HippoStdNodeType.HIPPOSTD_STATESUMMARY) &&
                    "changed".equals(unpublishedVariant.getProperty(HippoStdNodeType.HIPPOSTD_STATESUMMARY).getString())) {
                return true;
            }
        } catch(RepositoryException ex) {
            // Just consume the exception
            log.warn("Failed to lookup unpublished status", ex);
        }
        return false;
    }

    // Gets the locale String of the document currently selected in the editor
    public String getCurrentlySelectedDocumentLocale() {
        LanguageModel languageModel = new LanguageModel(this);
        return getLocaleProvider().getLocale(languageModel.getObject()).getName();
    }

    public final Component getActionIcon(final String id, IModel<HippoLocale> localeModel) {
        final HippoLocale hippoLocale = localeModel.getObject();
        final HippoIconStack nodeIcon = new HippoIconStack(id, IconSize.M);

        final ResourceReference flagIcon = hippoLocale.getIcon(IconSize.M, LocaleState.EXISTS);
        nodeIcon.addFromResource(flagIcon);

        return nodeIcon;
    }

    public Boolean canTranslateModel() {
        return canTranslateModel.getObject();
    }

    @Override
    public IPluginContext getPluginContext() {
        return super.getPluginContext();
    }

    @Override
    public IPluginConfig getPluginConfig() {
        return super.getPluginConfig();
    }

    public boolean hasLocaleTranslation(String locale) {
        return translationProvider != null && translationProvider.contains(locale);
    }

    public boolean currentDocumentHasTranslation() {
        // Will return true if the currently selected document has at least one translation (excluding English)
        Set<String> availableLanguages = getAvailableLanguages();
        boolean hasTranslation = false;
        try {
            HippoTranslatedNode translatedNode = translatedNodeFactory.createFromNode(getDocumentNode());
            for (String language : availableLanguages) {
                if ("en".equals(language)) {
                    continue;
                } else if (translatedNode.hasTranslation(language)) {
                    hasTranslation = true;
                    break;
                }
            }
        } catch(RepositoryException ex) {
            // If we get a repository exception creating the hippo node just return false
            log.warn("Unable to create HippoTranslatedNode", ex);
        }
        return hasTranslation;
    }

    public Set<String> getAvailableLanguages() {
        WorkflowDescriptorModel wdm = (WorkflowDescriptorModel) TranslationWorkflowPlugin.this.getDefaultModel();
        if (wdm != null) {
            WorkflowDescriptor descriptor = wdm.getObject();
            WorkflowManager manager = UserSession.get().getWorkflowManager();
            try {
                TranslationWorkflow translationWorkflow = (TranslationWorkflow) manager.getWorkflow(descriptor);
                return (Set<String>) translationWorkflow.hints().get("available");
            } catch (RepositoryException | RemoteException | WorkflowException ex) {
                log.error("Failed to retrieve available languages", ex);
            }
        }
        return Collections.emptySet();
    }

    public List<HippoLocale> getAvailableLocales() {
        final ILocaleProvider localeProvider = getLocaleProvider();
        List<HippoLocale> localeList = getAvailableLanguages().stream()
                .map(name -> localeProvider.getLocale(name)).collect(Collectors.toList());
        return localeList;
    }

    private Node getDocumentNode() throws RepositoryException {
        WorkflowDescriptorModel wdm = (WorkflowDescriptorModel) getDefaultModel();
        if (wdm != null) {
            return wdm.getNode();
        }
        return null;
    }

    @Override
    public WorkflowDescriptor getModelObject() {
        WorkflowDescriptorModel wdm = (WorkflowDescriptorModel) getDefaultModel();
        if (wdm != null) {
            return wdm.getObject();
        }
        return null;
    }

    public Node getSourceDocumentNode() throws RepositoryException {
        return ((WorkflowDescriptorModel) getDefaultModel()).getNode();
    }

    public ILocaleProvider getLocaleProvider() {
        return getPluginContext().getService(
                getPluginConfig().getString(ILocaleProvider.SERVICE_ID, ILocaleProvider.class.getName()),
                ILocaleProvider.class);
    }

    public IBrowseService<IModel<Node>> getBrowserService() {
        final String serviceId = getPluginConfig().getString(IBrowseService.BROWSER_ID, "service.browse");
        return getPluginContext().getService(serviceId, IBrowseService.class);
    }

    @Override
    protected void onDetach() {
        if (translationProvider != null) {
            translationProvider.detach();
        }
        this.canTranslateModel.detach();
        super.onDetach();
    }
}
