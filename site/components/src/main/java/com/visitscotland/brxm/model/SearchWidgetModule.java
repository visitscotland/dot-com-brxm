package com.visitscotland.brxm.model;

import java.util.List;

public class SearchWidgetModule {

    private String title;
    private String description;
    private String prompt;
    private String mainCategory;
    private List<String> categories;
    private List<String> subcategories;

    public SearchWidgetModule() {
    }

    public SearchWidgetModule(String title,
                              String description,
                              String prompt,
                              String mainCategory,
                              List<String> categories,
                              List<String> subcategories) {
        this.title = title;
        this.description = description;
        this.prompt = prompt;
        this.mainCategory = mainCategory;
        this.categories = categories;
        this.subcategories = subcategories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<String> subcategories) {
        this.subcategories = subcategories;
    }
}

