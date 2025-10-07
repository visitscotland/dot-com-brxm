package com.visitscotland.brxm.pagebuilder;

import com.visitscotland.brxm.hippobeans.*;
import com.visitscotland.brxm.mapper.*;
import com.visitscotland.brxm.mapper.module.ArticleMapper;
import com.visitscotland.brxm.mapper.module.ICentreMapper;
import com.visitscotland.brxm.mapper.module.LongCopyMapper;
import com.visitscotland.brxm.mapper.module.MegalinkMapper;
import com.visitscotland.brxm.mock.LinksModuleMockBuilder;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.*;
import com.visitscotland.brxm.model.megalinks.*;
import com.visitscotland.brxm.mock.MegalinksMockBuilder;
import com.visitscotland.brxm.services.DocumentUtilsService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.brxm.utils.SiteProperties;
import org.hippoecm.hst.mock.core.component.MockHstRequest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PageAssemblerTest {

    MockHstRequest request;

    @Mock
    Page page;

    @Mock
    ICentreMapper iCentreMapper;

    @Mock
    MegalinkMapper megalinkMapper;

    @Mock
    ArticleMapper articleMapper;

    @Mock
    LongCopyMapper longCopyMapper;

    @Mock
    DocumentUtilsService utils;

    @Mock
    PreviewWarningMapper previewModeFactory;

    @Mock
    SiteProperties properties;

    @Mock
    ContentLogger logger;

    @Mock
    ResourceBundleService bundle;

    @InjectMocks
    PageAssembler builder;

    @BeforeEach
    void init() {
        request = new MockHstRequest();
        request.setLocale(Locale.UK);

        //Adds a mock document to the Request
        request.setModel("document", page);
    }

    /**
     * Builds a Page with no documents associated
     */
    @Test
    void pageWithoutElements() {
        when(utils.getAllowedDocuments(page)).thenReturn(Collections.emptyList());

        builder.addModules(request);

        List<?> items = (List<?>) request.getAttribute(PageAssembler.PAGE_ITEMS);

        assertEquals(0, items.size());
    }

    /**
     * Build a page with one Megalinks document associated
     */
    @Test
    @Disabled("The responsability of handling Megalinks is not on Megalinks Mapper")
    void addMegalinksModule_basic()  throws PageCompositionException {
        Megalinks megalinks = new MegalinksMockBuilder().build();
        LinksModule<?> module = new LinksModuleMockBuilder().withLink(mock(EnhancedLink.class)).build();

        when(utils.getAllowedDocuments(page)).thenReturn(Collections.singletonList(megalinks));
        doReturn(module).when(megalinkMapper).getMegalinkModule(megalinks, Locale.UK);

        builder.addModules(request);
        List<?> items = (List<?>) request.getAttribute(PageAssembler.PAGE_ITEMS);

        assertEquals(1, items.size());
    }

    /**
     * Build a page with one Megalinks document associated
     */
    @Test
    @DisplayName("VS-3269 - Megalinks with no links are completely removed. But they still show a preview message")
    @Disabled("The responsability of handling Megalinks is not on Megalinks Mapper")
    void addMegalinksModule_noLinks()  throws PageCompositionException {
        Megalinks megalinks = new MegalinksMockBuilder().build();
        LinksModule<?> module = new LinksModuleMockBuilder().build();


        when(utils.getAllowedDocuments(page)).thenReturn(Collections.singletonList(megalinks));
        doReturn(module).when(megalinkMapper).getMegalinkModule(megalinks, Locale.UK);
        when(previewModeFactory.createErrorModule(any())).thenReturn(new Module<>());


        builder.addModules(request);
        List<?> items = (List<?>) request.getAttribute(PageAssembler.PAGE_ITEMS);

        assertEquals(1, items.size());
        assertSame(items.get(0).getClass(), Module.class);
    }

    /**
     * Styles alternate, and the last repeats the first colour
     */
    @Test
    @Disabled("The responsability of handling Megalinks is not on Megalinks Mapper")
    void addMegalinksModule_alternateStyles()  throws PageCompositionException {
        List<BaseDocument> list = Arrays.asList(
                new MegalinksMockBuilder().build(),
                new MegalinksMockBuilder().build(),
                new MegalinksMockBuilder().build(),
                new MegalinksMockBuilder().build());

        when(utils.getAllowedDocuments(page)).thenReturn(list);
        LinksModule<?> module1 = new LinksModuleMockBuilder().withLink(mock(EnhancedLink.class)).title("h2").build();
        LinksModule<?> module2 = new LinksModuleMockBuilder().withLink(mock(EnhancedLink.class)).title("h2").build();
        LinksModule<?> module3 = new LinksModuleMockBuilder().withLink(mock(EnhancedLink.class)).title("h2").build();
        LinksModule<?> module4 = new LinksModuleMockBuilder().withLink(mock(EnhancedLink.class)).title("h2").build();
        doReturn(module1).when(megalinkMapper).getMegalinkModule((Megalinks) list.get(0), Locale.UK);
        doReturn(module2).when(megalinkMapper).getMegalinkModule((Megalinks) list.get(1), Locale.UK);
        doReturn(module3).when(megalinkMapper).getMegalinkModule((Megalinks) list.get(2), Locale.UK);
        doReturn(module4).when(megalinkMapper).getMegalinkModule((Megalinks) list.get(3), Locale.UK);


        builder.addModules(request);
        List<LinksModule<?>> items = request.getModel(PageAssembler.PAGE_ITEMS);

        assertEquals(4, items.size());

        verify(module1).setThemeIndex(0);
        verify(module2).setThemeIndex(1 % CompositionModel.THEMES);
        verify(module3).setThemeIndex(2 % CompositionModel.THEMES);
        verify(module4).setThemeIndex(3 % CompositionModel.THEMES);
    }

    /**
     * 3 first items share colour because their title is null, 4th is different
     */
    @Test
    @Disabled("The responsability of handling Megalinks is not on Megalinks Mapper")
    void addMegalinksModule_skipAlternateStyles_whenNoH2()  throws PageCompositionException {
        List<BaseDocument> list = Arrays.asList(
                new MegalinksMockBuilder().build(),
                new MegalinksMockBuilder().build(),
                new MegalinksMockBuilder().build(),
                new MegalinksMockBuilder().build());

        when(utils.getAllowedDocuments(page)).thenReturn(list);
        LinksModule<?> module1 = new LinksModuleMockBuilder().withLink(mock(EnhancedLink.class)).title("h2").build();
        LinksModule<?> module2 = new LinksModuleMockBuilder().withLink(mock(EnhancedLink.class)).build();
        LinksModule<?> module3 = new LinksModuleMockBuilder().withLink(mock(EnhancedLink.class)).build();
        LinksModule<?> module4 = new LinksModuleMockBuilder().withLink(mock(EnhancedLink.class)).title("h2").build();

        doReturn(module1).when(megalinkMapper).getMegalinkModule((Megalinks) list.get(0), Locale.UK);
        doReturn(module2).when(megalinkMapper).getMegalinkModule((Megalinks) list.get(1), Locale.UK);
        doReturn(module3).when(megalinkMapper).getMegalinkModule((Megalinks) list.get(2), Locale.UK);
        doReturn(module4).when(megalinkMapper).getMegalinkModule((Megalinks) list.get(3), Locale.UK);


        builder.addModules(request);
        List<?> items = request.getModel(PageAssembler.PAGE_ITEMS);

        assertEquals(4, items.size());

        verify(module1).setThemeIndex(0);
        verify(module2).setThemeIndex(0);
        verify(module3).setThemeIndex(0);
        verify(module4).setThemeIndex(1);
    }

    /**
     * First item always have the same style independently of if the section title is defined
     */
    @Test
    @Disabled("The responsability of handling Megalinks is not on Megalinks Mapper")
    void addMegalinksModule_firstItemColourIsStyle3_whenNoH2()  throws PageCompositionException {
        Megalinks mega = new MegalinksMockBuilder().build();
        when(utils.getAllowedDocuments(page)).thenReturn(Collections.singletonList(mega));

        // Build the first case where the first element has no title
        LinksModule<?> module1 = new LinksModuleMockBuilder().withLink(mock(EnhancedLink.class)).build();
        LinksModule<?> module2 = new LinksModuleMockBuilder().withLink(mock(EnhancedLink.class)).title("h2").build();
        doReturn(module1).when(megalinkMapper).getMegalinkModule(mega, Locale.UK);

        builder.addModules(request);

        // Build the second case where the first element has a title
        doReturn(module2).when(megalinkMapper).getMegalinkModule(mega, Locale.UK);
        builder.addModules(request);

        verify(module1).setThemeIndex(0);
        verify(module2).setThemeIndex(0);
    }

    /**
     * Verifies that the alignment for Single Image modules alternates
     */
    @Test
    @Disabled("The responsability of handling Megalinks is not on Megalinks Mapper")
    void addMegalinksModule_alternateAlignment()  throws PageCompositionException {
        List<BaseDocument> list = Arrays.asList(
                new MegalinksMockBuilder().build(),
                new MegalinksMockBuilder().build(),
                new MegalinksMockBuilder().build(),
                new MegalinksMockBuilder().build());

        when(utils.getAllowedDocuments(page)).thenReturn(list);

        LinksModule<?> module1 = new LinksModuleMockBuilder().withLink(mock(EnhancedLink.class)).type(SingleImageLinksModule.class).build();
        LinksModule<?> module2 = new LinksModuleMockBuilder().withLink(mock(EnhancedLink.class)).type(SingleImageLinksModule.class).build();
        LinksModule<?> module3 = new LinksModuleMockBuilder().withLink(mock(EnhancedLink.class)).type(SingleImageLinksModule.class).build();
        LinksModule<?> module4 = new LinksModuleMockBuilder().withLink(mock(EnhancedLink.class)).type(SingleImageLinksModule.class).build();

        doReturn(module1).when(megalinkMapper).getMegalinkModule((Megalinks) list.get(0), Locale.UK);
        doReturn(module2).when(megalinkMapper).getMegalinkModule((Megalinks) list.get(1), Locale.UK);
        doReturn(module3).when(megalinkMapper).getMegalinkModule((Megalinks) list.get(2), Locale.UK);
        doReturn(module4).when(megalinkMapper).getMegalinkModule((Megalinks) list.get(3), Locale.UK);


        builder.addModules(request);
        List<?> items = request.getModel(PageAssembler.PAGE_ITEMS);
        assertEquals(4, items.size());

        verify(module1).setAlignment(CompositionModel.ALIGNMENT[0 % 2]);
        verify(module2).setAlignment(CompositionModel.ALIGNMENT[1 % 2]);
        verify(module3).setAlignment(CompositionModel.ALIGNMENT[2 % 2]);
        verify(module4).setAlignment(CompositionModel.ALIGNMENT[3 % 2]);
    }

    @Test
    @DisplayName("VS-2015 - Match the initial background colour with the megalinks")
    @Disabled("The responsability of handling Megalinks is not on Megalinks Mapper")
    void setIntroTheme()  throws PageCompositionException {
        Megalinks mega = new MegalinksMockBuilder().build();
        when(utils.getAllowedDocuments(page)).thenReturn(Collections.singletonList(mega));

        doReturn(new LinksModuleMockBuilder().withLink(mock(EnhancedLink.class)).build()).when(megalinkMapper).getMegalinkModule(mega, Locale.UK);

        builder.addModules(request);
        LinksModule<?> module = (LinksModule<?>) ((List<?>) request.getModel(PageAssembler.PAGE_ITEMS)).get(0);

        assertNotNull(request.getAttribute(PageAssembler.INTRO_THEME));
        assertEquals(request.getAttribute(PageAssembler.INTRO_THEME), module.getThemeIndex());
    }

    @Test
    @DisplayName("VS-2015 - introTheme is populated with a neutral theme when the theme cannot be inferred")
    void setIntroTheme_forNonMegalinks(){
        when(utils.getAllowedDocuments(page)).thenReturn(Collections.emptyList());

        builder.addModules(request);

        assertNull(request.getAttribute(PageAssembler.INTRO_THEME));
    }

    @Test
    @Disabled("This unit test is to be moved to LongCopyMapper")
    @DisplayName("VS-2132 - Happy Path create a module that contains the basic information")
    void createLongCopy_basic(){
        General page = mock(General.class);
        LongCopy longCopy = mock(LongCopy.class);

        //The module is only allowed got general pages.
        when(page.getTheme()).thenReturn("Simple");
        request.setModel("document", page);

        when(utils.getAllowedDocuments(page)).thenReturn(Collections.singletonList(longCopy));
        when(longCopyMapper.getModule(any(LongCopy.class))).thenReturn(new LongCopyModule());

        builder.addModules(request);

        //List<Module> items = (List<Module>) request.getAttribute(PageTemplateBuilder.PAGE_ITEMS);
        LongCopyModule module = (LongCopyModule) ((List<Module>) request.getModel(PageAssembler.PAGE_ITEMS)).get(0);
        assertNotNull(module);
    }

    @Test
    @DisplayName("VS-2132 - This item allowed on general page type - simple theme pages only (Document types)")
    void createLongCopy_forbidden_destinations(){
        Destination page = mock(Destination.class);
        LongCopy longCopy = mock(LongCopy.class);

        //The module is only allowed got general pages.
        request.setModel("document", page);

        when(utils.getAllowedDocuments(page)).thenReturn(Collections.singletonList(longCopy));

        builder.addModules(request);

        assertEquals(0, ((List<?>) request.getModel(PageAssembler.PAGE_ITEMS)).stream().filter(m -> m instanceof LongCopyModule).count());
    }

    @Test
    @Disabled("This unit test is to be moved to LongCopyMapper")
    @DisplayName("VS-2132 - This item allowed on general page type - simple theme pages only (Themes)")
    void createLongCopy_forbidden_generalStandard(){
        General page = mock(General.class);
        LongCopy longCopy = mock(LongCopy.class);

        //The module is only allowed got general pages.
        when(page.getTheme()).thenReturn("Standard");
        request.setModel("document", page);

        when(utils.getAllowedDocuments(page)).thenReturn(Collections.singletonList(longCopy));

        builder.addModules(request);

        assertEquals(0,((List<?>) request.getAttribute(PageAssembler.PAGE_ITEMS)).stream().filter(m -> !(m instanceof ErrorModule)).count());
    }

    @Test
    @Disabled("This unit test is to be moved to LongCopyMapper")
    @DisplayName("VS-2132 - This item could be used only ... as a single instance")
    void createLongCopy_forbidden_multiple(){
        General page = mock(General.class);

        //The module is only allowed got general pages.
        when(page.getTheme()).thenReturn("Simple");
        request.setModel("document", page);

        when(utils.getAllowedDocuments(page)).thenReturn(Arrays.asList(mock(LongCopy.class), mock(LongCopy.class), mock(LongCopy.class)));
        when(longCopyMapper.getModule(any(LongCopy.class))).thenReturn(new LongCopyModule());

        builder.addModules(request);

        assertEquals(1, ((List<?>) request.getModel(PageAssembler.PAGE_ITEMS)).stream().filter(m -> m instanceof LongCopyModule).count());
    }
}
