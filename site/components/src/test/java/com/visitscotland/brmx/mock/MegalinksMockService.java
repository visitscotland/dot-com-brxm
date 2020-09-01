package com.visitscotland.brmx.mock;

import com.visitscotland.brmx.beans.*;
import com.visitscotland.brmx.components.content.factory.LinkModuleFactoryMockitoTest;
import com.visitscotland.brmx.dms.ProductSearchBuilder;

import java.util.Collections;

import static org.mockito.Mockito.*;

public class MegalinksMockService {

    ProductSearchBuilder builder;

    public MegalinksMockService(ProductSearchBuilder builder){
        this.builder = builder;
    }

    public Megalinks mockMultiImage() {
        return mockMultiImage(null);
    }

    public Megalinks mockMultiImage(String title) {
        Megalinks mega = mock(Megalinks.class, withSettings().lenient());
        MegalinkItem item = mockItem();

        when(mega.getTitle()).thenReturn(title);
        when(mega.getMegalinkItems()).thenReturn(Collections.singletonList(item));

        return mega;
    }

    public MegalinkItem mockItem() {
        return mockItem(false, LinkModuleFactoryMockitoTest.LinkType.CMS);
    }

    public MegalinkItem mockItem(boolean featured, LinkModuleFactoryMockitoTest.LinkType type) {
        MegalinkItem item = mock(MegalinkItem.class, withSettings().lenient());

        when(item.getFeature()).thenReturn(featured);
        if (type == LinkModuleFactoryMockitoTest.LinkType.CMS) {
            when(item.getLink()).thenReturn(mockPage());
        } else {
            SharedLink link = mockSharedLink(type);
            when(item.getLink()).thenReturn(link);
        }

        return item;
    }


    private SharedLink mockSharedLink(LinkModuleFactoryMockitoTest.LinkType linkType) {
        SharedLink link = mock(SharedLink.class, withSettings().lenient());
        when(link.getImage()).thenReturn(mock(Image.class, withSettings().lenient()));

        switch (linkType) {
            case DMS:
                DMSLink type = mock(DMSLink.class, withSettings().lenient());
                when(type.getProduct()).thenReturn(LinkModuleFactoryMockitoTest.DMS_ID);
                when(link.getLinkType()).thenReturn(type);
                break;
            case PRODUCT_SEARCH:
                ProductsSearch ps = mock(ProductsSearch.class, withSettings().lenient());
                when(builder.fromHippoBean(ps)).thenReturn(builder);
                when(builder.build()).thenReturn(LinkModuleFactoryMockitoTest.PSR_URL);
                when(link.getLinkType()).thenReturn(ps);
                break;
            case EXTERNAL:
                ExternalLink external = mock(ExternalLink.class, withSettings().lenient());
                when(external.getLink()).thenReturn(LinkModuleFactoryMockitoTest.EXTERNAL_URL);
                when(link.getLinkType()).thenReturn(external);
                break;
            default:

        }
        return link;
    }

    private Page mockPage() {
        return mock(Page.class);
    }
}
