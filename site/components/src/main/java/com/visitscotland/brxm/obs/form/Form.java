package com.visitscotland.brxm.obs.form;

import java.util.ArrayList;
import java.util.List;

public class Form {
    Content content = new Content();
    List<Field> fields = new ArrayList<>();

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
