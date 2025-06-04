package com.visitscotland.brxm.event;

import com.visitscotland.brxm.exception.EventsSearchException;
import org.hippoecm.hst.content.beans.query.exceptions.FilterException;
import org.hippoecm.hst.content.beans.query.filter.Filter;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.query.HstQuery;

import org.hippoecm.hst.core.parameters.ParametersInfo;
import org.hippoecm.hst.core.component.HstRequest;

import org.onehippo.cms7.essentials.components.info.EssentialsListComponentInfo;
import org.onehippo.cms7.essentials.components.EssentialsSearchComponent;

@ParametersInfo(type = EssentialsListComponentInfo.class)
public class EventSearchComponent extends EssentialsSearchComponent {
    @Override
    protected <T extends EssentialsListComponentInfo> HstQuery buildQuery(final HstRequest request,
                                                                          final T paramInfo,
                                                                          final HippoBean scope) {
        final HstQuery hstQuery = super.buildQuery(request, paramInfo, scope);
        final String keyword = this.getAnyParameter(request, "query");

        if (keyword != null && !keyword.trim().isEmpty()) {
            final Filter filter = hstQuery.createFilter();

            try {
                filter.addContains(".", keyword);
            } catch (final FilterException exception) {
                throw new EventsSearchException(exception);
            }

            hstQuery.setFilter(filter);
            return hstQuery;
        }

        return hstQuery;
    }
}