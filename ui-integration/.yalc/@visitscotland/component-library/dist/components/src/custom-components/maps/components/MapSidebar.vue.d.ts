declare function __VLS_template(): {
    "vs-map-sidebar-sub-filters"?(_: {}): any;
    "vs-map-sidebar-search-results"?(_: {}): any;
};
declare const __VLS_component: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /** Text query from Map Search */
    query: {
        type: StringConstructor;
        default: string;
    };
    /** Selected Top Level Category */
    selectedCategories: {
        type: StringConstructor;
        default: string;
    };
    /** Label for the sidebar header */
    headerLabel: {
        type: StringConstructor;
        default: string;
    };
    /** Label for the close sidebar button */
    closeSidebarButtonLabel: {
        type: StringConstructor;
        default: string;
    };
    /** Label for the input placeholder text */
    inputPlaceholderLabel: {
        type: StringConstructor;
        default: string;
    };
    /** Label for the search button */
    searchButtonLabel: {
        type: StringConstructor;
        default: string;
    };
    /** Label for the clear map link */
    clearMapLabel: {
        type: StringConstructor;
        default: string;
    };
    subFilterHeaderLabel: {
        type: StringConstructor;
        default: string;
    };
    /** Label for the search results text */
    searchResultsLabel: {
        type: StringConstructor;
        default: string;
    };
    /** Label for the open sidebar button */
    openSidebarButtonLabel: {
        type: StringConstructor;
        default: string;
    };
}>, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {
    "search-input-changed": (...args: any[]) => void;
    "reset-map": (...args: any[]) => void;
}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /** Text query from Map Search */
    query: {
        type: StringConstructor;
        default: string;
    };
    /** Selected Top Level Category */
    selectedCategories: {
        type: StringConstructor;
        default: string;
    };
    /** Label for the sidebar header */
    headerLabel: {
        type: StringConstructor;
        default: string;
    };
    /** Label for the close sidebar button */
    closeSidebarButtonLabel: {
        type: StringConstructor;
        default: string;
    };
    /** Label for the input placeholder text */
    inputPlaceholderLabel: {
        type: StringConstructor;
        default: string;
    };
    /** Label for the search button */
    searchButtonLabel: {
        type: StringConstructor;
        default: string;
    };
    /** Label for the clear map link */
    clearMapLabel: {
        type: StringConstructor;
        default: string;
    };
    subFilterHeaderLabel: {
        type: StringConstructor;
        default: string;
    };
    /** Label for the search results text */
    searchResultsLabel: {
        type: StringConstructor;
        default: string;
    };
    /** Label for the open sidebar button */
    openSidebarButtonLabel: {
        type: StringConstructor;
        default: string;
    };
}>> & Readonly<{
    "onSearch-input-changed"?: (...args: any[]) => any;
    "onReset-map"?: (...args: any[]) => any;
}>, {
    query: string;
    selectedCategories: string;
    headerLabel: string;
    closeSidebarButtonLabel: string;
    inputPlaceholderLabel: string;
    searchButtonLabel: string;
    clearMapLabel: string;
    subFilterHeaderLabel: string;
    searchResultsLabel: string;
    openSidebarButtonLabel: string;
}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
declare const _default: __VLS_WithTemplateSlots<typeof __VLS_component, ReturnType<typeof __VLS_template>>;
export default _default;
type __VLS_WithTemplateSlots<T, S> = T & {
    new (): {
        $slots: S;
    };
};
