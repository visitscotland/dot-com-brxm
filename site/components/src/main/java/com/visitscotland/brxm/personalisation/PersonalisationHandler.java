package com.visitscotland.brxm.personalisation;

import org.hippoecm.hst.core.container.ValveContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

final class PersonalisationHandler {
    private PersonalisationHandler() { }

    static void processValveContext(ValveContext valveContext) {
        final HttpServletRequest request = valveContext.getServletRequest();
        final VisitorContext visitorContext = new VisitorContext.Builder()
            .withCountry(getCountryFromRequest(request))
            .build();

        appendVisitorContextToRequestContext(visitorContext, valveContext);
    }

    private static void appendVisitorContextToRequestContext(VisitorContext visitorContext,
                                                             ValveContext valveContext) {
        valveContext
            .getRequestContext()
            .setAttribute("visitorContext", visitorContext);
    }

    private static String getCountryFromRequest(final HttpServletRequest request) {
        return Optional
            .ofNullable(request.getHeader("Visitor-Country"))
            .orElseThrow(CountryNotPresentException::new);
    }
}