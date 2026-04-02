package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.hippobeans.*;
import com.visitscotland.brxm.model.FormModule;
import com.visitscotland.brxm.model.SimpleEntry;
import com.visitscotland.brxm.model.form.BregConfiguration;
import com.visitscotland.brxm.model.form.CRMConfiguration;
import com.visitscotland.brxm.model.form.FeplConfiguration;
import com.visitscotland.brxm.model.form.MarketoConfiguration;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.brxm.utils.SiteProperties;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoCompound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

@Component
public class FormMapper extends ModuleMapper<Form, FormModule> {

    private static final Logger log = LoggerFactory.getLogger(FormMapper.class);
    private final SiteProperties properties;


    private final ContentLogger contentLogger;

    public FormMapper(SiteProperties properties, ContentLogger contentLogger) {
        this.properties = properties;
        this.contentLogger = contentLogger;
    }

    @Deprecated
    public FormModule getModule(MarketoForm document) {
        MarketoConfiguration cfg = getMarketoConfiguration(document);

        FormModule module = new FormModule();
        module.setConfig(cfg);
        module.setTitle(document.getTitle());
        module.setJsonUrl(document.getJsonUrl());
        module.setNoJavaScriptMessage(document.getNonJavaScriptMessage());
        module.setCopy(document.getCopy());

        return module;
    }

    private MarketoConfiguration getMarketoConfiguration(HippoBean bean) {
        MarketoConfiguration cfg = new MarketoConfiguration();
        cfg.setRecaptcha(properties.getFormsRecaptcha());
        cfg.setMarketoInstance(properties.getFormsMarketoUrl());
        cfg.setMunchkinId(properties.getFormsMarketoMunchkin());
        cfg.setScript(properties.getFormsMarketoScript());
        /** TODO: This property should go away
         * @see MarketoConfiguration.production
         */
        cfg.setProduction(false);

        if (bean instanceof FormCompoundMarketo) {
            cfg.setJsonUrl(((FormCompoundMarketo) bean).getJsonUrl());
        } else if (bean instanceof MarketoForm){
            cfg.setJsonUrl(((MarketoForm) bean).getJsonUrl());
        }

        return cfg;
    }

    private FeplConfiguration getFeplConfiguration(FormCompoundFepl fepl){
        FeplConfiguration cfg = new FeplConfiguration();
        cfg.setRecaptcha(properties.getFormsRecaptcha());
        cfg.setSubmitUrl(fepl.getUrl());
        cfg.setJsonUrl(fepl.getJsonURL());

        return cfg;
    }

    private BregConfiguration getBregConfiguration(FormCompoundBreg breg){
        BregConfiguration cfg = new BregConfiguration();
        cfg.setSubmitUrl(breg.getUrl());
        cfg.setJsonUrl(breg.getJsonUrl());
        cfg.setActivityCode(breg.getActivityCode());
        cfg.setActivityDescription(breg.getActivityDescription());
        cfg.setActivitySource(breg.getActivitySource());

        if (!breg.getUrl().contains("no-captcha")){
            cfg.setRecaptcha(properties.getFormsRecaptcha());
        }

        List<Entry> consents = breg.getConsents();
        String consentComposition = "";
        List<SimpleEntry> entries = new ArrayList<>();
        for (Entry cons : consents) {
            String consValue = cons.getValue();
            if (consValue.contains(",") || consValue.contains(";")){
                //TODO: This is a workaround that must be fixed on BREG (VS-1237)
                log.error("The consent message has been altered because it contains not allowed characters '{}'",
                        consValue);
                consValue = consValue.replace(",", " –").replace(";", " –");
            }

            if (Contract.isEmpty(consentComposition)){
                consentComposition = cons.getKey() + "," + consValue;
            } else {
                consentComposition = consentComposition + ";" + cons.getKey() + "," + consValue;
            }

            entries.add(new SimpleEntry(cons));
        }

        cfg.setConsents(consentComposition);
        cfg.setConsentList(entries);

        if (properties.isFormBregLegalBasisEnabled()) {
            cfg.setLegalBasis(properties.getFormBregLegalBasisText());
        }

        return cfg;
    }

    private CRMConfiguration getCRMConfiguration(FormCompoundCRM crm){
        CRMConfiguration cfg = new CRMConfiguration();
        cfg.setRecaptcha(properties.getFormsRecaptcha());
        cfg.setSubmitUrl(crm.getUrl());
        cfg.setJsonUrl(crm.getJsonURL());

        return cfg;
    }

    public FormModule getModule(Form document) {
        HippoCompound cfg = document.getFormConfiguration();

        FormModule module = new FormModule();
        module.setTitle(document.getTitle());
        module.setNoJavaScriptMessage(document.getNonJavaScriptMessage());
        module.setCopy(document.getCopy());
        module.setHippoBean(document);

        if (cfg instanceof FormCompoundFepl) {
            module.setConfig(getFeplConfiguration((FormCompoundFepl) document.getFormConfiguration()));
        } else if (cfg instanceof FormCompoundMarketo) {
            module.setConfig(getMarketoConfiguration(document.getFormConfiguration()));
        } else if (cfg instanceof FormCompoundBreg) {
            module.setConfig(getBregConfiguration((FormCompoundBreg) document.getFormConfiguration()));
        } else if (cfg instanceof FormCompoundCRM) {
            module.setConfig(getCRMConfiguration((FormCompoundCRM) document.getFormConfiguration()));
        } else {
            contentLogger.warn("The Form document '{}' does not have a valid configuration. It won't appear on the page", document.getPath());
            return null;
        }

        return module;
    }

    @Override
    void addLabels(PageCompositionHelper compositionHelper) throws MissingResourceException {
        compositionHelper.addAllSiteLabels("forms");

        compositionHelper.addProperty("form.json.countries",
                properties.getProperty("form.json.countries").orElse(""));
        compositionHelper.addProperty("form.json.messages",
                properties.getProperty("form.json.messages").orElse(""));
    }

    @Override
    FormModule map(Form document, PageCompositionHelper compositionHelper) throws PageCompositionException {
        return getModule(document);
    }
}
