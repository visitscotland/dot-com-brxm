package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.components.content.service.AuthorService;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;
import org.springframework.stereotype.Component;

@Component
public abstract class PageTemplateInitializer  {

    private final AuthorService authorService;

    public PageTemplateInitializer(AuthorService authorService) {
        this.authorService = authorService;
    }

    public PageIntro getPageIntro(PageCompositionHelper pageCompositionHelper) {

        try {
            return initPageIntro(pageCompositionHelper);
        } catch (PageCompositionException e) {
            //TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    private PageIntro initPageIntro(PageCompositionHelper pageCompositionHelper) throws PageCompositionException {
        final Page page = pageCompositionHelper.getPage();
        final PageIntro pageTemplate = new PageIntro(page);

        authorService.getBlog(pageCompositionHelper).ifPresent(pageTemplate::setAuthor);

        return pageTemplate;
    }

}
