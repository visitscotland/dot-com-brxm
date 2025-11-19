declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * Changes to a no-container layout.
     * Rounded tabs that are the width of their content,
     * and centered in the tab row.
     * Provided to child components.
     */
    noContainer: {
        type: BooleanConstructor;
        default: boolean;
    };
}>, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * Changes to a no-container layout.
     * Rounded tabs that are the width of their content,
     * and centered in the tab row.
     * Provided to child components.
     */
    noContainer: {
        type: BooleanConstructor;
        default: boolean;
    };
}>> & Readonly<{}>, {
    noContainer: boolean;
}, {}, {
    BTabs: {
        new (...args: any[]): import('vue').CreateComponentPublicInstanceWithMixins<Readonly<import('vue').ExtractPropTypes<{
            modelValue: import('vue').PropType<number>;
            activeId: import('vue').PropType<string | undefined>;
            fill: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            tag: {
                type: import('vue').PropType<string>;
                default: string;
            };
            small: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            end: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            id: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            align: {
                type: import('vue').PropType<import('bootstrap-vue-next').AlignmentJustifyContent>;
                default: undefined;
            };
            lazy: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            noFade: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            vertical: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            contentClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            card: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            justified: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            pills: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            activeTabClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            activeNavItemClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            inactiveNavItemClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            inactiveTabClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            navClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            navItemClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            navWrapperClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            noNavStyle: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            tabClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
        }>> & {
            onClick?: (() => any) | undefined;
            "onActivate-tab"?: ((v1: number, v2: number, v3: import('bootstrap-vue-next').BvEvent) => any) | undefined;
        }, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {
            click: () => void;
            "activate-tab": (v1: number, v2: number, v3: import('bootstrap-vue-next').BvEvent) => void;
        }, import('vue').PublicProps, {
            fill: boolean;
            tag: string;
            small: boolean;
            end: boolean;
            id: string;
            align: import('bootstrap-vue-next').AlignmentJustifyContent;
            lazy: boolean;
            noFade: boolean;
            vertical: boolean;
            contentClass: any;
            card: boolean;
            justified: boolean;
            pills: boolean;
            activeTabClass: any;
            activeNavItemClass: any;
            inactiveNavItemClass: any;
            inactiveTabClass: any;
            navClass: any;
            navItemClass: any;
            navWrapperClass: any;
            noNavStyle: boolean;
            tabClass: any;
        }, true, {}, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, {}, any, import('vue').ComponentProvideOptions, {
            P: {};
            B: {};
            D: {};
            C: {};
            M: {};
            Defaults: {};
        }, Readonly<import('vue').ExtractPropTypes<{
            modelValue: import('vue').PropType<number>;
            activeId: import('vue').PropType<string | undefined>;
            fill: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            tag: {
                type: import('vue').PropType<string>;
                default: string;
            };
            small: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            end: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            id: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            align: {
                type: import('vue').PropType<import('bootstrap-vue-next').AlignmentJustifyContent>;
                default: undefined;
            };
            lazy: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            noFade: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            vertical: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            contentClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            card: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            justified: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            pills: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            activeTabClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            activeNavItemClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            inactiveNavItemClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            inactiveTabClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            navClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            navItemClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            navWrapperClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            noNavStyle: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            tabClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
        }>> & {
            onClick?: (() => any) | undefined;
            "onActivate-tab"?: ((v1: number, v2: number, v3: import('bootstrap-vue-next').BvEvent) => any) | undefined;
        }, {}, {}, {}, {}, {
            fill: boolean;
            tag: string;
            small: boolean;
            end: boolean;
            id: string;
            align: import('bootstrap-vue-next').AlignmentJustifyContent;
            lazy: boolean;
            noFade: boolean;
            vertical: boolean;
            contentClass: any;
            card: boolean;
            justified: boolean;
            pills: boolean;
            activeTabClass: any;
            activeNavItemClass: any;
            inactiveNavItemClass: any;
            inactiveTabClass: any;
            navClass: any;
            navItemClass: any;
            navWrapperClass: any;
            noNavStyle: boolean;
            tabClass: any;
        }>;
        __isFragment?: never;
        __isTeleport?: never;
        __isSuspense?: never;
    } & import('vue').ComponentOptionsBase<Readonly<import('vue').ExtractPropTypes<{
        modelValue: import('vue').PropType<number>;
        activeId: import('vue').PropType<string | undefined>;
        fill: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        tag: {
            type: import('vue').PropType<string>;
            default: string;
        };
        small: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        end: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        id: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        align: {
            type: import('vue').PropType<import('bootstrap-vue-next').AlignmentJustifyContent>;
            default: undefined;
        };
        lazy: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        noFade: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        vertical: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        contentClass: {
            type: import('vue').PropType<any>;
            default: undefined;
        };
        card: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        justified: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        pills: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        activeTabClass: {
            type: import('vue').PropType<any>;
            default: undefined;
        };
        activeNavItemClass: {
            type: import('vue').PropType<any>;
            default: undefined;
        };
        inactiveNavItemClass: {
            type: import('vue').PropType<any>;
            default: undefined;
        };
        inactiveTabClass: {
            type: import('vue').PropType<any>;
            default: undefined;
        };
        navClass: {
            type: import('vue').PropType<any>;
            default: undefined;
        };
        navItemClass: {
            type: import('vue').PropType<any>;
            default: undefined;
        };
        navWrapperClass: {
            type: import('vue').PropType<any>;
            default: undefined;
        };
        noNavStyle: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        tabClass: {
            type: import('vue').PropType<any>;
            default: undefined;
        };
    }>> & {
        onClick?: (() => any) | undefined;
        "onActivate-tab"?: ((v1: number, v2: number, v3: import('bootstrap-vue-next').BvEvent) => any) | undefined;
    }, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {
        click: () => void;
        "activate-tab": (v1: number, v2: number, v3: import('bootstrap-vue-next').BvEvent) => void;
    }, string, {
        fill: boolean;
        tag: string;
        small: boolean;
        end: boolean;
        id: string;
        align: import('bootstrap-vue-next').AlignmentJustifyContent;
        lazy: boolean;
        noFade: boolean;
        vertical: boolean;
        contentClass: any;
        card: boolean;
        justified: boolean;
        pills: boolean;
        activeTabClass: any;
        activeNavItemClass: any;
        inactiveNavItemClass: any;
        inactiveTabClass: any;
        navClass: any;
        navItemClass: any;
        navWrapperClass: any;
        noNavStyle: boolean;
        tabClass: any;
    }, {}, string, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, import('vue').ComponentProvideOptions> & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps & (new () => {
        $slots: Readonly<{
            default?: ((props: Record<string, never>) => any) | undefined;
            empty?: ((props: Record<string, never>) => any) | undefined;
            'tabs-end'?: ((props: Record<string, never>) => any) | undefined;
            'tabs-start'?: ((props: Record<string, never>) => any) | undefined;
        }> & {
            default?: ((props: Record<string, never>) => any) | undefined;
            empty?: ((props: Record<string, never>) => any) | undefined;
            'tabs-end'?: ((props: Record<string, never>) => any) | undefined;
            'tabs-start'?: ((props: Record<string, never>) => any) | undefined;
        };
    });
}, {}, string, () => {
    noContainer: import('vue').ComputedRef<boolean>;
}, true, {}, any>;
export default _default;
