package com.visitscotland.brxm.utils.pagebuilder;

import com.visitscotland.brxm.model.Module;

import java.util.ArrayList;
import java.util.List;

public class CompositionModel {

    static final String[] ALIGNMENT = {"right", "left"};
    static final Integer THEMES = 3;

    private List<Module<?>> modules = new ArrayList<>();
    private int style = 0;
    private int alignment = 0;


    List<Module<?>> getModules() {
        return modules;
    }

    String calculateAlignment(){
        return ALIGNMENT[alignment++ % ALIGNMENT.length];
    }
    /**
     * Return the current Style index and increment the value of the index
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
