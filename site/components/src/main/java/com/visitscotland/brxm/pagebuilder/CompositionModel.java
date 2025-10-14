package com.visitscotland.brxm.pagebuilder;

import com.visitscotland.brxm.model.Module;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompositionModel {

    static final String[] ALIGNMENT = {"right", "left"};
    static final Integer THEMES = 3;

    private final List<Module<?>> modules = new ArrayList<>();
    private int style = 0;
    private int alignment = 0;


    List<Module<?>> getModules() {
        return modules;
    }

    void addModule(Module<?> module){
        modules.add(module);
    }

    String calculateAlignment(){
        return ALIGNMENT[alignment++ % ALIGNMENT.length];
    }
    /**
     * Returns the current theme index (which is the style index modulo THEMES)
     * and optionally increments the style index
     *
     * @param increment Whether to increment the style index
     * @return The current theme index (0 to THEMES-1)
     */
    int calculateThemeIndex(boolean increment){
        return calculateStyleIndex(increment) % THEMES;
    }

    private int calculateStyleIndex(boolean increment) {
        if (increment) {
            return style++;
        } else if (style > 0) {
            return style - 1;
        } else {
            return 0;
        }
    }
}
