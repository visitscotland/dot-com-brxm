package com.visitscotland.brxm.comparator;

import com.visitscotland.brxm.utils.VsException;
import org.hippoecm.hst.configuration.hosting.Mount;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryManager;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.FilterException;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.query.filter.Filter;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.util.PathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

@Component
public class HippoQueryService {

    private static final Logger logger = LoggerFactory.getLogger(HippoQueryService.class);

    private static final String HIPPO_STATE = "hippostd:state";
    private static final String HIPPO_STATE_SUMMARY = "hippostd:stateSummary";
    private static final String PUBLISHED = "published";
    private static final String LIVE = "live";

    HippoBeanIterator findAll(Class<? extends HippoBean> type) throws RepositoryException, QueryException, VsContractException {
        if (type == null) {
            throw new UnexpectedNullException("type");
        }
        HstQueryManager hstQueryManager = getHstQueryManager();
        @SuppressWarnings("unchecked")
        HstQuery hstQuery = hstQueryManager.createQuery(getProjectMountPoint(), type);
        filterOnlyLiveDocuments(hstQuery);
        var result = hstQuery.execute();
        HippoBeanIterator iterator = result.getHippoBeans();

        logger.debug("Find all {} returned {} rows", type.getName(), iterator.getSize());
        return iterator;
    }

    public HstQueryManager getHstQueryManager() throws RepositoryException {
        var requestContext = RequestContextProvider.get();
        return requestContext.getQueryManager(requestContext.getSession());
    }

    private Node getProjectMountPoint() throws RepositoryException {
        String mountContentPath = RequestContextProvider.get().getResolvedMount().getMount().getContentPath();
        return RequestContextProvider.get().getSession().getRootNode()
                .getNode(PathUtils.normalizePath(mountContentPath));
    }

    private void filterOnlyLiveDocuments(HstQuery hstQuery) throws FilterException {
        Filter filter = hstQuery.createFilter();
        // Only documents of published type (green)
        filter.addEqualTo(HIPPO_STATE, PUBLISHED);
        // Only documents that are actually live and (opposing to those taken offline)
        filter.addEqualTo(HIPPO_STATE_SUMMARY, LIVE);
        hstQuery.setFilter(filter);
    }
}
