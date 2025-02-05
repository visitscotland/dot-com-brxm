package com.visitscotland.brxm.services.search;

import com.visitscotland.brxm.hippobeans.BaseDocument;
import com.visitscotland.brxm.hippobeans.EventBSH;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.hippoecm.hst.component.support.bean.BaseHstComponent;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.builder.HstQueryBuilder;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.util.SearchInputParsingUtils;
import org.springframework.stereotype.Component;

import javax.jcr.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class HstQueryService {

//    public void query(Class<? extends  HippoBean> documentType) {
////        final Session session = UserSession.get().getJcrSession();
//
//        HttpServletRequest request = RequestContextProvider.get().getServletRequest();
//        query(request, BaseDocument.class);
//    }

    public HstQueryService() {
    }

    public <T extends HippoBean> HstQueryResult  query(Class<T> documentType) {

        try {
            final Node node = RequestContextProvider.get().getSession().getNode("/content/documents/bsh/sandbox/events");

            @SuppressWarnings("unchecked")
            HstQuery hstQuery = HstQueryBuilder.create(node)
                    .ofTypes(documentType)
                    .build();

            // execute the query
            return hstQuery.execute();

        } catch (QueryException | RepositoryException e) {
            throw new HstComponentException (
                    "Exception occurred during creation or execution of HstQuery.", e);
        }
    }

    public HstQueryResult query(HstRequest request, Class<? extends  HippoBean> documentType) {
//        HstRequestContext requestContext = request.getRequestContext();
        // the scope to search below, for example /content/documents/myproject
//        HstRequestContext requestContext = RequestContextProvider.get().getContentBean();
        HippoBean scope = RequestContextProvider.get().getContentBean();

        try {
            // parse a free text query to remove invalid chars. The argument
            // 'false' means no wildcards allowed
//            String query = getPublicRequestParameter(request, "q");
//            String parsedQuery = SearchInputParsingUtils.parse(query, false);

//            int pageSize = NumberUtils.toInt(getPublicRequestParameter(request, "ps"), 10);
//            int pageNum = NumberUtils.toInt(getPublicRequestParameter(request, "pn"), 1);

            // create the query to search below 'scope', return beans that are
            // of type BaseDocument bean or a subclass/sub-jcr-types, the
            // third argument, 'true', indicates whether to include subtypes
            HstQuery hstQuery = HstQueryBuilder.create(scope)
                    .ofTypes(documentType)
//                    .where(constraint(".").contains(parsedQuery))
//                    .limit(pageSize)
//                    .offset(pageSize * (pageNum - 1))
//                    .orderByDescending("mynamespace:date")
                    .build();

            // execute the query
            HstQueryResult result = hstQuery.execute();

            // set the result, info and parsedQuery on the HstRequest : It is
            // then available in the JSP
            request.setAttribute("result", result);
//            request.setAttribute("query", parsedQuery);

            return result;

        } catch (QueryException e) {
            throw new HstComponentException (
                    "Exception occured during creation or execution of HstQuery.", e);
        }
    }

//    public String getPublicRequestParameter(HstRequest request, String parameterName) {
//        Map<String, String[]> namespaceLessParameters = request.getParameterMap("");
//        String[] paramValues = (String[])namespaceLessParameters.get(parameterName);
//        return paramValues != null && paramValues.length > 0 ? paramValues[0] : null;
//    }
//
//    public String[] getPublicRequestParameters(HstRequest request, String parameterName) {
//        Map<String, String[]> namespaceLessParameters = request.getParameterMap("");
//        String[] paramValues = (String[])namespaceLessParameters.get(parameterName);
//        return paramValues != null && paramValues.length > 0 ? paramValues : ArrayUtils.EMPTY_STRING_ARRAY;
//    }
}
