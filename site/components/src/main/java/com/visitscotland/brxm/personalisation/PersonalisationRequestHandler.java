package com.visitscotland.brxm.personalisation;

import org.hippoecm.hst.core.request.HstRequestContext;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

final class PersonalisationRequestHandler {
    private PersonalisationRequestHandler() { }

    static void processRequest(final HttpServletRequest httpServletRequest,
                               final HstRequestContext requestContext) {
        final Optional<String> visitorCountry = extractCountryFromRequest(httpServletRequest)
            .filter(PersonalisationRequestHandler::isValidCountry);

        if (visitorCountry.isPresent() && visitorContextDoesNotExist(requestContext)) {
            final VisitorContext visitorContext = new VisitorContext(visitorCountry.get());

            requestContext.setAttribute("visitorContext", visitorContext);
        }
    }

    private static Optional<String> extractCountryFromRequest(final HttpServletRequest servletRequest) {
        final String countryHeader = servletRequest.getHeader("Visitor-Country");
        return Optional.ofNullable(countryHeader);
    }

    private static boolean visitorContextDoesNotExist(final HstRequestContext requestContext) {
        final VisitorContext visitorContext = (VisitorContext) requestContext.getAttribute("visitorContext");
        return Optional.ofNullable(visitorContext).isEmpty();
    }

    private static boolean isValidCountry(String country) {
        final Validator<String> validator = new CountryValidator();
        return validator.isValid(country);
    }
}