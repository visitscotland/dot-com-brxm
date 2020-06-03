package com.visitscotland.brmx.utils;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import com.visitscotland.brmx.beans.*;
import com.visitscotland.brmx.beans.dms.LocationObject;
import com.visitscotland.brmx.beans.mapping.megalinks.AbstractLayout;
import com.visitscotland.brmx.beans.mapping.megalinks.FeaturedLayout;
import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.MockType;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class LinkModulesFactoryTest {

    private final String TITLE = "Megalink title";
    
    //This constants must not generate the name from the class since Freemarker is not aware of them so any change would break the template 
    final String LIST = "ListLayout";
    final String FEATURED = "FeaturedLayout";
    final String SINGLE_IMAGE = "SingleImageLayout";

    static LinkModulesFactory factory;


    static Page page;

    /**
     * {@code factory} needs an static method (createUrl) to be mocked since it relies on a static BloomReach dependency
     *
     * {@code page} represent a dummy link.
     */
    @BeforeAll
    static void init(){
        Image image = EasyMock.createNiceMock(Image.class);

        factory = EasyMock.partialMockBuilder(LinkModulesFactory.class)
                .addMockedMethod("createUrl", HippoBean.class)
                .addMockedMethod("getLocation", String.class, Locale.class)
                .createMock();

        page = EasyMock.createNiceMock(Page.class);

        EasyMock.expect(page.getTitle()).andReturn("Linked page").anyTimes();
        EasyMock.expect(page.getTeaser()).andReturn("Teaser of the linked page").anyTimes();
        EasyMock.expect(page.getHeroImage()).andReturn(image).anyTimes();

        EasyMock.expect(factory.createUrl(page)).andReturn("/fake-url/mock").anyTimes();
        EasyMock.expect(factory.getLocation(EasyMock.anyString(), EasyMock.anyObject(Locale.class))).andReturn(null).anyTimes();
        EasyMock.replay(factory, page, image);
    }

    @Test
    void getListLayout(){
        Megalinks mega = createMockMegalink(TITLE, false, true, true, 0);
        AbstractLayout layout = factory.getMegalinkModule(mega, Locale.UK);
        Assertions.assertEquals(layout.getType(), LIST);
    }

    @Test
    void getListLayoutOn7OrMoreItems(){
        Megalinks mega = createMockMegalink(TITLE, false, false, true, LinkModulesFactory.MAX_ITEMS);
        AbstractLayout layout = factory.getMegalinkModule(mega, Locale.UK);
        Assertions.assertNotEquals(layout.getType(), LIST);

        mega = createMockMegalink(TITLE, false, false, true, LinkModulesFactory.MAX_ITEMS + 1);
        layout = factory.getMegalinkModule(mega, Locale.UK);
        Assertions.assertEquals(layout.getType(), LIST);
    }

    @Test
    void getSingleImage(){
        Megalinks mega = createMockMegalink(TITLE, false, false, true, 0, "Single image title");
        AbstractLayout layout = factory.getMegalinkModule(mega, Locale.UK);
        Assertions.assertEquals(layout.getType(), SINGLE_IMAGE);
    }

    @Test
    void getSingleImageWithLargeAmountOfItems(){
        Megalinks mega = createMockMegalink(TITLE, false, false, true, LinkModulesFactory.MAX_ITEMS + 1, "Single image title");
        AbstractLayout layout = factory.getMegalinkModule(mega, Locale.UK);
        Assertions.assertEquals(layout.getType(), SINGLE_IMAGE);
    }

    @Test
    void getFeatured(){
        for (int i= 0; i < LinkModulesFactory.MAX_ITEMS; i++) {
            Megalinks mega = createMockMegalink(TITLE, false, false, true, i);
            AbstractLayout layout = factory.getMegalinkModule(mega, Locale.UK);
            Assertions.assertEquals(layout.getType(), FEATURED);
        }
    }

    @Test
    void countFeaturedItems(){
        createFeaturedLayoutAndCheckItems(1,0,0);
        createFeaturedLayoutAndCheckItems(2,0,0);
        createFeaturedLayoutAndCheckItems(3,0,1);
        createFeaturedLayoutAndCheckItems(4,1,2);
        createFeaturedLayoutAndCheckItems(5,1,2);
        createFeaturedLayoutAndCheckItems(6,1,2);

        //Check that from 7 items is not Featured any longer.
        Megalinks mega = createMockMegalink(TITLE, false, false, true, 7);
        AbstractLayout layout = factory.getMegalinkModule(mega, Locale.UK);
        Assertions.assertNotEquals(layout.getType(), FEATURED);
    }
    
    private void createFeaturedLayoutAndCheckItems(int total, int minItems, int maxItems){
        Megalinks min = createMockMegalink(TITLE, false, false, true, 0);
        Megalinks max = createMockMegalink(TITLE, false, false, true, 0);

        for (int i = 0; i < total; i++){
            min.getMegalinkItems().add(createMockMegalinkItem(false));
            max.getMegalinkItems().add(createMockMegalinkItem(true));
        }

        Assertions.assertEquals(((FeaturedLayout) factory.getMegalinkModule(min, Locale.UK)).getFeaturedLinks().size(), minItems);
        Assertions.assertEquals(((FeaturedLayout) factory.getMegalinkModule(max, Locale.UK)).getFeaturedLinks().size(), maxItems);
    }

    @Test
    void addValidLinkElements(){

        MegalinkItem mi = EasyMock.createMock(MegalinkItem.class);

        EasyMock.expect(mi.getFeature()).andReturn(false).anyTimes();
        EasyMock.expect(mi.getLink()).andReturn(page).anyTimes();


        EasyMock.replay(mi);

        Assertions.assertEquals(factory.convertoToFlatLinks(Arrays.asList(mi)).size(), 1);
        Assertions.assertEquals(factory.convertToEnhancedLinks(Arrays.asList(mi), Locale.UK).size(), 1);

        //This verifies that messages were generated and include the problematic node
        EasyMock.verify(mi);
    }

    @Test
    void skipNullLinkElements(){

        MegalinkItem mi = EasyMock.createMock(MegalinkItem.class);

        EasyMock.expect(mi.getFeature()).andReturn(false).anyTimes();
        EasyMock.expect(mi.getLink()).andReturn(null).anyTimes();
        EasyMock.expect(mi.getPath()).andReturn("path/to/node").times(2);

        EasyMock.replay(mi);

        Assertions.assertEquals(factory.convertoToFlatLinks(Arrays.asList(mi)).size(), 0);
        Assertions.assertEquals(factory.convertToEnhancedLinks(Arrays.asList(mi), Locale.UK).size(), 0);

        //This verifies that messages were generated and include the problematic node
        EasyMock.verify(mi);
    }

    @Test
    void skipInvalidLinkElements(){

        MegalinkItem mi = EasyMock.createMock(MegalinkItem.class);

        EasyMock.expect(mi.getFeature()).andReturn(false).anyTimes();
        EasyMock.expect(mi.getLink()).andReturn(EasyMock.createNiceMock(Megalinks.class)).anyTimes();
        EasyMock.expect(mi.getPath()).andReturn("path/to/node").times(2);

        EasyMock.replay(mi);

        Assertions.assertEquals(factory.convertoToFlatLinks(Arrays.asList(mi)).size(), 0);
        Assertions.assertEquals(factory.convertToEnhancedLinks(Arrays.asList(mi), Locale.UK).size(), 0);

        //This verifies that messages were generated and include the problematic node
        EasyMock.verify(mi);
    }


    private Megalinks createMockMegalink(String title, boolean introduction, boolean listView, boolean teaser, int links, String intro){
        Megalinks mega = EasyMock.createMock(Megalinks.class);
        SingleImageModule single = EasyMock.createNiceMock(SingleImageModule.class);
        Image img = EasyMock.createNiceMock(Image.class);
        List<MegalinkItem> items = new ArrayList<>();

        EasyMock.expect(mega.getTitle()).andReturn(title).anyTimes();
        EasyMock.expect(mega.getList()).andReturn(listView).anyTimes();
        EasyMock.expect(mega.getHideTeaser()).andReturn(teaser).anyTimes();

        EasyMock.expect(mega.getSingleImageModule()).andReturn(single).anyTimes();

        EasyMock.expect(single.getTitle()).andReturn(intro);
        EasyMock.expect(single.getImage()).andReturn(img);
        EasyMock.expect(single.getFullWidth()).andReturn(false);
        EasyMock.expect(single.getIntroduction()).andReturn(EasyMock.createNiceMock(HippoHtml.class));

        if (introduction){
            EasyMock.expect(mega.getIntroduction()).andReturn(EasyMock.createNiceMock(HippoHtml.class)).anyTimes();
        } else {
            EasyMock.expect(mega.getIntroduction()).andReturn(null).anyTimes();
        }

        for (int i=0; i<links; i++) {
            items.add(createMockMegalinkItem(false));
        }

        EasyMock.expect(mega.getMegalinkItems()).andReturn(items).anyTimes();

        EasyMock.replay(mega, single, img);

        return mega;
    }


    private Megalinks createMockMegalink(String title, boolean introduction, boolean listView, boolean teaser, int links){
        Megalinks mega = EasyMock.createMock(Megalinks.class);
        List<MegalinkItem> items = new ArrayList<>();

        EasyMock.expect(mega.getTitle()).andReturn(title).anyTimes();
        EasyMock.expect(mega.getList()).andReturn(listView).anyTimes();
        EasyMock.expect(mega.getHideTeaser()).andReturn(teaser).anyTimes();
        EasyMock.expect(mega.getSingleImageModule()).andReturn(null).anyTimes();

        if (introduction){
            EasyMock.expect(mega.getIntroduction()).andReturn(EasyMock.createNiceMock(HippoHtml.class)).anyTimes();
        } else {
            EasyMock.expect(mega.getIntroduction()).andReturn(null).anyTimes();
        }

        for (int i=0; i<links; i++) {
            items.add(createMockMegalinkItem(false));
        }

        EasyMock.expect(mega.getMegalinkItems()).andReturn(items).anyTimes();

        EasyMock.replay(mega);

        return mega;
    }

    private MegalinkItem createMockMegalinkItem(boolean featured) {
        MegalinkItem item = EasyMock.createMock(MegalinkItem.class);

        EasyMock.expect(item.getFeature()).andReturn(featured).anyTimes();
        EasyMock.expect(item.getLink()).andReturn(page).anyTimes();

        EasyMock.replay(item);

        return item;
    }
}
