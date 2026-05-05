package com.visitscotland.brxm.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.visitscotland.brxm.hippobeans.SearchWidget;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

import java.util.Map;

public class SearchWidgetModule extends Module<SearchWidget>{

    private String title;
    private HippoHtml introduction;
    private String button;
    private String placeholder;
    private String mainCategory;
    private Map<String, String> categories;
    private Map<String, String> subcategories;
    private JsonNode filters;
    private String id;

     public SearchWidgetModule(SearchWidget hippoBean, String id) {
        setHippoBean(hippoBean);
        setId(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HippoHtml getIntroduction() {
        return introduction;
    }

    public void setIntroduction(HippoHtml description) {
        this.introduction = description;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public Map<String, String> getCategories() {
        return categories;
    }

    public void setCategories(Map<String, String> categories) {
        this.categories = categories;
    }

    public Map<String, String> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(Map<String, String> subcategories) {
        this.subcategories = subcategories;
    }

    public JsonNode getFilters() {
        return filters;
    }

    public void setFilters(JsonNode filters) {
        this.filters = filters;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

