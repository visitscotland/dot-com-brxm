package com.visitscotland.brxm.components;


import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.dms.DMSConstants;
import com.visitscotland.brxm.dms.DMSProxy;
import com.visitscotland.utils.Contract;
import org.json.JSONArray;
import org.json.JSONObject;
import org.hippoecm.repository.HippoStdNodeType;
import org.onehippo.forge.exdocpicker.api.ExternalDocumentCollection;
import org.onehippo.forge.exdocpicker.api.ExternalDocumentServiceContext;
import org.onehippo.forge.exdocpicker.api.ExternalDocumentServiceFacade;
import org.onehippo.forge.exdocpicker.impl.SimpleExternalDocumentCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import java.util.*;

//TODO Make this testable
public abstract class AbstractDMSPicker implements ExternalDocumentServiceFacade<DocumentObject> {

    private static Logger log = LoggerFactory.getLogger(AbstractDMSPicker.class);

    private static final String PARAM_EXTERNAL_DOCS_FIELD_NAME = "example.external.docs.field.name";

    private DMSProxy dmsProxy;

    private JSONArray docArray;

    public AbstractDMSPicker(String type) {
        try {
            dmsProxy = VsComponentManager.get(DMSProxy.class);
            docArray = new JSONArray();
            docArray.putAll(deserialize(
                    request(type,null, productTypesForPSR(type))));

        } catch (Exception e) {
            log.error("Failed to load JSON data.", e);
        }
    }

    @Override
    public void setFieldExternalDocuments(ExternalDocumentServiceContext context, ExternalDocumentCollection<DocumentObject> exdocs) {
        final String fieldName = context.getPluginConfig().getString(PARAM_EXTERNAL_DOCS_FIELD_NAME);

        if (Contract.isEmpty(fieldName)) {
            throw new IllegalArgumentException("Invalid plugin configuration parameter for '"
                    + PARAM_EXTERNAL_DOCS_FIELD_NAME + "': " + fieldName);
        }

        try {
            final Node contextNode = context.getContextModel().getNode();
            final List<String> docIds = new ArrayList<>();

            for (Iterator<? extends JSONObject> it = exdocs.iterator(); it.hasNext();) {
                JSONObject doc = it.next();
                docIds.add(doc.getString("id"));
            }

            if (!contextNode.isNodeType(HippoStdNodeType.NT_RELAXED)) {
                contextNode.addMixin(HippoStdNodeType.NT_RELAXED);
            }

            contextNode.setProperty(fieldName, docIds.toArray(new String[docIds.size()]));
        } catch (RepositoryException e) {
            log.error("Failed to set related exdoc array field.", e);
        }
    }

    public ExternalDocumentCollection<DocumentObject> getFieldExternalDocuments(ExternalDocumentServiceContext context) {
        final String fieldName = context.getPluginConfig().getString(PARAM_EXTERNAL_DOCS_FIELD_NAME);

        if (Contract.isEmpty(fieldName)) {
            throw new IllegalArgumentException("Invalid plugin configuration parameter for '"
                    + PARAM_EXTERNAL_DOCS_FIELD_NAME + "': " + fieldName);
        }

        ExternalDocumentCollection<DocumentObject> docCollection = new SimpleExternalDocumentCollection<>();

        try {
            final Node contextNode = context.getContextModel().getNode();

            if (contextNode.hasProperty(fieldName)) {
                Value[] values = contextNode.getProperty(fieldName).getValues();

                for (Value value : values) {
                    String id = value.getString();
                    DocumentObject doc = findDocumentById(id);

                    if (doc != null) {
                        docCollection.add(doc);
                    }
                }
            }
        } catch (RepositoryException e) {
            log.error("Failed to retrieve related exdoc array field.", e);
        }

        return docCollection;
    }


    @Override
    public String getDocumentTitle(ExternalDocumentServiceContext context, DocumentObject doc, Locale preferredLocale) {
        if (doc != null && doc.has("id")) {
            return doc.getString("id");
        }

        return "";
    }
    @Override
    public String getDocumentDescription(ExternalDocumentServiceContext context, DocumentObject doc, Locale preferredLocale) {
        if (doc != null && doc.has("name")) {
            return doc.getString("name");
        }

        return "";
    }

    @Override
    public String getDocumentIconLink(ExternalDocumentServiceContext context, DocumentObject doc, Locale preferredLocale) {
        return "";
    }

    /**
     * Get the DMS metadata by the DMS id.
     *
     * @param id
     * @return
     */
    protected DocumentObject findDocumentById(final String id) {
        for (int i = 0; i < docArray.length(); i++) {
            JSONObject doc = docArray.getJSONObject(i);

            if (id != null && id.equals(doc.getString("id"))) {
                return (DocumentObject) doc;
            }
        }

        return null;
    }

    /**
     * Method to retrieve only the metadata (categories and facilities) for Product types used in the product search
     *
     * @return List<ProductTypes>
     */

    protected List<String> productTypesForPSR(String productType){
        List<String> searchTypes = new ArrayList<>();
        searchTypes.add(ProductTypes.ACCOMMODATION.getId());
        searchTypes.add(ProductTypes.ACTIVITY.getId());
        searchTypes.add(ProductTypes.ATTRACTION.getId());
        searchTypes.add(ProductTypes.EVENT.getId());
        searchTypes.add(ProductTypes.SHOPPING.getId());
        searchTypes.add(ProductTypes.FOOD_DRINK.getId());
        searchTypes.add(ProductTypes.TRANSPORT.getId());
        return searchTypes;
    }

    /**
     * Request the resource taking into account the language.
     *
     * @param locale: Specific locale for the fragment or null if the locale is English (default locale)
     *
     * @return HTML fragment according to the type and the locale
     */
    protected String request(String productType, Locale locale, List<String> productTypeParameterList) {
        String parameters ="";
        if (productTypeParameterList!=null){
            for (String productTypeParameter: productTypeParameterList) {
                if (parameters.isEmpty()) {
                    parameters = "prodtypes=" + productTypeParameter;
                } else {
                    parameters += "&prodtypes=" + productTypeParameter;
                }
            }
        }

        return dmsProxy.request(String.format(DMSConstants.META_PRODUCT_LIST,productType, parameters), locale);
    }

    protected JSONArray deserialize(String data) {
        JSONArray parsedArray = new JSONArray(data);
        JSONArray docArray = new JSONArray();

        for (int i = 0; i < parsedArray.length(); i++) {
            JSONObject jsonObject = parsedArray.getJSONObject(i);

            DocumentObject documentObject = new DocumentObject();
            for (String key : jsonObject.keySet()) {
                documentObject.put(key, jsonObject.get(key));
            }

            docArray.put(documentObject);
        }
        return docArray;
    }

    protected JSONArray getDocArray() {
        return docArray;
    }

    protected void setDocArray(JSONArray docArray) {
        this.docArray = docArray;
    }

}