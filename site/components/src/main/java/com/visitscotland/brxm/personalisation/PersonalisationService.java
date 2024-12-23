package com.visitscotland.brxm.personalisation;

import com.visitscotland.brxm.hippobeans.Personalization;

import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.request.HstRequestContext;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.Optional;
import java.util.Objects;
import java.util.List;

import static com.visitscotland.brxm.hippobeans.Personalization.PERSONALIZATION_JCR_TYPE;

@Component
public class PersonalisationService {
    public List<HippoBean> getPersonalisedVariants(HippoBean hippoBean) {
        final List<Personalization> personalisationCompounds = hippoBean
            .getChildBeans(PERSONALIZATION_JCR_TYPE);

        return personalisationCompounds
            .stream()
            .filter(this::matchesVisitorContextCountry)
            .map(Personalization::getModule)
            .filter(Objects::nonNull)
            .collect(Collectors.toUnmodifiableList());
    }

    private boolean matchesVisitorContextCountry(Personalization personalization) {
        return getVisitorContextFromRequest()
            .filter(visitorContext -> personalization.getCountry().equals(visitorContext.getCountry()))
            .isPresent();
    }

    private Optional<VisitorContext> getVisitorContextFromRequest() {
        final HstRequestContext requestContext = RequestContextProvider.get();
        final VisitorContext visitorContext = (VisitorContext) requestContext.getAttribute("visitorContext");

        return Optional.ofNullable(visitorContext);
    }
}