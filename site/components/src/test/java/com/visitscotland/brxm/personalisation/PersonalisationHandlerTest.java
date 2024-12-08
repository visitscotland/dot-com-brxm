package com.visitscotland.brxm.personalisation;

import org.hippoecm.hst.mock.core.component.MockValveContext;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.core.component.HstRequest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonalisationHandlerTest {
    @Mock private HstRequest hstRequest;
    @InjectMocks private MockValveContext valveContext;

    private final ArgumentCaptor<VisitorContext> visitorContextCaptor;

    private static final String VISITOR_COUNTRY_HEADER = "Visitor-Country";

    public PersonalisationHandlerTest() {
        this.visitorContextCaptor = ArgumentCaptor.forClass(VisitorContext.class);
    }

    @Test
    void When_ProcessValveContext_With_NoVisitorCountryPresent_Expect_RequestContextSetAttributeNotCalled() {
        final HstRequestContext hstRequestContext = mock(HstRequestContext.class);

        when(hstRequest.getHeader(VISITOR_COUNTRY_HEADER)).thenReturn(null);
        when(hstRequest.getRequestContext()).thenReturn(hstRequestContext);

        PersonalisationHandler.processValveContext(valveContext);

        verify(valveContext.getRequestContext(), times(0)).setAttribute(anyString(), any(VisitorContext.class));
    }

    @Test
    void When_ProcessValveContext_With_ValidVisitorCountry_Expect_VisitorContextAddedToRequestContext() {
        final String franceCountryCode = "FR";
        final HstRequestContext hstRequestContext = mock(HstRequestContext.class);

        when(hstRequest.getHeader(VISITOR_COUNTRY_HEADER)).thenReturn(franceCountryCode);
        when(hstRequest.getRequestContext()).thenReturn(hstRequestContext);
        doNothing().when(hstRequestContext).setAttribute(anyString(), any(VisitorContext.class));

        PersonalisationHandler.processValveContext(valveContext);

        verify(hstRequestContext, times(1))
            .setAttribute(anyString(), visitorContextCaptor.capture());

        final VisitorContext visitorContext = visitorContextCaptor.getValue();
        Assertions.assertEquals(franceCountryCode, visitorContext.getCountry());
    }
}