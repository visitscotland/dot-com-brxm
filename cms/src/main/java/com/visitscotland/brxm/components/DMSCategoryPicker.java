package com.visitscotland.brxm.components;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.json.JSONArray;
import org.json.JSONObject;
import org.onehippo.forge.exdocpicker.api.ExternalDocumentCollection;
import org.onehippo.forge.exdocpicker.api.ExternalDocumentServiceContext;
import org.onehippo.forge.exdocpicker.impl.SimpleExternalDocumentCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import java.util.*;

@Deprecated
public class DMSCategoryPicker extends AbstractDMSPicker{

    /**
     * Plugin parameter name for physical document field name (JCR property name).
     */
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(DMSCategoryPicker.class);

    private static final String PRODUCT_TYPE = "dms.productype";

    private static final String TYPE = "category";

    public DMSCategoryPicker() {
        super (TYPE);
    }

    @Override
    public ExternalDocumentCollection<DocumentObject> searchExternalDocuments(ExternalDocumentServiceContext context, String queryString) {
        final String fieldName = context.getPluginConfig().getString(PRODUCT_TYPE);
        try {
            List<String> productTypes = Arrays.asList(context.getContextModel().getNode().getProperty(fieldName).getValue().getString().split("\\s*,\\s*"));
            if (!productTypes.get(0).isEmpty()) {
                JSONArray subCategory = new JSONArray();

                subCategory.putAll(deserialize(request(TYPE,null, productTypes)));

                setDocArray(subCategory);
            }
        } catch (RepositoryException e) {
            logger.error("Error while getting DMS categories", e);
        }

        ExternalDocumentCollection<DocumentObject> docCollection =new SimpleExternalDocumentCollection<>();
        for (int i = 0; i < getDocArray().length(); i++) {
            JSONObject doc = getDocArray().getJSONObject(i);
            if (doc.getString("name").toLowerCase().contains(Objects.toString(queryString, "").toLowerCase())) {
                docCollection.add((DocumentObject) doc);
            }
        }

        return docCollection;
    }

}