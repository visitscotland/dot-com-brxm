declare const _default: {
    VsMainMap: import('vue').DefineComponent<{}, {
        center: Record<string, any>;
        categories: Record<string, any>;
        noJsMessage: string;
        noCookiesMessage: string;
        jsDisabled: boolean;
        cookieBtnText: string;
        labels: Record<string, any>;
        zoom: number;
        radius: number;
        languageCode: string;
        categoryLabels: Record<string, any>;
        apiKey?: string;
        $props: {
            readonly center?: Record<string, any>;
            readonly categories?: Record<string, any>;
            readonly noJsMessage?: string;
            readonly noCookiesMessage?: string;
            readonly jsDisabled?: boolean;
            readonly cookieBtnText?: string;
            readonly labels?: Record<string, any>;
            readonly zoom?: number;
            readonly radius?: number;
            readonly languageCode?: string;
            readonly categoryLabels?: Record<string, any>;
            readonly apiKey?: string;
        };
    }, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<{}> & Readonly<{}>, {}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
    VsMapSidebar: {
        new (...args: any[]): import('vue').CreateComponentPublicInstanceWithMixins<Readonly<import('vue').ExtractPropTypes<{
            query: {
                type: StringConstructor;
                default: string;
            };
            selectedCategories: {
                type: StringConstructor;
                default: string;
            };
            headerLabel: {
                type: StringConstructor;
                default: string;
            };
            closeSidebarButtonLabel: {
                type: StringConstructor;
                default: string;
            };
            inputPlaceholderLabel: {
                type: StringConstructor;
                default: string;
            };
            searchButtonLabel: {
                type: StringConstructor;
                default: string;
            };
            clearMapLabel: {
                type: StringConstructor;
                default: string;
            };
            subFilterHeaderLabel: {
                type: StringConstructor;
                default: string;
            };
            searchResultsLabel: {
                type: StringConstructor;
                default: string;
            };
            openSidebarButtonLabel: {
                type: StringConstructor;
                default: string;
            };
        }>> & Readonly<{
            "onSearch-input-changed"?: (...args: any[]) => any;
            "onReset-map"?: (...args: any[]) => any;
        }>, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {
            "search-input-changed": (...args: any[]) => void;
            "reset-map": (...args: any[]) => void;
        }, import('vue').PublicProps, {
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
        }, true, {}, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, {}, any, import('vue').ComponentProvideOptions, {
            P: {};
            B: {};
            D: {};
            C: {};
            M: {};
            Defaults: {};
        }, Readonly<import('vue').ExtractPropTypes<{
            query: {
                type: StringConstructor;
                default: string;
            };
            selectedCategories: {
                type: StringConstructor;
                default: string;
            };
            headerLabel: {
                type: StringConstructor;
                default: string;
            };
            closeSidebarButtonLabel: {
                type: StringConstructor;
                default: string;
            };
            inputPlaceholderLabel: {
                type: StringConstructor;
                default: string;
            };
            searchButtonLabel: {
                type: StringConstructor;
                default: string;
            };
            clearMapLabel: {
                type: StringConstructor;
                default: string;
            };
            subFilterHeaderLabel: {
                type: StringConstructor;
                default: string;
            };
            searchResultsLabel: {
                type: StringConstructor;
                default: string;
            };
            openSidebarButtonLabel: {
                type: StringConstructor;
                default: string;
            };
        }>> & Readonly<{
            "onSearch-input-changed"?: (...args: any[]) => any;
            "onReset-map"?: (...args: any[]) => any;
        }>, {}, {}, {}, {}, {
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
        }>;
        __isFragment?: never;
        __isTeleport?: never;
        __isSuspense?: never;
    } & import('vue').ComponentOptionsBase<Readonly<import('vue').ExtractPropTypes<{
        query: {
            type: StringConstructor;
            default: string;
        };
        selectedCategories: {
            type: StringConstructor;
            default: string;
        };
        headerLabel: {
            type: StringConstructor;
            default: string;
        };
        closeSidebarButtonLabel: {
            type: StringConstructor;
            default: string;
        };
        inputPlaceholderLabel: {
            type: StringConstructor;
            default: string;
        };
        searchButtonLabel: {
            type: StringConstructor;
            default: string;
        };
        clearMapLabel: {
            type: StringConstructor;
            default: string;
        };
        subFilterHeaderLabel: {
            type: StringConstructor;
            default: string;
        };
        searchResultsLabel: {
            type: StringConstructor;
            default: string;
        };
        openSidebarButtonLabel: {
            type: StringConstructor;
            default: string;
        };
    }>> & Readonly<{
        "onSearch-input-changed"?: (...args: any[]) => any;
        "onReset-map"?: (...args: any[]) => any;
    }>, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {
        "search-input-changed": (...args: any[]) => void;
        "reset-map": (...args: any[]) => void;
    }, string, {
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
    }, {}, string, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, import('vue').ComponentProvideOptions> & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps & (new () => {
        $slots: {
            "vs-map-sidebar-sub-filters"?(_: {}): any;
            "vs-map-sidebar-search-results"?(_: {}): any;
        };
    });
};
export default _default;
