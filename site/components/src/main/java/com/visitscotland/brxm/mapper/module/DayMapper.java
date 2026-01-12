package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.hippobeans.Day;
import com.visitscotland.brxm.model.ItineraryDayModule;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import org.springframework.stereotype.Component;

import java.util.MissingResourceException;

@Component
public class DayMapper extends ModuleMapper<Day, ItineraryDayModule> {


    @Override
    public void addLabels(PageCompositionHelper compositionHelper) throws MissingResourceException {

    }

    @Override
    public ItineraryDayModule map(Day document, PageCompositionHelper compositionHelper) throws PageCompositionException {
        ItineraryDayModule day = new ItineraryDayModule();
        day.setTitle(document.getTitle());
        day.setIntroduction(document.getIntroduction());

        return day;
    }

}
