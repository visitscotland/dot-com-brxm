package com.visitscotland.brxm.obs.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Field {
    String name;
    String element;
    String type;
    String label;
    String hint;
    Map<String, Object> validation = new HashMap<>();
    Map<String, Object> validationMessages = new HashMap<>();
    List<Option> options;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public Map<String, Object> getValidation() {
        return validation;
    }

    public void setValidation(Map<String, Object> validation) {
        this.validation = validation;
    }

    public Map<String, Object> getValidationMessages() {
        return validationMessages;
    }

    public void setValidationMessages(Map<String, Object> validationMessages) {
        this.validationMessages = validationMessages;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
