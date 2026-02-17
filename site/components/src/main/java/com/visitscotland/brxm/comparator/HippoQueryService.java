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

/**
 * Service for executing queries against the Hippo CMS repository.
 * Provides methods to find and filter content beans with proper state filtering.
 */
@Component
public class HippoQueryService {

    private static final Logger logger = LoggerFactory.getLogger(HippoQueryService.class);

    private static final String HIPPO_STATE = "hippostd:state";
    private static final String HIPPO_STATE_SUMMARY = "hippostd:stateSummary";
    private static final String PUBLISHED = "published";
    private static final String LIVE = "live";

    /**
     * Finds all published documents of the specified type.
     *
     * <i>Note: that this method is not looking for a specific path</i>
     *
     * @param type The HippoBean class type to search for
     * @return Iterator containing all matching documents
     * @throws RepositoryException If repository access fails
     * @throws QueryException If query execution fails
     * @throws VsContractException If type parameter is null
     */
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

    /**
     * Gets the HST query manager for the current request context.
     *
     * @return The HstQueryManager instance
     * @throws RepositoryException If unable to access the repository session
     */
    private HstQueryManager getHstQueryManager() throws RepositoryException {
        var requestContext = RequestContextProvider.get();
        return requestContext.getQueryManager(requestContext.getSession());
    }

    /**
     * Gets the project mount point node for the current channel (site)
     *
     * @return The root node for the current mount's content path
     * @throws RepositoryException If unable to access the mount point
     */
    private Node getProjectMountPoint() throws RepositoryException {
        String mountContentPath = RequestContextProvider.get().getResolvedMount().getMount().getContentPath();
        return RequestContextProvider.get().getSession().getRootNode()
                .getNode(PathUtils.normalizePath(mountContentPath));
    }

    /**
     * Applies filters to only return published documents.
     *
     * @param hstQuery The query to apply filters to
     * @throws FilterException If unable to apply the filters
     */
    private void filterOnlyLiveDocuments(HstQuery hstQuery) throws FilterException {
        Filter filter = hstQuery.createFilter();
        // Only documents of published type (green)
        filter.addEqualTo(HIPPO_STATE, PUBLISHED);
        // Only documents that are actually live and (opposing to those taken offline)
        filter.addEqualTo(HIPPO_STATE_SUMMARY, LIVE);
        hstQuery.setFilter(filter);
    }
}
