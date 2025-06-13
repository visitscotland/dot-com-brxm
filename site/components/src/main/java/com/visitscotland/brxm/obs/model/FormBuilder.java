package com.visitscotland.brxm.obs.model;

import com.visitscotland.brxm.obs.form.Field;
import com.visitscotland.brxm.obs.form.Form;
import com.visitscotland.brxm.obs.form.Option;

import java.util.ArrayList;
import java.util.List;

public class FormBuilder {

    private Form form = new Form();

    public static FormBuilder create() {
        return new FormBuilder();
    }

    public Form build() {
        return form;
    }

    public FormBuilder input(String label, boolean required) {
        Field field = new Field();
        field.setLabel(label);
        field.setName(toKebabCase(label));
        field.setElement("input");
        field.setType("text");

        if (required) {
            field.getValidation().put("required", true);
            field.getValidationMessages().put("required", "This field is required");
        }
        form.getFields().add(field);

        return this;
    }

    public FormBuilder email(String label, boolean required) {
        Field field = new Field();
        field.setLabel(label);
        field.setName(toKebabCase(label));
        field.setElement("input");
        field.setType("email");

        field.getValidation().put("email", true);
        field.getValidationMessages().put("email", "Enter an email address in the correct format e.g. name@example.com");

        if (required) {
            field.getValidation().put("required", true);
            field.getValidationMessages().put("required", "This field is required");
        }

        form.getFields().add(field);

        return this;
    }

    public FormBuilder selectImportance(String label, String id, boolean required) {
        Field field = new Field();
        field.setLabel(label);
        field.setName(id);
        field.setElement("select");

        List<Option> options = new ArrayList<Option>();

        options.add(new Option("Very Important","3"));
        options.add(new Option("Desired","2"));
        options.add(new Option("Useful","1"));
        options.add(new Option("Not Required","0"));

        if (required) {
            field.getValidation().put("required", true);
            field.getValidationMessages().put("required", "This field is required");
        }
        form.getFields().add(field);

        return this;
    }

    private String toKebabCase(String text){
        if (text != null) {
            return text.toLowerCase().replaceAll("\\W", "-").replaceAll("-{2,}", "-").replaceAll("-$", "").replaceAll("^-", "");
        }
        return null;
    }
}
