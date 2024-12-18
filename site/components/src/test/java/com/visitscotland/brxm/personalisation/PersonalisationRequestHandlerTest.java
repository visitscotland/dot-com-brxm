package com.visitscotland.brxm.personalisation;

import org.hippoecm.hst.mock.core.request.MockHstRequestContext;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonalisationRequestHandlerTest {
    @Mock private MockHstRequestContext requestContext;
    @Mock private HttpServletRequest servletRequest;

    private final ArgumentCaptor<VisitorContext> captor;

    private static final String VISITOR_CONTEXT_ATTRIBUTE = "visitorContext";
    private static final String COUNTRY_HEADER_NAME = "Visitor-Country";
    private static final String UNITED_KINGDOM = "GB";
    private static final String UNITED_STATES = "US";

    protected PersonalisationRequestHandlerTest() {
        this.captor = ArgumentCaptor.forClass(VisitorContext.class);
    }

    @Test
    void When_ProcessRequest_Given_ValidCountryAndVisitorContextDoesNotExist_Expect_VisitorContextIsSet() {
        when(servletRequest.getHeader(COUNTRY_HEADER_NAME)).thenReturn(UNITED_KINGDOM);
        when(requestContext.getAttribute(VISITOR_CONTEXT_ATTRIBUTE)).thenReturn(null);

        PersonalisationRequestHandler.processRequest(servletRequest, requestContext);

        verify(requestContext, times(1))
            .setAttribute(eq(VISITOR_CONTEXT_ATTRIBUTE), captor.capture());

        final VisitorContext visitorContext = captor.getValue();
        Assertions.assertEquals(UNITED_KINGDOM, visitorContext.getCountry());
    }

    @Test
    void When_ProcessRequest_Given_ValidCountryAndVisitorContextExists_Expect_NoVisitorContextIsSet() {
        when(servletRequest.getHeader(COUNTRY_HEADER_NAME)).thenReturn(UNITED_KINGDOM);
        when(requestContext.getAttribute(VISITOR_CONTEXT_ATTRIBUTE)).thenReturn(new VisitorContext(UNITED_STATES));

        PersonalisationRequestHandler.processRequest(servletRequest, requestContext);

        verify(requestContext, never()).setAttribute(eq(VISITOR_CONTEXT_ATTRIBUTE), any(VisitorContext.class));
    }

    @Test
    void When_ProcessRequest_Given_InvalidCountry_Expect_NoVisitorContextIsSet() {
        when(servletRequest.getHeader(COUNTRY_HEADER_NAME)).thenReturn("INVALID");

        PersonalisationRequestHandler.processRequest(servletRequest, requestContext);

        verify(requestContext, never()).setAttribute(eq(VISITOR_CONTEXT_ATTRIBUTE), any(VisitorContext.class));
    }

    @Test
    void When_ProcessRequest_Given_CountryHeaderIsAbsent_Expect_NoVisitorContextIsSet() {
        when(servletRequest.getHeader(COUNTRY_HEADER_NAME)).thenReturn(null);

        PersonalisationRequestHandler.processRequest(servletRequest, requestContext);

        verify(requestContext, never()).setAttribute(eq(VISITOR_CONTEXT_ATTRIBUTE), any(VisitorContext.class));
    }
}