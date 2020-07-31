package com.visitscotland.brmx.components.navigation;

import org.hippoecm.hst.core.sitemenu.EditableMenu;
import org.hippoecm.hst.core.sitemenu.HstSiteMenu;
import org.hippoecm.hst.core.sitemenu.HstSiteMenuItem;
import org.hippoecm.hst.core.sitemenu.HstSiteMenus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class RootMenuItemTest {




//    private HstSiteMenu hstMenu;
//
//    private List<HstSiteMenuItem> menuItems;
//
//    public RootMenuItem(HstSiteMenu hstMenu){
//        this.hstMenu = hstMenu;
//    }
//
//    public void setSiteMenuItems(List<HstSiteMenuItem> items){
//        this.menuItems = items;
//    }
//
//    @Override
//    public List<HstSiteMenuItem> getSiteMenuItems() {
//        return (List<HstSiteMenuItem>) menuItems;
//    }

    @Test
    void menuItems() {

    }

    @Test
    void encapsulatedMethods() {
        // The following method are encapsulated from the original HstMenuItem to ensure the out-of-the-box menu
        // functionality keeps working
        HstSiteMenu hsm = mock(HstSiteMenu.class);
        RootMenuItem item = new RootMenuItem(hsm);

        HstSiteMenus hsmMock = mock(HstSiteMenus.class);
        when(item.getHstSiteMenus()).thenReturn(hsmMock);
        Assertions.assertEquals(hsmMock, item.getHstSiteMenus());
        verify(hsm).getHstSiteMenus();

        HstSiteMenuItem hsmiMock = mock(HstSiteMenuItem.class);
        when(item.getHstSiteMenus()).thenReturn(hsmMock);
        Assertions.assertEquals(hsmMock, item.getHstSiteMenus());
        verify(hsm).getHstSiteMenus();

        when(item.getDeepestExpandedItem()).thenReturn(hsmiMock);
        Assertions.assertEquals(hsmiMock, item.getDeepestExpandedItem());
        verify(hsm).getDeepestExpandedItem();

        when(item.getSelectSiteMenuItem()).thenReturn(hsmiMock);
        Assertions.assertEquals(hsmiMock, item.getSelectSiteMenuItem());
        verify(hsm).getSelectSiteMenuItem();

        EditableMenu editableMenu = mock(EditableMenu.class);
        when(item.getEditableMenu()).thenReturn(editableMenu);
        Assertions.assertEquals(editableMenu, item.getEditableMenu());
        verify(hsm).getEditableMenu();

        when(item.getName()).thenReturn("name");
        Assertions.assertEquals("name", item.getName());
        verify(hsm).getName();

        when(item.isExpanded()).thenReturn(true);
        Assertions.assertEquals(true, item.isExpanded());
        verify(hsm).isExpanded();
    }
}
