package com.visitscotland.brxm.mock;

import com.visitscotland.brxm.hippobeans.TravelInformation;
import com.visitscotland.brxm.hippobeans.TravelInformationTab;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class TravelInformationMockBuilder {

    private final TravelInformation mock;

    public TravelInformationMockBuilder() {
        mock = Mockito.mock(TravelInformation.class);
        when(mock.getPracticalInformation()).thenReturn(new ArrayList<>());
    }

    public TravelInformation build() {
        return mock;
    }

    public TravelInformationMockBuilder copy(String copy) {
        HippoHtml copyHtml = Mockito.mock(HippoHtml.class);
        when(copyHtml.getContent()).thenReturn(copy);
        when(mock.getCopy()).thenReturn(copyHtml);
        return this;
    }

    public TravelInformationMockBuilder title(String title) {
        when(mock.getTitle()).thenReturn(title);
        return this;
    }

    public TravelInformationMockBuilder practicalInformation(
            TravelInformationTab... tabs) {

        when(mock.getPracticalInformation())
                .thenReturn(Arrays.asList(tabs));

        return this;
    }
}
