package com.visitscotland.brxm.personalisation;

import org.hippoecm.hst.core.container.ValveContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

final class PersonalisationHandler {
    // Prevent unwanted instantiation
    private PersonalisationHandler() { }

    static void processValveContext(ValveContext valveContext) {
        final HttpServletRequest request = valveContext.getServletRequest();

        getCountryFromRequest(request)
            .ifPresent(visitorCountry -> {
                final VisitorContext visitorContext = createVisitorContext(visitorCountry);
                appendVisitorContextToRequestContext(visitorContext, valveContext);
            });
    }

    private static VisitorContext createVisitorContext(String country) {
        return new VisitorContext.Builder()
            .withCountry(country)
            .build();
    }

    private static Optional<String> getCountryFromRequest(final HttpServletRequest request) {
        final String visitorCountry = request.getHeader("Visitor-Country");
        return Optional.ofNullable(visitorCountry);
    }

    private static void appendVisitorContextToRequestContext(VisitorContext visitorContext,
                                                             ValveContext valveContext) {
        valveContext
            .getRequestContext()
            .setAttribute("visitorContext", visitorContext);
    }
}