package com.visitscotland.brxm.mock;

import com.visitscotland.brxm.hippobeans.*;
import com.visitscotland.brxm.mapper.MegalinkMapperTest;
import com.visitscotland.brxm.dms.ProductSearchBuilder;

import java.util.Collections;

import static org.mockito.Mockito.*;

public class MegalinksMockService {

    ProductSearchBuilder builder;

    Megalinks mega;

    @Deprecated
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

    public Megalinks mockSingleImage() {
        Megalinks mega = mock(Megalinks.class, withSettings().lenient());
        SingleImageModule sim = mock(SingleImageModule.class);
        when(mega.getSingleImageModule()).thenReturn(sim);

        return mega;
    }

    public MegalinkItem mockItem() {
        return mockItem(false, MegalinkMapperTest.LinkType.CMS);
    }

    public MegalinkItem mockItem(boolean featured, MegalinkMapperTest.LinkType type) {
        MegalinkItem item = mock(MegalinkItem.class, withSettings().lenient());

        when(item.getFeature()).thenReturn(featured);
        if (type == MegalinkMapperTest.LinkType.CMS) {
            when(item.getLinkItem()).thenReturn(mockPage());
        } else {
            SharedLink link = mockSharedLink(type);
            when(item.getLinkItem()).thenReturn(link);
        }

        return item;
    }

    private SharedLink mockSharedLink(MegalinkMapperTest.LinkType linkType) {
        SharedLink link = mock(SharedLink.class, withSettings().lenient());
        when(link.getImage()).thenReturn(mock(Image.class, withSettings().lenient()));

        switch (linkType) {
            case DMS:
                DMSLink type = mock(DMSLink.class, withSettings().lenient());
                when(type.getProduct()).thenReturn(MegalinkMapperTest.DMS_ID);
                when(link.getLinkType()).thenReturn(type);
                break;
            case PRODUCT_SEARCH:
                ProductsSearch ps = mock(ProductsSearch.class, withSettings().lenient());
                when(builder.fromHippoBean(ps)).thenReturn(builder);
                when(builder.build()).thenReturn(MegalinkMapperTest.PSR_URL);
                when(link.getLinkType()).thenReturn(ps);
                break;
            case EXTERNAL:
                ExternalLink external = mock(ExternalLink.class, withSettings().lenient());
                when(external.getLink()).thenReturn(MegalinkMapperTest.EXTERNAL_URL);
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
