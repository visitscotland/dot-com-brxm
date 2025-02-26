package com.visitscotland.brxm.components.navigation;

import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.navigation.NavigationWidget;
import org.hippoecm.hst.content.annotations.PageModelIgnore;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.request.ResolvedSiteMapItem;
import org.hippoecm.hst.core.sitemenu.HstSiteMenu;
import org.hippoecm.hst.core.sitemenu.HstSiteMenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuItem implements HstSiteMenuItem {

    private final HstSiteMenuItem hstMenuItem;
    private final List<HstSiteMenuItem> children;

    @PageModelIgnore
    private MenuItem parent;

    private String title;
    @PageModelIgnore
    private Page page;
    private NavigationWidget widget;
    private String cta;
    private String externalLink;
    private String plainLink;


    public MenuItem(HstSiteMenuItem hstMenuItem) {
        this.children = new ArrayList<>();
        this.hstMenuItem = hstMenuItem;
    }

    public HstSiteMenuItem getHstMenuItem() {
        return hstMenuItem;
    }

    public NavigationWidget getWidget() {
        return widget;
    }

    public void setWidget(NavigationWidget widget) {
        this.widget = widget;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public List<HstSiteMenuItem> getChildMenuItems() {
        return children;
    }

    public void addChild(MenuItem child) {
        if (child != null && !children.contains(child)) {
            children.add(child);
            child.parent = this;
        }
    }

    public boolean removeChild(MenuItem child) {
        if (children.contains(child)) {
            return children.remove(child);
        } else {
            return false;
        }
    }

    @Override
    public HstSiteMenuItem getParentItem() {
        return parent;
    }

    public String getCta() {
        return cta;
    }

    public void setCta(String cta) {
        this.cta = cta;
    }


    // Delegated methods

    @Override
    public String getName() {
        return hstMenuItem.getName();
    }

    @Override
    public HstSiteMenu getHstSiteMenu() {
        return hstMenuItem.getHstSiteMenu();
    }

    @Override
    public String getParameter(String s) {
        return hstMenuItem.getParameter(s);
    }

    @Override
    public String getLocalParameter(String s) {
        return hstMenuItem.getLocalParameter(s);
    }

    @Override
    public Map<String, String> getParameters() {
        return hstMenuItem.getParameters();
    }

    @Override
    public Map<String, String> getLocalParameters() {
        return hstMenuItem.getLocalParameters();
    }

    @Override
    public HstLink getHstLink() {
        return hstMenuItem.getHstLink();
    }

    public void setExternalLink(String externalLink){
        this.externalLink = externalLink;
    }

    @Override
    public String getExternalLink() {
        if (externalLink == null){
            return hstMenuItem.getExternalLink();
        } else {
            return externalLink;
        }

    }

    @Override
    public ResolvedSiteMapItem resolveToSiteMapItem() {
        return hstMenuItem.resolveToSiteMapItem();
    }

    @Override
    public boolean isExpanded() {
        return hstMenuItem.isExpanded();
    }

    @Override
    public Map<String, Object> getProperties() {
        return hstMenuItem.getProperties();
    }

    @Override
    public boolean isRepositoryBased() {
        return hstMenuItem.isRepositoryBased();
    }

    @Override
    public int getDepth() {
        return hstMenuItem.getDepth();
    }

    @Override
    public boolean isSelected() {
        return hstMenuItem.isSelected();
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getPlainLink() {
        return plainLink;
    }

    public void setPlainLink(String plainLink) {
        this.plainLink = plainLink;
    }
}
