declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * The aria control ID used for panel ID to match button aria control
     */
    controlId: {
        type: StringConstructor;
        required: true;
    };
    /**
     * The title for the submenu and alias for the testing data-unique-id
     */
    title: {
        type: StringConstructor;
        required: true;
    };
    /**
     * Level of items in the nav
     * `1, 2`
     */
    level: {
        type: StringConstructor;
        required: true;
        validator: (value: unknown) => any;
    };
    /**
     * The CTA link used at the top of the accordion dropdown
     */
    ctaLink: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The CTA text used for the CTA link at the top of the accordion dropdown
     */
    ctaText: {
        type: StringConstructor;
        default: string;
    };
}>, {}, {}, {
    getUniqueId(): string;
    accordionItemClasses(): string[];
}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * The aria control ID used for panel ID to match button aria control
     */
    controlId: {
        type: StringConstructor;
        required: true;
    };
    /**
     * The title for the submenu and alias for the testing data-unique-id
     */
    title: {
        type: StringConstructor;
        required: true;
    };
    /**
     * Level of items in the nav
     * `1, 2`
     */
    level: {
        type: StringConstructor;
        required: true;
        validator: (value: unknown) => any;
    };
    /**
     * The CTA link used at the top of the accordion dropdown
     */
    ctaLink: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The CTA text used for the CTA link at the top of the accordion dropdown
     */
    ctaText: {
        type: StringConstructor;
        default: string;
    };
}>> & Readonly<{}>, {
    ctaLink: string;
    ctaText: string;
}, {}, {
    VsAccordionItem: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
        colourBadge: {
            type: StringConstructor;
            required: false;
            default: string;
        };
        controlId: {
            type: StringConstructor;
            required: true;
        };
        headingLevel: {
            type: NumberConstructor;
            default: number;
            validator: (value: unknown) => any;
        };
        itemBreakPoint: {
            type: StringConstructor;
            default(): "lg";
            validator: (value: unknown) => any;
        };
        openByDefault: {
            type: BooleanConstructor;
            default: boolean;
        };
        variant: {
            type: StringConstructor;
            default: string;
        };
    }>, {}, {
        show: boolean;
    }, {
        toggleAccordionBtn(): string;
        toggleResponsiveItem(): string;
    }, {
        onButtonClick(): void;
    }, {
        computed: {
            pageUrl: () => string;
        };
        data(): {
            dataLayerLoadConfirmed: boolean;
            dataLayerStore: any;
        };
        mounted(): void;
        methods: {
            templateFiller(template: any, values: any): {};
            createDataLayerObject(type: any, event: any, href: any): void;
            returnIsoDate(): string;
            pushToDataLayer(object: any): void;
            compileFullTemplate(templateValues: any): any;
            targetText(event: any): any;
        };
    }, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
        colourBadge: {
            type: StringConstructor;
            required: false;
            default: string;
        };
        controlId: {
            type: StringConstructor;
            required: true;
        };
        headingLevel: {
            type: NumberConstructor;
            default: number;
            validator: (value: unknown) => any;
        };
        itemBreakPoint: {
            type: StringConstructor;
            default(): "lg";
            validator: (value: unknown) => any;
        };
        openByDefault: {
            type: BooleanConstructor;
            default: boolean;
        };
        variant: {
            type: StringConstructor;
            default: string;
        };
    }>> & Readonly<{}>, {
        variant: string;
        colourBadge: string;
        headingLevel: number;
        itemBreakPoint: string;
        openByDefault: boolean;
    }, {}, {
        VsAccordionToggle: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
            variant: {
                type: StringConstructor;
                default: string;
            };
            visible: {
                type: BooleanConstructor;
                default: boolean;
            };
        }>, {}, {}, {}, {
            triggerToggle(): void;
        }, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, "toggle-panel"[], "toggle-panel", import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
            variant: {
                type: StringConstructor;
                default: string;
            };
            visible: {
                type: BooleanConstructor;
                default: boolean;
            };
        }>> & Readonly<{
            "onToggle-panel"?: (...args: any[]) => any;
        }>, {
            variant: string;
            visible: boolean;
        }, {}, {
            VsButton: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
                href: {
                    type: StringConstructor;
                    default: any;
                };
                tabindex: {
                    type: StringConstructor;
                    default: any;
                };
                variant: {
                    type: StringConstructor;
                    default: string;
                    validator: (value: unknown) => any;
                };
                size: {
                    type: StringConstructor;
                    default: string;
                    validator: (value: unknown) => any;
                };
                animate: {
                    type: BooleanConstructor;
                    default: boolean;
                };
                rounded: {
                    type: BooleanConstructor;
                    default: boolean;
                };
                icon: {
                    type: StringConstructor;
                    default: string;
                };
                iconOnly: {
                    type: BooleanConstructor;
                    default: boolean;
                };
                iconPosition: {
                    type: StringConstructor;
                    default: string;
                    validator: (value: unknown) => any;
                };
            }>, {}, {
                isAnimating: boolean;
            }, {
                buttonClasses(): {
                    'vs-button--animated': boolean;
                    'vs-button--is-animating': boolean;
                    'vs-button--rounded': boolean;
                    'vs-button--icon-only': boolean;
                    'vs-button--flex-reverse': boolean;
                }[];
                iconClasses(): {
                    'vs-icon--right': boolean;
                    'vs-icon--left': boolean;
                }[];
            }, {
                animateHandler(event: any): void;
                tabbedIn(event: any): void;
                trackLink(event: any): void;
            }, {
                computed: {
                    pageUrl: () => string;
                };
                data(): {
                    dataLayerLoadConfirmed: boolean;
                    dataLayerStore: any;
                };
                mounted(): void;
                methods: {
                    templateFiller(template: any, values: any): {};
                    createDataLayerObject(type: any, event: any, href: any): void;
                    returnIsoDate(): string;
                    pushToDataLayer(object: any): void;
                    compileFullTemplate(templateValues: any): any;
                    targetText(event: any): any;
                };
            }, import('vue').ComponentOptionsMixin, "btnFocus"[], "btnFocus", import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
                href: {
                    type: StringConstructor;
                    default: any;
                };
                tabindex: {
                    type: StringConstructor;
                    default: any;
                };
                variant: {
                    type: StringConstructor;
                    default: string;
                    validator: (value: unknown) => any;
                };
                size: {
                    type: StringConstructor;
                    default: string;
                    validator: (value: unknown) => any;
                };
                animate: {
                    type: BooleanConstructor;
                    default: boolean;
                };
                rounded: {
                    type: BooleanConstructor;
                    default: boolean;
                };
                icon: {
                    type: StringConstructor;
                    default: string;
                };
                iconOnly: {
                    type: BooleanConstructor;
                    default: boolean;
                };
                iconPosition: {
                    type: StringConstructor;
                    default: string;
                    validator: (value: unknown) => any;
                };
            }>> & Readonly<{
                onBtnFocus?: (...args: any[]) => any;
            }>, {
                icon: string;
                variant: string;
                size: string;
                href: string;
                tabindex: string;
                animate: boolean;
                rounded: boolean;
                iconOnly: boolean;
                iconPosition: string;
            }, {}, {
                BButton: {
                    new (...args: any[]): import('vue').CreateComponentPublicInstanceWithMixins<Readonly<import('vue').ExtractPropTypes<{
                        pressed: import('vue').PropType<boolean | undefined>;
                        tag: {
                            type: import('vue').PropType<string>;
                            default: string;
                        };
                        type: {
                            type: import('vue').PropType<import('bootstrap-vue-next').ButtonType>;
                            default: string;
                        };
                        replace: {
                            type: import('vue').PropType<boolean>;
                            default: undefined;
                        };
                        target: {
                            type: import('vue').PropType<import('bootstrap-vue-next').LinkTarget>;
                            default: undefined;
                        };
                        to: {
                            type: import('vue').PropType<import('vue-router').RouteLocationRaw>;
                            default: undefined;
                        };
                        append: {
                            type: import('vue').PropType<boolean>;
                            default: undefined;
                        };
                        disabled: {
                            type: import('vue').PropType<boolean>;
                            default: undefined;
                        };
                        href: {
                            type: import('vue').PropType<string>;
                            default: undefined;
                        };
                        rel: {
                            type: import('vue').PropType<string>;
                            default: undefined;
                        };
                        size: {
                            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseSize>;
                            default: string;
                        };
                        active: {
                            type: import('vue').PropType<boolean>;
                            default: boolean;
                        };
                        activeClass: {
                            type: import('vue').PropType<string>;
                            default: undefined;
                        };
                        routerComponentName: {
                            type: import('vue').PropType<string>;
                            default: undefined;
                        };
                        variant: {
                            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseButtonVariant | null>;
                            default: string;
                        };
                        opacity: {
                            type: import('vue').PropType<10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                            default: undefined;
                        };
                        opacityHover: {
                            type: import('vue').PropType<10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                            default: undefined;
                        };
                        underlineVariant: {
                            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant | null>;
                            default: undefined;
                        };
                        underlineOffset: {
                            type: import('vue').PropType<1 | 2 | 3 | "1" | "2" | "3">;
                            default: undefined;
                        };
                        underlineOffsetHover: {
                            type: import('vue').PropType<1 | 2 | 3 | "1" | "2" | "3">;
                            default: undefined;
                        };
                        underlineOpacity: {
                            type: import('vue').PropType<0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                            default: undefined;
                        };
                        underlineOpacityHover: {
                            type: import('vue').PropType<0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                            default: undefined;
                        };
                        pill: {
                            type: import('vue').PropType<boolean>;
                            default: boolean;
                        };
                        icon: {
                            type: import('vue').PropType<boolean>;
                            default: undefined;
                        };
                        routerTag: {
                            type: import('vue').PropType<string>;
                            default: undefined;
                        };
                        exactActiveClass: {
                            type: import('vue').PropType<string>;
                            default: undefined;
                        };
                        loading: {
                            type: import('vue').PropType<boolean>;
                            default: boolean;
                        };
                        loadingFill: {
                            type: import('vue').PropType<boolean>;
                            default: boolean;
                        };
                        loadingText: {
                            type: import('vue').PropType<string>;
                            default: string;
                        };
                        squared: {
                            type: import('vue').PropType<boolean>;
                            default: boolean;
                        };
                    }>> & {
                        onClick?: ((value: MouseEvent) => any) | undefined;
                    }, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {
                        click: (value: MouseEvent) => void;
                    }, import('vue').PublicProps, {
                        tag: string;
                        type: import('bootstrap-vue-next').ButtonType;
                        replace: boolean;
                        target: import('bootstrap-vue-next').LinkTarget;
                        to: import('vue-router').RouteLocationRaw;
                        append: boolean;
                        disabled: boolean;
                        href: string;
                        rel: string;
                        size: keyof import('bootstrap-vue-next').BaseSize;
                        active: boolean;
                        activeClass: string;
                        routerComponentName: string;
                        variant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
                        opacity: 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                        opacityHover: 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                        underlineVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                        underlineOffset: 1 | 2 | 3 | "1" | "2" | "3";
                        underlineOffsetHover: 1 | 2 | 3 | "1" | "2" | "3";
                        underlineOpacity: 0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                        underlineOpacityHover: 0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                        pill: boolean;
                        icon: boolean;
                        routerTag: string;
                        exactActiveClass: string;
                        loading: boolean;
                        loadingFill: boolean;
                        loadingText: string;
                        squared: boolean;
                    }, true, {}, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, {}, any, import('vue').ComponentProvideOptions, {
                        P: {};
                        B: {};
                        D: {};
                        C: {};
                        M: {};
                        Defaults: {};
                    }, Readonly<import('vue').ExtractPropTypes<{
                        pressed: import('vue').PropType<boolean | undefined>;
                        tag: {
                            type: import('vue').PropType<string>;
                            default: string;
                        };
                        type: {
                            type: import('vue').PropType<import('bootstrap-vue-next').ButtonType>;
                            default: string;
                        };
                        replace: {
                            type: import('vue').PropType<boolean>;
                            default: undefined;
                        };
                        target: {
                            type: import('vue').PropType<import('bootstrap-vue-next').LinkTarget>;
                            default: undefined;
                        };
                        to: {
                            type: import('vue').PropType<import('vue-router').RouteLocationRaw>;
                            default: undefined;
                        };
                        append: {
                            type: import('vue').PropType<boolean>;
                            default: undefined;
                        };
                        disabled: {
                            type: import('vue').PropType<boolean>;
                            default: undefined;
                        };
                        href: {
                            type: import('vue').PropType<string>;
                            default: undefined;
                        };
                        rel: {
                            type: import('vue').PropType<string>;
                            default: undefined;
                        };
                        size: {
                            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseSize>;
                            default: string;
                        };
                        active: {
                            type: import('vue').PropType<boolean>;
                            default: boolean;
                        };
                        activeClass: {
                            type: import('vue').PropType<string>;
                            default: undefined;
                        };
                        routerComponentName: {
                            type: import('vue').PropType<string>;
                            default: undefined;
                        };
                        variant: {
                            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseButtonVariant | null>;
                            default: string;
                        };
                        opacity: {
                            type: import('vue').PropType<10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                            default: undefined;
                        };
                        opacityHover: {
                            type: import('vue').PropType<10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                            default: undefined;
                        };
                        underlineVariant: {
                            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant | null>;
                            default: undefined;
                        };
                        underlineOffset: {
                            type: import('vue').PropType<1 | 2 | 3 | "1" | "2" | "3">;
                            default: undefined;
                        };
                        underlineOffsetHover: {
                            type: import('vue').PropType<1 | 2 | 3 | "1" | "2" | "3">;
                            default: undefined;
                        };
                        underlineOpacity: {
                            type: import('vue').PropType<0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                            default: undefined;
                        };
                        underlineOpacityHover: {
                            type: import('vue').PropType<0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                            default: undefined;
                        };
                        pill: {
                            type: import('vue').PropType<boolean>;
                            default: boolean;
                        };
                        icon: {
                            type: import('vue').PropType<boolean>;
                            default: undefined;
                        };
                        routerTag: {
                            type: import('vue').PropType<string>;
                            default: undefined;
                        };
                        exactActiveClass: {
                            type: import('vue').PropType<string>;
                            default: undefined;
                        };
                        loading: {
                            type: import('vue').PropType<boolean>;
                            default: boolean;
                        };
                        loadingFill: {
                            type: import('vue').PropType<boolean>;
                            default: boolean;
                        };
                        loadingText: {
                            type: import('vue').PropType<string>;
                            default: string;
                        };
                        squared: {
                            type: import('vue').PropType<boolean>;
                            default: boolean;
                        };
                    }>> & {
                        onClick?: ((value: MouseEvent) => any) | undefined;
                    }, {}, {}, {}, {}, {
                        tag: string;
                        type: import('bootstrap-vue-next').ButtonType;
                        replace: boolean;
                        target: import('bootstrap-vue-next').LinkTarget;
                        to: import('vue-router').RouteLocationRaw;
                        append: boolean;
                        disabled: boolean;
                        href: string;
                        rel: string;
                        size: keyof import('bootstrap-vue-next').BaseSize;
                        active: boolean;
                        activeClass: string;
                        routerComponentName: string;
                        variant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
                        opacity: 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                        opacityHover: 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                        underlineVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                        underlineOffset: 1 | 2 | 3 | "1" | "2" | "3";
                        underlineOffsetHover: 1 | 2 | 3 | "1" | "2" | "3";
                        underlineOpacity: 0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                        underlineOpacityHover: 0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                        pill: boolean;
                        icon: boolean;
                        routerTag: string;
                        exactActiveClass: string;
                        loading: boolean;
                        loadingFill: boolean;
                        loadingText: string;
                        squared: boolean;
                    }>;
                    __isFragment?: never;
                    __isTeleport?: never;
                    __isSuspense?: never;
                } & import('vue').ComponentOptionsBase<Readonly<import('vue').ExtractPropTypes<{
                    pressed: import('vue').PropType<boolean | undefined>;
                    tag: {
                        type: import('vue').PropType<string>;
                        default: string;
                    };
                    type: {
                        type: import('vue').PropType<import('bootstrap-vue-next').ButtonType>;
                        default: string;
                    };
                    replace: {
                        type: import('vue').PropType<boolean>;
                        default: undefined;
                    };
                    target: {
                        type: import('vue').PropType<import('bootstrap-vue-next').LinkTarget>;
                        default: undefined;
                    };
                    to: {
                        type: import('vue').PropType<import('vue-router').RouteLocationRaw>;
                        default: undefined;
                    };
                    append: {
                        type: import('vue').PropType<boolean>;
                        default: undefined;
                    };
                    disabled: {
                        type: import('vue').PropType<boolean>;
                        default: undefined;
                    };
                    href: {
                        type: import('vue').PropType<string>;
                        default: undefined;
                    };
                    rel: {
                        type: import('vue').PropType<string>;
                        default: undefined;
                    };
                    size: {
                        type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseSize>;
                        default: string;
                    };
                    active: {
                        type: import('vue').PropType<boolean>;
                        default: boolean;
                    };
                    activeClass: {
                        type: import('vue').PropType<string>;
                        default: undefined;
                    };
                    routerComponentName: {
                        type: import('vue').PropType<string>;
                        default: undefined;
                    };
                    variant: {
                        type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseButtonVariant | null>;
                        default: string;
                    };
                    opacity: {
                        type: import('vue').PropType<10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                        default: undefined;
                    };
                    opacityHover: {
                        type: import('vue').PropType<10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                        default: undefined;
                    };
                    underlineVariant: {
                        type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant | null>;
                        default: undefined;
                    };
                    underlineOffset: {
                        type: import('vue').PropType<1 | 2 | 3 | "1" | "2" | "3">;
                        default: undefined;
                    };
                    underlineOffsetHover: {
                        type: import('vue').PropType<1 | 2 | 3 | "1" | "2" | "3">;
                        default: undefined;
                    };
                    underlineOpacity: {
                        type: import('vue').PropType<0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                        default: undefined;
                    };
                    underlineOpacityHover: {
                        type: import('vue').PropType<0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                        default: undefined;
                    };
                    pill: {
                        type: import('vue').PropType<boolean>;
                        default: boolean;
                    };
                    icon: {
                        type: import('vue').PropType<boolean>;
                        default: undefined;
                    };
                    routerTag: {
                        type: import('vue').PropType<string>;
                        default: undefined;
                    };
                    exactActiveClass: {
                        type: import('vue').PropType<string>;
                        default: undefined;
                    };
                    loading: {
                        type: import('vue').PropType<boolean>;
                        default: boolean;
                    };
                    loadingFill: {
                        type: import('vue').PropType<boolean>;
                        default: boolean;
                    };
                    loadingText: {
                        type: import('vue').PropType<string>;
                        default: string;
                    };
                    squared: {
                        type: import('vue').PropType<boolean>;
                        default: boolean;
                    };
                }>> & {
                    onClick?: ((value: MouseEvent) => any) | undefined;
                }, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {
                    click: (value: MouseEvent) => void;
                }, string, {
                    tag: string;
                    type: import('bootstrap-vue-next').ButtonType;
                    replace: boolean;
                    target: import('bootstrap-vue-next').LinkTarget;
                    to: import('vue-router').RouteLocationRaw;
                    append: boolean;
                    disabled: boolean;
                    href: string;
                    rel: string;
                    size: keyof import('bootstrap-vue-next').BaseSize;
                    active: boolean;
                    activeClass: string;
                    routerComponentName: string;
                    variant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
                    opacity: 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                    opacityHover: 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                    underlineVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                    underlineOffset: 1 | 2 | 3 | "1" | "2" | "3";
                    underlineOffsetHover: 1 | 2 | 3 | "1" | "2" | "3";
                    underlineOpacity: 0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                    underlineOpacityHover: 0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                    pill: boolean;
                    icon: boolean;
                    routerTag: string;
                    exactActiveClass: string;
                    loading: boolean;
                    loadingFill: boolean;
                    loadingText: string;
                    squared: boolean;
                }, {}, string, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, import('vue').ComponentProvideOptions> & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps & (new () => {
                    $slots: Readonly<{
                        default?: ((props: Record<string, never>) => any) | undefined;
                        loading?: ((props: Record<string, never>) => any) | undefined;
                        'loading-spinner'?: ((props: Record<string, never>) => any) | undefined;
                    }> & {
                        default?: ((props: Record<string, never>) => any) | undefined;
                        loading?: ((props: Record<string, never>) => any) | undefined;
                        'loading-spinner'?: ((props: Record<string, never>) => any) | undefined;
                    };
                });
                VsIcon: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
                    icon: {
                        type: StringConstructor;
                        required: true;
                    };
                    variant: {
                        type: StringConstructor;
                        default: string;
                        validator: (value: unknown) => any;
                    };
                    customColour: {
                        type: StringConstructor;
                        default: any;
                    };
                    size: {
                        type: StringConstructor;
                        default: string;
                        validator: (value: unknown) => any;
                    };
                    smallSize: {
                        type: StringConstructor;
                        default: any;
                        validator: (value: unknown) => any;
                    };
                }>, {}, {
                    tokens: {
                        "vs-color-background-primary": string;
                        "vs-color-background-secondary": string;
                        "vs-color-background-bold": string;
                        "vs-color-background-brand": string;
                        "vs-color-background-inverse": string;
                        "vs-color-background-highlight": string;
                        "vs-color-background-information": string;
                        "vs-color-background-success": string;
                        "vs-color-background-warning": string;
                        "vs-color-background-error": string;
                        "vs-color-background-accent-heather-80": string;
                        "vs-color-background-accent-heather-30": string;
                        "vs-color-background-accent-tolsta-40": string;
                        "vs-color-background-accent-gorse-05": string;
                        "vs-color-border-primary": string;
                        "vs-color-border-secondary": string;
                        "vs-color-border-inverse": string;
                        "vs-color-border-input": string;
                        "vs-color-border-highlight": string;
                        "vs-color-border-bold": string;
                        "vs-color-border-success": string;
                        "vs-color-border-warning": string;
                        "vs-color-border-error": string;
                        "vs-color-border-disabled": string;
                        "vs-color-icon-primary": string;
                        "vs-color-icon-secondary": string;
                        "vs-color-icon-tertiary": string;
                        "vs-color-icon-inverse": string;
                        "vs-color-icon-cta-on-light": string;
                        "vs-color-icon-highlight": string;
                        "vs-color-icon-success": string;
                        "vs-color-icon-warning": string;
                        "vs-color-icon-error": string;
                        "vs-color-icon-disabled": string;
                        "vs-color-icon-accent-saltire-30": string;
                        "vs-color-icon-ski-easy": string;
                        "vs-color-icon-ski-intermediate": string;
                        "vs-color-icon-ski-difficult": string;
                        "vs-color-icon-ski-very-difficult": string;
                        "vs-color-icon-ski-itineraries": string;
                        "vs-color-icon-ski-other": string;
                        "vs-color-icon-accent-loch-ness": string;
                        "vs-color-icon-accent-grey-02": string;
                        "vs-color-icon-accent-saltire": string;
                        "vs-color-icon-accent-bramble": string;
                        "vs-color-icon-accent-heather": string;
                        "vs-color-icon-accent-whisky": string;
                        "vs-color-icon-accent-cranachan": string;
                        "vs-color-icon-accent-buachaille": string;
                        "vs-color-interaction-focus": string;
                        "vs-color-interaction-focus-on-bold": string;
                        "vs-color-interaction-cta-primary": string;
                        "vs-color-interaction-cta-secondary": string;
                        "vs-color-interaction-cta-hover": string;
                        "vs-color-interaction-cta-pressed": string;
                        "vs-color-interaction-cta-disabled": string;
                        "vs-color-interaction-cta-subtle-hover": string;
                        "vs-color-interaction-cta-subtle-pressed": string;
                        "vs-color-interaction-cta-subtle": string;
                        "vs-color-interaction-link-primary": string;
                        "vs-color-interaction-link-secondary": string;
                        "vs-color-interaction-link-active": string;
                        "vs-color-interaction-link-visited": string;
                        "vs-color-interaction-link-disabled": string;
                        "vs-color-interaction-link-visited-on-bold": string;
                        "vs-color-text-primary": string;
                        "vs-color-text-secondary": string;
                        "vs-color-text-tertiary": string;
                        "vs-color-text-inverse": string;
                        "vs-color-text-cta-on-light": string;
                        "vs-color-text-highlight": string;
                        "vs-color-text-brand": string;
                        "vs-color-text-error": string;
                        "vs-color-text-success": string;
                        "vs-color-text-disabled": string;
                        breakpoint_xl: string;
                        grid_container_width_xl: string;
                        breakpoint_md: string;
                        max_container_width_xl: string;
                        grid_container_width_md: string;
                        breakpoint_sm: string;
                        breakpoint_lg: string;
                        breakpoint_xs: string;
                        max_container_width_md: string;
                        grid_container_width_sm: string;
                        grid_container_width_lg: string;
                        max_container_width_sm: string;
                        max_container_width_lg: string;
                        max_container_width_xxl: string;
                        grid_container_width_xxl: string;
                        breakpoint_xxl: string;
                        grid_columns: string;
                        grid_gutter_width: string;
                        "vs-spacer-0": string;
                        "vs-spacer-0125": string;
                        "vs-spacer-025": string;
                        "vs-spacer-050": string;
                        "vs-spacer-075": string;
                        "vs-spacer-100": string;
                        "vs-spacer-125": string;
                        "vs-spacer-150": string;
                        "vs-spacer-175": string;
                        "vs-spacer-200": string;
                        "vs-spacer-250": string;
                        "vs-spacer-300": string;
                        "vs-spacer-400": string;
                        "vs-spacer-500": string;
                        "vs-spacer-600": string;
                        "vs-spacer-700": string;
                        "vs-radius-medium": string;
                        "vs-radius-none": string;
                        "vs-radius-large-increased": string;
                        "vs-radius-extra-large-increased": string;
                        "vs-radius-tiny": string;
                        "vs-radius-large": string;
                        "vs-radius-extra-large": string;
                        "vs-radius-extra-extra-large": string;
                        "vs-radius-huge": string;
                        "vs-radius-full": string;
                        "vs-radius-small": string;
                        "vs-elevation-shadow-raised": string;
                        "vs-elevation-shadow-overlay": string;
                        "vs-elevation-surface": string;
                        "vs-elevation-surface-section": string;
                        "vs-elevation-surface-raised": string;
                        "vs-elevation-surface-overlay": string;
                        "vs-focus-shadow": string;
                        "vs-focus-shadow-on-dark": string;
                        "vs-border-width-sm": string;
                        "vs-border-width-md": string;
                        "vs-font-size-display-m": string;
                        "vs-font-size-display-s": string;
                        "vs-font-size-heading-xl": string;
                        "vs-font-size-heading-l": string;
                        "vs-font-size-heading-m": string;
                        "vs-font-size-heading-s": string;
                        "vs-font-size-heading-xs": string;
                        "vs-font-size-heading-xxs": string;
                        "vs-font-size-heading-xxxs": string;
                        "vs-font-size-detail-l": string;
                        "vs-font-size-detail-m": string;
                        "vs-font-size-detail-s": string;
                        "vs-font-size-body-l": string;
                        "vs-font-size-body-m": string;
                        "vs-font-size-body-s": string;
                        "vs-font-weight-strong": string;
                        "vs-font-weight-medium": string;
                        "vs-font-weight-regular": string;
                        "vs-font-weight-subtle": string;
                        "vs-font-weight-heading": string;
                        "vs-font-weight-body": string;
                        "vs-font-weight-detail": string;
                        "vs-font-family-sans-serif": string;
                        "vs-font-family-display": string;
                        "vs-line-height-heading": number;
                        "vs-line-height-detail": number;
                        "vs-line-height-body": number;
                        "vs-letter-spacing-display": string;
                        "vs-letter-spacing-heading": string;
                        "vs-letter-spacing-detail": string;
                        "vs-letter-spacing-body": string;
                        icon_size_xxs: string;
                        icon_size_xs: string;
                        icon_size_sm: string;
                        icon_size_md: string;
                        icon_size_lg: string;
                        icon_size_xl: string;
                        "vs-icon-facility-audio-loop": string;
                        "vs-icon-control-collapse": string;
                        "vs-icon-facility-wheelchair-access": string;
                        "vs-icon-control-pause": string;
                        "vs-icon-facility-cafe": string;
                        "vs-icon-feedback-error": string;
                        "vs-icon-facility-accessible-parking": string;
                        "vs-icon-control-filters": string;
                        "vs-icon-feedback-information": string;
                        "vs-icon-control-external-link": string;
                        "vs-icon-control-play": string;
                        "vs-icon-facility-accessible-dining": string;
                        "vs-icon-facility-public-transport": string;
                        "vs-icon-season-winter": string;
                        "vs-icon-control-expand": string;
                        "vs-icon-facility-pets-welcome": string;
                        "vs-icon-facility-parking": string;
                        "vs-icon-control-menu": string;
                        "vs-icon-season-spring": string;
                        "vs-icon-season-summer": string;
                        "vs-icon-facility-breakfast": string;
                        "vs-icon-control-back-to-top": string;
                        "vs-icon-control-dismiss": string;
                        "vs-icon-feedback-warning": string;
                        "vs-icon-facility-accessible-shower": string;
                        "vs-icon-facility-wifi": string;
                        "vs-icon-control-search": string;
                        "vs-icon-control-download": string;
                        "vs-icon-season-autumn": string;
                        "vs-icon-facility-accessible-toilet": string;
                        "vs-icon-feedback-success": string;
                        "vs-icon-facility-vegan": string;
                        "font-size-1": string;
                        "font-size-2": string;
                        "font-size-3": string;
                        "font-size-4": string;
                        "font-size-5": string;
                        "font-size-6": string;
                        "font-size-7": string;
                        "font-size-8": string;
                        "font-size-9": string;
                        "font-size-10": string;
                        "font-size-body": string;
                        "font-size-body-md": string;
                        "font-size-lead": string;
                        "font-size-lead-md": string;
                        "font-size-teaser": string;
                        opacity_100: string;
                        opacity_50: string;
                        opacity_0: string;
                        duration_quickly: string;
                        duration_base: string;
                        duration_slowly: string;
                        no_javascript: string;
                        theme_dark: string;
                        theme_grey: string;
                    };
                }, {
                    iconClasses(): any[];
                    fontAwesomeClasses(): any;
                    iconStyles(): {
                        color: string;
                    };
                }, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
                    icon: {
                        type: StringConstructor;
                        required: true;
                    };
                    variant: {
                        type: StringConstructor;
                        default: string;
                        validator: (value: unknown) => any;
                    };
                    customColour: {
                        type: StringConstructor;
                        default: any;
                    };
                    size: {
                        type: StringConstructor;
                        default: string;
                        validator: (value: unknown) => any;
                    };
                    smallSize: {
                        type: StringConstructor;
                        default: any;
                        validator: (value: unknown) => any;
                    };
                }>> & Readonly<{}>, {
                    variant: string;
                    customColour: string;
                    size: string;
                    smallSize: string;
                }, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
            }, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
        }, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
        BCard: {
            new (...args: any[]): import('vue').CreateComponentPublicInstanceWithMixins<Readonly<import('vue').ExtractPropTypes<{
                variant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: null;
                };
                tag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                align: {
                    type: import('vue').PropType<import('bootstrap-vue-next').AlignmentTextHorizontal>;
                    default: undefined;
                };
                bodyBgVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: undefined;
                };
                bodyClass: {
                    type: import('vue').PropType<any>;
                    default: undefined;
                };
                bodyTag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                bodyText: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                bodyTextVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                    default: undefined;
                };
                borderVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: null;
                };
                footer: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                footerBgVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: undefined;
                };
                footerBorderVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: undefined;
                };
                footerClass: {
                    type: import('vue').PropType<any>;
                    default: undefined;
                };
                footerHtml: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                footerTag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                footerTextVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                    default: undefined;
                };
                footerVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: null;
                };
                header: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                headerBgVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: undefined;
                };
                headerBorderVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: undefined;
                };
                headerClass: {
                    type: import('vue').PropType<any>;
                    default: undefined;
                };
                headerHtml: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                headerTag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                headerTextVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                    default: undefined;
                };
                headerVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: null;
                };
                imgAlt: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                imgBottom: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                imgEnd: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                imgHeight: {
                    type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                    default: undefined;
                };
                imgSrc: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                imgStart: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                imgTop: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                imgWidth: {
                    type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                    default: undefined;
                };
                noBody: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                overlay: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                subtitle: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                subtitleTag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                subtitleTextVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                    default: string;
                };
                title: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                titleTag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                bgVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: null;
                };
                textVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                    default: null;
                };
            }>>, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, import('vue').PublicProps, {
                tag: string;
                title: string;
                footer: string;
                header: string;
                align: import('bootstrap-vue-next').AlignmentTextHorizontal;
                variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                overlay: boolean;
                bodyClass: any;
                headerClass: any;
                headerTag: string;
                bgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                textVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
                bodyBgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                bodyTextVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
                footerBgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                footerBorderVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                footerClass: any;
                footerTextVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
                footerVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                headerBgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                headerBorderVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                headerTextVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
                headerVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                titleTag: string;
                borderVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                subtitle: string;
                subtitleTag: string;
                subtitleTextVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
                bodyTag: string;
                bodyText: string;
                footerHtml: string;
                footerTag: string;
                headerHtml: string;
                imgAlt: string;
                imgBottom: boolean;
                imgEnd: boolean;
                imgHeight: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
                imgSrc: string;
                imgStart: boolean;
                imgTop: boolean;
                imgWidth: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
                noBody: boolean;
            }, true, {}, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, {}, any, import('vue').ComponentProvideOptions, {
                P: {};
                B: {};
                D: {};
                C: {};
                M: {};
                Defaults: {};
            }, Readonly<import('vue').ExtractPropTypes<{
                variant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: null;
                };
                tag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                align: {
                    type: import('vue').PropType<import('bootstrap-vue-next').AlignmentTextHorizontal>;
                    default: undefined;
                };
                bodyBgVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: undefined;
                };
                bodyClass: {
                    type: import('vue').PropType<any>;
                    default: undefined;
                };
                bodyTag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                bodyText: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                bodyTextVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                    default: undefined;
                };
                borderVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: null;
                };
                footer: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                footerBgVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: undefined;
                };
                footerBorderVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: undefined;
                };
                footerClass: {
                    type: import('vue').PropType<any>;
                    default: undefined;
                };
                footerHtml: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                footerTag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                footerTextVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                    default: undefined;
                };
                footerVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: null;
                };
                header: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                headerBgVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: undefined;
                };
                headerBorderVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: undefined;
                };
                headerClass: {
                    type: import('vue').PropType<any>;
                    default: undefined;
                };
                headerHtml: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                headerTag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                headerTextVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                    default: undefined;
                };
                headerVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: null;
                };
                imgAlt: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                imgBottom: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                imgEnd: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                imgHeight: {
                    type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                    default: undefined;
                };
                imgSrc: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                imgStart: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                imgTop: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                imgWidth: {
                    type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                    default: undefined;
                };
                noBody: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                overlay: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                subtitle: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                subtitleTag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                subtitleTextVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                    default: string;
                };
                title: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                titleTag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                bgVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: null;
                };
                textVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                    default: null;
                };
            }>>, {}, {}, {}, {}, {
                tag: string;
                title: string;
                footer: string;
                header: string;
                align: import('bootstrap-vue-next').AlignmentTextHorizontal;
                variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                overlay: boolean;
                bodyClass: any;
                headerClass: any;
                headerTag: string;
                bgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                textVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
                bodyBgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                bodyTextVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
                footerBgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                footerBorderVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                footerClass: any;
                footerTextVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
                footerVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                headerBgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                headerBorderVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                headerTextVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
                headerVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                titleTag: string;
                borderVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                subtitle: string;
                subtitleTag: string;
                subtitleTextVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
                bodyTag: string;
                bodyText: string;
                footerHtml: string;
                footerTag: string;
                headerHtml: string;
                imgAlt: string;
                imgBottom: boolean;
                imgEnd: boolean;
                imgHeight: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
                imgSrc: string;
                imgStart: boolean;
                imgTop: boolean;
                imgWidth: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
                noBody: boolean;
            }>;
            __isFragment?: never;
            __isTeleport?: never;
            __isSuspense?: never;
        } & import('vue').ComponentOptionsBase<Readonly<import('vue').ExtractPropTypes<{
            variant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: null;
            };
            tag: {
                type: import('vue').PropType<string>;
                default: string;
            };
            align: {
                type: import('vue').PropType<import('bootstrap-vue-next').AlignmentTextHorizontal>;
                default: undefined;
            };
            bodyBgVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: undefined;
            };
            bodyClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            bodyTag: {
                type: import('vue').PropType<string>;
                default: string;
            };
            bodyText: {
                type: import('vue').PropType<string>;
                default: string;
            };
            bodyTextVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                default: undefined;
            };
            borderVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: null;
            };
            footer: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            footerBgVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: undefined;
            };
            footerBorderVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: undefined;
            };
            footerClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            footerHtml: {
                type: import('vue').PropType<string>;
                default: string;
            };
            footerTag: {
                type: import('vue').PropType<string>;
                default: string;
            };
            footerTextVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                default: undefined;
            };
            footerVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: null;
            };
            header: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            headerBgVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: undefined;
            };
            headerBorderVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: undefined;
            };
            headerClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            headerHtml: {
                type: import('vue').PropType<string>;
                default: string;
            };
            headerTag: {
                type: import('vue').PropType<string>;
                default: string;
            };
            headerTextVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                default: undefined;
            };
            headerVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: null;
            };
            imgAlt: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            imgBottom: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            imgEnd: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            imgHeight: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
            };
            imgSrc: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            imgStart: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            imgTop: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            imgWidth: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
            };
            noBody: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            overlay: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            subtitle: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            subtitleTag: {
                type: import('vue').PropType<string>;
                default: string;
            };
            subtitleTextVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                default: string;
            };
            title: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            titleTag: {
                type: import('vue').PropType<string>;
                default: string;
            };
            bgVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: null;
            };
            textVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                default: null;
            };
        }>>, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, {
            tag: string;
            title: string;
            footer: string;
            header: string;
            align: import('bootstrap-vue-next').AlignmentTextHorizontal;
            variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            overlay: boolean;
            bodyClass: any;
            headerClass: any;
            headerTag: string;
            bgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            textVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
            bodyBgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            bodyTextVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
            footerBgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            footerBorderVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            footerClass: any;
            footerTextVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
            footerVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            headerBgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            headerBorderVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            headerTextVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
            headerVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            titleTag: string;
            borderVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            subtitle: string;
            subtitleTag: string;
            subtitleTextVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
            bodyTag: string;
            bodyText: string;
            footerHtml: string;
            footerTag: string;
            headerHtml: string;
            imgAlt: string;
            imgBottom: boolean;
            imgEnd: boolean;
            imgHeight: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            imgSrc: string;
            imgStart: boolean;
            imgTop: boolean;
            imgWidth: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            noBody: boolean;
        }, {}, string, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, import('vue').ComponentProvideOptions> & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps & (new () => {
            $slots: Readonly<{
                default?: ((props: Record<string, never>) => any) | undefined;
                footer?: ((props: Record<string, never>) => any) | undefined;
                header?: ((props: Record<string, never>) => any) | undefined;
                img?: ((props: Record<string, never>) => any) | undefined;
            }> & {
                default?: ((props: Record<string, never>) => any) | undefined;
                footer?: ((props: Record<string, never>) => any) | undefined;
                header?: ((props: Record<string, never>) => any) | undefined;
                img?: ((props: Record<string, never>) => any) | undefined;
            };
        });
        VsIcon: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
            icon: {
                type: StringConstructor;
                required: true;
            };
            variant: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            customColour: {
                type: StringConstructor;
                default: any;
            };
            size: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            smallSize: {
                type: StringConstructor;
                default: any;
                validator: (value: unknown) => any;
            };
        }>, {}, {
            tokens: {
                "vs-color-background-primary": string;
                "vs-color-background-secondary": string;
                "vs-color-background-bold": string;
                "vs-color-background-brand": string;
                "vs-color-background-inverse": string;
                "vs-color-background-highlight": string;
                "vs-color-background-information": string;
                "vs-color-background-success": string;
                "vs-color-background-warning": string;
                "vs-color-background-error": string;
                "vs-color-background-accent-heather-80": string;
                "vs-color-background-accent-heather-30": string;
                "vs-color-background-accent-tolsta-40": string;
                "vs-color-background-accent-gorse-05": string;
                "vs-color-border-primary": string;
                "vs-color-border-secondary": string;
                "vs-color-border-inverse": string;
                "vs-color-border-input": string;
                "vs-color-border-highlight": string;
                "vs-color-border-bold": string;
                "vs-color-border-success": string;
                "vs-color-border-warning": string;
                "vs-color-border-error": string;
                "vs-color-border-disabled": string;
                "vs-color-icon-primary": string;
                "vs-color-icon-secondary": string;
                "vs-color-icon-tertiary": string;
                "vs-color-icon-inverse": string;
                "vs-color-icon-cta-on-light": string;
                "vs-color-icon-highlight": string;
                "vs-color-icon-success": string;
                "vs-color-icon-warning": string;
                "vs-color-icon-error": string;
                "vs-color-icon-disabled": string;
                "vs-color-icon-accent-saltire-30": string;
                "vs-color-icon-ski-easy": string;
                "vs-color-icon-ski-intermediate": string;
                "vs-color-icon-ski-difficult": string;
                "vs-color-icon-ski-very-difficult": string;
                "vs-color-icon-ski-itineraries": string;
                "vs-color-icon-ski-other": string;
                "vs-color-icon-accent-loch-ness": string;
                "vs-color-icon-accent-grey-02": string;
                "vs-color-icon-accent-saltire": string;
                "vs-color-icon-accent-bramble": string;
                "vs-color-icon-accent-heather": string;
                "vs-color-icon-accent-whisky": string;
                "vs-color-icon-accent-cranachan": string;
                "vs-color-icon-accent-buachaille": string;
                "vs-color-interaction-focus": string;
                "vs-color-interaction-focus-on-bold": string;
                "vs-color-interaction-cta-primary": string;
                "vs-color-interaction-cta-secondary": string;
                "vs-color-interaction-cta-hover": string;
                "vs-color-interaction-cta-pressed": string;
                "vs-color-interaction-cta-disabled": string;
                "vs-color-interaction-cta-subtle-hover": string;
                "vs-color-interaction-cta-subtle-pressed": string;
                "vs-color-interaction-cta-subtle": string;
                "vs-color-interaction-link-primary": string;
                "vs-color-interaction-link-secondary": string;
                "vs-color-interaction-link-active": string;
                "vs-color-interaction-link-visited": string;
                "vs-color-interaction-link-disabled": string;
                "vs-color-interaction-link-visited-on-bold": string;
                "vs-color-text-primary": string;
                "vs-color-text-secondary": string;
                "vs-color-text-tertiary": string;
                "vs-color-text-inverse": string;
                "vs-color-text-cta-on-light": string;
                "vs-color-text-highlight": string;
                "vs-color-text-brand": string;
                "vs-color-text-error": string;
                "vs-color-text-success": string;
                "vs-color-text-disabled": string;
                breakpoint_xl: string;
                grid_container_width_xl: string;
                breakpoint_md: string;
                max_container_width_xl: string;
                grid_container_width_md: string;
                breakpoint_sm: string;
                breakpoint_lg: string;
                breakpoint_xs: string;
                max_container_width_md: string;
                grid_container_width_sm: string;
                grid_container_width_lg: string;
                max_container_width_sm: string;
                max_container_width_lg: string;
                max_container_width_xxl: string;
                grid_container_width_xxl: string;
                breakpoint_xxl: string;
                grid_columns: string;
                grid_gutter_width: string;
                "vs-spacer-0": string;
                "vs-spacer-0125": string;
                "vs-spacer-025": string;
                "vs-spacer-050": string;
                "vs-spacer-075": string;
                "vs-spacer-100": string;
                "vs-spacer-125": string;
                "vs-spacer-150": string;
                "vs-spacer-175": string;
                "vs-spacer-200": string;
                "vs-spacer-250": string;
                "vs-spacer-300": string;
                "vs-spacer-400": string;
                "vs-spacer-500": string;
                "vs-spacer-600": string;
                "vs-spacer-700": string;
                "vs-radius-medium": string;
                "vs-radius-none": string;
                "vs-radius-large-increased": string;
                "vs-radius-extra-large-increased": string;
                "vs-radius-tiny": string;
                "vs-radius-large": string;
                "vs-radius-extra-large": string;
                "vs-radius-extra-extra-large": string;
                "vs-radius-huge": string;
                "vs-radius-full": string;
                "vs-radius-small": string;
                "vs-elevation-shadow-raised": string;
                "vs-elevation-shadow-overlay": string;
                "vs-elevation-surface": string;
                "vs-elevation-surface-section": string;
                "vs-elevation-surface-raised": string;
                "vs-elevation-surface-overlay": string;
                "vs-focus-shadow": string;
                "vs-focus-shadow-on-dark": string;
                "vs-border-width-sm": string;
                "vs-border-width-md": string;
                "vs-font-size-display-m": string;
                "vs-font-size-display-s": string;
                "vs-font-size-heading-xl": string;
                "vs-font-size-heading-l": string;
                "vs-font-size-heading-m": string;
                "vs-font-size-heading-s": string;
                "vs-font-size-heading-xs": string;
                "vs-font-size-heading-xxs": string;
                "vs-font-size-heading-xxxs": string;
                "vs-font-size-detail-l": string;
                "vs-font-size-detail-m": string;
                "vs-font-size-detail-s": string;
                "vs-font-size-body-l": string;
                "vs-font-size-body-m": string;
                "vs-font-size-body-s": string;
                "vs-font-weight-strong": string;
                "vs-font-weight-medium": string;
                "vs-font-weight-regular": string;
                "vs-font-weight-subtle": string;
                "vs-font-weight-heading": string;
                "vs-font-weight-body": string;
                "vs-font-weight-detail": string;
                "vs-font-family-sans-serif": string;
                "vs-font-family-display": string;
                "vs-line-height-heading": number;
                "vs-line-height-detail": number;
                "vs-line-height-body": number;
                "vs-letter-spacing-display": string;
                "vs-letter-spacing-heading": string;
                "vs-letter-spacing-detail": string;
                "vs-letter-spacing-body": string;
                icon_size_xxs: string;
                icon_size_xs: string;
                icon_size_sm: string;
                icon_size_md: string;
                icon_size_lg: string;
                icon_size_xl: string;
                "vs-icon-facility-audio-loop": string;
                "vs-icon-control-collapse": string;
                "vs-icon-facility-wheelchair-access": string;
                "vs-icon-control-pause": string;
                "vs-icon-facility-cafe": string;
                "vs-icon-feedback-error": string;
                "vs-icon-facility-accessible-parking": string;
                "vs-icon-control-filters": string;
                "vs-icon-feedback-information": string;
                "vs-icon-control-external-link": string;
                "vs-icon-control-play": string;
                "vs-icon-facility-accessible-dining": string;
                "vs-icon-facility-public-transport": string;
                "vs-icon-season-winter": string;
                "vs-icon-control-expand": string;
                "vs-icon-facility-pets-welcome": string;
                "vs-icon-facility-parking": string;
                "vs-icon-control-menu": string;
                "vs-icon-season-spring": string;
                "vs-icon-season-summer": string;
                "vs-icon-facility-breakfast": string;
                "vs-icon-control-back-to-top": string;
                "vs-icon-control-dismiss": string;
                "vs-icon-feedback-warning": string;
                "vs-icon-facility-accessible-shower": string;
                "vs-icon-facility-wifi": string;
                "vs-icon-control-search": string;
                "vs-icon-control-download": string;
                "vs-icon-season-autumn": string;
                "vs-icon-facility-accessible-toilet": string;
                "vs-icon-feedback-success": string;
                "vs-icon-facility-vegan": string;
                "font-size-1": string;
                "font-size-2": string;
                "font-size-3": string;
                "font-size-4": string;
                "font-size-5": string;
                "font-size-6": string;
                "font-size-7": string;
                "font-size-8": string;
                "font-size-9": string;
                "font-size-10": string;
                "font-size-body": string;
                "font-size-body-md": string;
                "font-size-lead": string;
                "font-size-lead-md": string;
                "font-size-teaser": string;
                opacity_100: string;
                opacity_50: string;
                opacity_0: string;
                duration_quickly: string;
                duration_base: string;
                duration_slowly: string;
                no_javascript: string;
                theme_dark: string;
                theme_grey: string;
            };
        }, {
            iconClasses(): any[];
            fontAwesomeClasses(): any;
            iconStyles(): {
                color: string;
            };
        }, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
            icon: {
                type: StringConstructor;
                required: true;
            };
            variant: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            customColour: {
                type: StringConstructor;
                default: any;
            };
            size: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            smallSize: {
                type: StringConstructor;
                default: any;
                validator: (value: unknown) => any;
            };
        }>> & Readonly<{}>, {
            variant: string;
            customColour: string;
            size: string;
            smallSize: string;
        }, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
        BCardHeader: {
            new (...args: any[]): import('vue').CreateComponentPublicInstanceWithMixins<Readonly<import('vue').ExtractPropTypes<{
                variant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: undefined;
                };
                tag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                borderVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: undefined;
                };
                bgVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: undefined;
                };
                textVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                    default: undefined;
                };
                html: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                text: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
            }>>, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, import('vue').PublicProps, {
                tag: string;
                html: string;
                text: string;
                variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                bgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                textVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
                borderVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            }, true, {}, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, {}, any, import('vue').ComponentProvideOptions, {
                P: {};
                B: {};
                D: {};
                C: {};
                M: {};
                Defaults: {};
            }, Readonly<import('vue').ExtractPropTypes<{
                variant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: undefined;
                };
                tag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                borderVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: undefined;
                };
                bgVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: undefined;
                };
                textVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                    default: undefined;
                };
                html: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                text: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
            }>>, {}, {}, {}, {}, {
                tag: string;
                html: string;
                text: string;
                variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                bgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                textVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
                borderVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            }>;
            __isFragment?: never;
            __isTeleport?: never;
            __isSuspense?: never;
        } & import('vue').ComponentOptionsBase<Readonly<import('vue').ExtractPropTypes<{
            variant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: undefined;
            };
            tag: {
                type: import('vue').PropType<string>;
                default: string;
            };
            borderVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: undefined;
            };
            bgVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: undefined;
            };
            textVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                default: undefined;
            };
            html: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            text: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
        }>>, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, {
            tag: string;
            html: string;
            text: string;
            variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            bgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            textVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
            borderVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
        }, {}, string, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, import('vue').ComponentProvideOptions> & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps & (new () => {
            $slots: Readonly<{
                default?: ((props: Record<string, never>) => any) | undefined;
            }> & {
                default?: ((props: Record<string, never>) => any) | undefined;
            };
        });
        BCardBody: {
            new (...args: any[]): import('vue').CreateComponentPublicInstanceWithMixins<Readonly<import('vue').ExtractPropTypes<{
                variant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: null;
                };
                tag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                overlay: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                subtitle: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                subtitleTag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                subtitleTextVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                    default: undefined;
                };
                title: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                titleTag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                bgVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: null;
                };
                textVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                    default: null;
                };
                text: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
            }>>, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, import('vue').PublicProps, {
                tag: string;
                title: string;
                text: string;
                variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                overlay: boolean;
                bgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                textVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
                titleTag: string;
                subtitle: string;
                subtitleTag: string;
                subtitleTextVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
            }, true, {}, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, {}, any, import('vue').ComponentProvideOptions, {
                P: {};
                B: {};
                D: {};
                C: {};
                M: {};
                Defaults: {};
            }, Readonly<import('vue').ExtractPropTypes<{
                variant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: null;
                };
                tag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                overlay: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                subtitle: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                subtitleTag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                subtitleTextVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                    default: undefined;
                };
                title: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                titleTag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                bgVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                    default: null;
                };
                textVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                    default: null;
                };
                text: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
            }>>, {}, {}, {}, {}, {
                tag: string;
                title: string;
                text: string;
                variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                overlay: boolean;
                bgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                textVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
                titleTag: string;
                subtitle: string;
                subtitleTag: string;
                subtitleTextVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
            }>;
            __isFragment?: never;
            __isTeleport?: never;
            __isSuspense?: never;
        } & import('vue').ComponentOptionsBase<Readonly<import('vue').ExtractPropTypes<{
            variant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: null;
            };
            tag: {
                type: import('vue').PropType<string>;
                default: string;
            };
            overlay: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            subtitle: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            subtitleTag: {
                type: import('vue').PropType<string>;
                default: string;
            };
            subtitleTextVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                default: undefined;
            };
            title: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            titleTag: {
                type: import('vue').PropType<string>;
                default: string;
            };
            bgVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: null;
            };
            textVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                default: null;
            };
            text: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
        }>>, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, {
            tag: string;
            title: string;
            text: string;
            variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            overlay: boolean;
            bgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            textVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
            titleTag: string;
            subtitle: string;
            subtitleTag: string;
            subtitleTextVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
        }, {}, string, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, import('vue').ComponentProvideOptions> & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps & (new () => {
            $slots: Readonly<{
                default?: ((props: Record<string, never>) => any) | undefined;
                subtitle?: ((props: Record<string, never>) => any) | undefined;
                title?: ((props: Record<string, never>) => any) | undefined;
            }> & {
                default?: ((props: Record<string, never>) => any) | undefined;
                subtitle?: ((props: Record<string, never>) => any) | undefined;
                title?: ((props: Record<string, never>) => any) | undefined;
            };
        });
        VsHeading: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
            level: {
                type: (StringConstructor | NumberConstructor)[];
                default: string;
                validator: (value: unknown) => any;
            };
            headingStyle: {
                type: StringConstructor;
                required: true;
                validator: (value: unknown) => any;
            };
            id: {
                type: StringConstructor;
                default: any;
            };
            noMargins: {
                type: BooleanConstructor;
                default: boolean;
            };
        }>, {}, {}, {
            headingClasses(): string[];
            type(): string;
        }, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
            level: {
                type: (StringConstructor | NumberConstructor)[];
                default: string;
                validator: (value: unknown) => any;
            };
            headingStyle: {
                type: StringConstructor;
                required: true;
                validator: (value: unknown) => any;
            };
            id: {
                type: StringConstructor;
                default: any;
            };
            noMargins: {
                type: BooleanConstructor;
                default: boolean;
            };
        }>> & Readonly<{}>, {
            id: string;
            level: string | number;
            noMargins: boolean;
        }, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
    }, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
    VsLink: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
        href: {
            type: StringConstructor;
            default: any;
        };
        type: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
        variant: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
        iconSize: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
        disabled: {
            type: BooleanConstructor;
            default: boolean;
        };
        dataLayerValue: {
            type: StringConstructor;
            default: any;
        };
    }>, {}, {}, {
        variantClass(): string;
        iconName(): string;
        iconVariant(): string;
    }, {
        clickHandler(event: any): void;
        navigateToUrl(): void;
        keyHandler(event: any): void;
    }, {
        computed: {
            pageUrl: () => string;
        };
        data(): {
            dataLayerLoadConfirmed: boolean;
            dataLayerStore: any;
        };
        mounted(): void;
        methods: {
            templateFiller(template: any, values: any): {};
            createDataLayerObject(type: any, event: any, href: any): void;
            returnIsoDate(): string;
            pushToDataLayer(object: any): void;
            compileFullTemplate(templateValues: any): any;
            targetText(event: any): any;
        };
    }, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
        href: {
            type: StringConstructor;
            default: any;
        };
        type: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
        variant: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
        iconSize: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
        disabled: {
            type: BooleanConstructor;
            default: boolean;
        };
        dataLayerValue: {
            type: StringConstructor;
            default: any;
        };
    }>> & Readonly<{}>, {
        type: string;
        variant: string;
        disabled: boolean;
        href: string;
        iconSize: string;
        dataLayerValue: string;
    }, {}, {
        VsIcon: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
            icon: {
                type: StringConstructor;
                required: true;
            };
            variant: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            customColour: {
                type: StringConstructor;
                default: any;
            };
            size: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            smallSize: {
                type: StringConstructor;
                default: any;
                validator: (value: unknown) => any;
            };
        }>, {}, {
            tokens: {
                "vs-color-background-primary": string;
                "vs-color-background-secondary": string;
                "vs-color-background-bold": string;
                "vs-color-background-brand": string;
                "vs-color-background-inverse": string;
                "vs-color-background-highlight": string;
                "vs-color-background-information": string;
                "vs-color-background-success": string;
                "vs-color-background-warning": string;
                "vs-color-background-error": string;
                "vs-color-background-accent-heather-80": string;
                "vs-color-background-accent-heather-30": string;
                "vs-color-background-accent-tolsta-40": string;
                "vs-color-background-accent-gorse-05": string;
                "vs-color-border-primary": string;
                "vs-color-border-secondary": string;
                "vs-color-border-inverse": string;
                "vs-color-border-input": string;
                "vs-color-border-highlight": string;
                "vs-color-border-bold": string;
                "vs-color-border-success": string;
                "vs-color-border-warning": string;
                "vs-color-border-error": string;
                "vs-color-border-disabled": string;
                "vs-color-icon-primary": string;
                "vs-color-icon-secondary": string;
                "vs-color-icon-tertiary": string;
                "vs-color-icon-inverse": string;
                "vs-color-icon-cta-on-light": string;
                "vs-color-icon-highlight": string;
                "vs-color-icon-success": string;
                "vs-color-icon-warning": string;
                "vs-color-icon-error": string;
                "vs-color-icon-disabled": string;
                "vs-color-icon-accent-saltire-30": string;
                "vs-color-icon-ski-easy": string;
                "vs-color-icon-ski-intermediate": string;
                "vs-color-icon-ski-difficult": string;
                "vs-color-icon-ski-very-difficult": string;
                "vs-color-icon-ski-itineraries": string;
                "vs-color-icon-ski-other": string;
                "vs-color-icon-accent-loch-ness": string;
                "vs-color-icon-accent-grey-02": string;
                "vs-color-icon-accent-saltire": string;
                "vs-color-icon-accent-bramble": string;
                "vs-color-icon-accent-heather": string;
                "vs-color-icon-accent-whisky": string;
                "vs-color-icon-accent-cranachan": string;
                "vs-color-icon-accent-buachaille": string;
                "vs-color-interaction-focus": string;
                "vs-color-interaction-focus-on-bold": string;
                "vs-color-interaction-cta-primary": string;
                "vs-color-interaction-cta-secondary": string;
                "vs-color-interaction-cta-hover": string;
                "vs-color-interaction-cta-pressed": string;
                "vs-color-interaction-cta-disabled": string;
                "vs-color-interaction-cta-subtle-hover": string;
                "vs-color-interaction-cta-subtle-pressed": string;
                "vs-color-interaction-cta-subtle": string;
                "vs-color-interaction-link-primary": string;
                "vs-color-interaction-link-secondary": string;
                "vs-color-interaction-link-active": string;
                "vs-color-interaction-link-visited": string;
                "vs-color-interaction-link-disabled": string;
                "vs-color-interaction-link-visited-on-bold": string;
                "vs-color-text-primary": string;
                "vs-color-text-secondary": string;
                "vs-color-text-tertiary": string;
                "vs-color-text-inverse": string;
                "vs-color-text-cta-on-light": string;
                "vs-color-text-highlight": string;
                "vs-color-text-brand": string;
                "vs-color-text-error": string;
                "vs-color-text-success": string;
                "vs-color-text-disabled": string;
                breakpoint_xl: string;
                grid_container_width_xl: string;
                breakpoint_md: string;
                max_container_width_xl: string;
                grid_container_width_md: string;
                breakpoint_sm: string;
                breakpoint_lg: string;
                breakpoint_xs: string;
                max_container_width_md: string;
                grid_container_width_sm: string;
                grid_container_width_lg: string;
                max_container_width_sm: string;
                max_container_width_lg: string;
                max_container_width_xxl: string;
                grid_container_width_xxl: string;
                breakpoint_xxl: string;
                grid_columns: string;
                grid_gutter_width: string;
                "vs-spacer-0": string;
                "vs-spacer-0125": string;
                "vs-spacer-025": string;
                "vs-spacer-050": string;
                "vs-spacer-075": string;
                "vs-spacer-100": string;
                "vs-spacer-125": string;
                "vs-spacer-150": string;
                "vs-spacer-175": string;
                "vs-spacer-200": string;
                "vs-spacer-250": string;
                "vs-spacer-300": string;
                "vs-spacer-400": string;
                "vs-spacer-500": string;
                "vs-spacer-600": string;
                "vs-spacer-700": string;
                "vs-radius-medium": string;
                "vs-radius-none": string;
                "vs-radius-large-increased": string;
                "vs-radius-extra-large-increased": string;
                "vs-radius-tiny": string;
                "vs-radius-large": string;
                "vs-radius-extra-large": string;
                "vs-radius-extra-extra-large": string;
                "vs-radius-huge": string;
                "vs-radius-full": string;
                "vs-radius-small": string;
                "vs-elevation-shadow-raised": string;
                "vs-elevation-shadow-overlay": string;
                "vs-elevation-surface": string;
                "vs-elevation-surface-section": string;
                "vs-elevation-surface-raised": string;
                "vs-elevation-surface-overlay": string;
                "vs-focus-shadow": string;
                "vs-focus-shadow-on-dark": string;
                "vs-border-width-sm": string;
                "vs-border-width-md": string;
                "vs-font-size-display-m": string;
                "vs-font-size-display-s": string;
                "vs-font-size-heading-xl": string;
                "vs-font-size-heading-l": string;
                "vs-font-size-heading-m": string;
                "vs-font-size-heading-s": string;
                "vs-font-size-heading-xs": string;
                "vs-font-size-heading-xxs": string;
                "vs-font-size-heading-xxxs": string;
                "vs-font-size-detail-l": string;
                "vs-font-size-detail-m": string;
                "vs-font-size-detail-s": string;
                "vs-font-size-body-l": string;
                "vs-font-size-body-m": string;
                "vs-font-size-body-s": string;
                "vs-font-weight-strong": string;
                "vs-font-weight-medium": string;
                "vs-font-weight-regular": string;
                "vs-font-weight-subtle": string;
                "vs-font-weight-heading": string;
                "vs-font-weight-body": string;
                "vs-font-weight-detail": string;
                "vs-font-family-sans-serif": string;
                "vs-font-family-display": string;
                "vs-line-height-heading": number;
                "vs-line-height-detail": number;
                "vs-line-height-body": number;
                "vs-letter-spacing-display": string;
                "vs-letter-spacing-heading": string;
                "vs-letter-spacing-detail": string;
                "vs-letter-spacing-body": string;
                icon_size_xxs: string;
                icon_size_xs: string;
                icon_size_sm: string;
                icon_size_md: string;
                icon_size_lg: string;
                icon_size_xl: string;
                "vs-icon-facility-audio-loop": string;
                "vs-icon-control-collapse": string;
                "vs-icon-facility-wheelchair-access": string;
                "vs-icon-control-pause": string;
                "vs-icon-facility-cafe": string;
                "vs-icon-feedback-error": string;
                "vs-icon-facility-accessible-parking": string;
                "vs-icon-control-filters": string;
                "vs-icon-feedback-information": string;
                "vs-icon-control-external-link": string;
                "vs-icon-control-play": string;
                "vs-icon-facility-accessible-dining": string;
                "vs-icon-facility-public-transport": string;
                "vs-icon-season-winter": string;
                "vs-icon-control-expand": string;
                "vs-icon-facility-pets-welcome": string;
                "vs-icon-facility-parking": string;
                "vs-icon-control-menu": string;
                "vs-icon-season-spring": string;
                "vs-icon-season-summer": string;
                "vs-icon-facility-breakfast": string;
                "vs-icon-control-back-to-top": string;
                "vs-icon-control-dismiss": string;
                "vs-icon-feedback-warning": string;
                "vs-icon-facility-accessible-shower": string;
                "vs-icon-facility-wifi": string;
                "vs-icon-control-search": string;
                "vs-icon-control-download": string;
                "vs-icon-season-autumn": string;
                "vs-icon-facility-accessible-toilet": string;
                "vs-icon-feedback-success": string;
                "vs-icon-facility-vegan": string;
                "font-size-1": string;
                "font-size-2": string;
                "font-size-3": string;
                "font-size-4": string;
                "font-size-5": string;
                "font-size-6": string;
                "font-size-7": string;
                "font-size-8": string;
                "font-size-9": string;
                "font-size-10": string;
                "font-size-body": string;
                "font-size-body-md": string;
                "font-size-lead": string;
                "font-size-lead-md": string;
                "font-size-teaser": string;
                opacity_100: string;
                opacity_50: string;
                opacity_0: string;
                duration_quickly: string;
                duration_base: string;
                duration_slowly: string;
                no_javascript: string;
                theme_dark: string;
                theme_grey: string;
            };
        }, {
            iconClasses(): any[];
            fontAwesomeClasses(): any;
            iconStyles(): {
                color: string;
            };
        }, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
            icon: {
                type: StringConstructor;
                required: true;
            };
            variant: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            customColour: {
                type: StringConstructor;
                default: any;
            };
            size: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            smallSize: {
                type: StringConstructor;
                default: any;
                validator: (value: unknown) => any;
            };
        }>> & Readonly<{}>, {
            variant: string;
            customColour: string;
            size: string;
            smallSize: string;
        }, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
    }, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
