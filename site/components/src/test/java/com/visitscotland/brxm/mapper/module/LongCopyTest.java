package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.hippobeans.LongCopy;
import com.visitscotland.brxm.model.LongCopyModule;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LongCopyTest {

    @InjectMocks
    LongCopyMapper mapper;

    @Test
    @DisplayName("LongCopy - Create a LongCopy module")
    void createLongCopy(){
        LongCopy document = mock(LongCopy.class);
        HippoHtml html = mock(HippoHtml.class);
        when(document.getCopy()).thenReturn(html);

        LongCopyModule module = mapper.getModule(document);

        Assertions.assertEquals(html, module.getCopy());
    }
}
