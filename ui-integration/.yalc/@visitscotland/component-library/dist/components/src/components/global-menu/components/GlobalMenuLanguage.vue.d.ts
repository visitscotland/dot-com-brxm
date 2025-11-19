declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * 'Language' label to be shown or translated before language name.
     * Example: "Language": English.
     */
    languageLabel: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Language locale string.
     * Example: "en_GB"
     */
    language: {
        type: StringConstructor;
        default: string;
    };
}>, {}, {}, {}, {}, {
    computed: {
        selectedLanguage(): any;
        localeCookie(): any;
        translationCookie(): string;
    };
    methods: {
        cookieExists(cookie: any): boolean;
        setCookie(name: any, value: any, newCookie: any, sessionCookie?: boolean): void;
    };
}, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * 'Language' label to be shown or translated before language name.
     * Example: "Language": English.
     */
    languageLabel: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Language locale string.
     * Example: "en_GB"
     */
    language: {
        type: StringConstructor;
        default: string;
    };
}>> & Readonly<{}>, {
    language: string;
    languageLabel: string;
}, {}, {
    VsDropdown: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
        text: {
            type: StringConstructor;
            default: string;
        };
        variant: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
    }>, {}, {}, {
        nonButtonContentSlots(): any;
    }, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
        text: {
            type: StringConstructor;
            default: string;
        };
        variant: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
    }>> & Readonly<{}>, {
        variant: string;
        text: string;
    }, {}, {
        BDropdown: {
            new (...args: any[]): import('vue').CreateComponentPublicInstanceWithMixins<Readonly<import('vue').ExtractPropTypes<{
                modelValue: import('vue').PropType<boolean>;
                split: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                end: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                center: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                offset: {
                    type: import('vue').PropType<string | number | Readonly<{
                        mainAxis?: number | undefined;
                        crossAxis?: number | undefined;
                        alignmentAxis?: number | null | undefined;
                    }>>;
                    default: number;
                };
                id: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                ariaLabel: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                role: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                disabled: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                text: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                size: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseSize>;
                    default: string;
                };
                variant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseButtonVariant | null>;
                    default: string;
                };
                lazy: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                boundary: {
                    type: import('vue').PropType<Element | "document" | "clippingAncestors" | Element[] | import('@floating-ui/utils').Rect | "viewport">;
                    default: string;
                };
                boundaryPadding: {
                    type: import('vue').PropType<import('@floating-ui/utils').Padding>;
                    default: undefined;
                };
                floatingMiddleware: {
                    type: import('vue').PropType<{
                        name: string;
                        options?: any;
                        fn: (state: {
                            x: number;
                            y: number;
                            placement: import('@floating-ui/utils').Placement;
                            strategy: import('@floating-ui/utils').Strategy;
                            initialPlacement: import('@floating-ui/utils').Placement;
                            middlewareData: any;
                            rects: import('@floating-ui/utils').ElementRects;
                            platform: any;
                            elements: import('@floating-ui/dom').Elements;
                        }) => any | Promise<any>;
                    }[]>;
                    default: undefined;
                };
                noFlip: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                noShift: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                noSize: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                strategy: {
                    type: import('vue').PropType<import('@floating-ui/utils').Strategy>;
                    default: string;
                };
                teleportDisabled: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                teleportTo: {
                    type: import('vue').PropType<string | import('vue').RendererElement | null>;
                    default: undefined;
                };
                isNav: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                autoClose: {
                    type: import('vue').PropType<boolean | "inside" | "outside">;
                    default: boolean;
                };
                dropend: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                dropstart: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                dropup: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                menuClass: {
                    type: import('vue').PropType<any>;
                    default: undefined;
                };
                noCaret: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                splitButtonType: {
                    type: import('vue').PropType<import('bootstrap-vue-next').ButtonType>;
                    default: string;
                };
                splitClass: {
                    type: import('vue').PropType<any>;
                    default: undefined;
                };
                splitDisabled: {
                    type: import('vue').PropType<boolean>;
                    default: undefined;
                };
                splitHref: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                splitTo: {
                    type: import('vue').PropType<import('vue-router').RouteLocationRaw>;
                    default: undefined;
                };
                splitVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseButtonVariant | null>;
                    default: undefined;
                };
                toggleClass: {
                    type: import('vue').PropType<any>;
                    default: undefined;
                };
                toggleText: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
            }>> & {
                onHidden?: (() => any) | undefined;
                onShow?: ((value: import('bootstrap-vue-next').BvTriggerableEvent) => any) | undefined;
                onHide?: ((value: import('bootstrap-vue-next').BvTriggerableEvent) => any) | undefined;
                "onHide-prevented"?: (() => any) | undefined;
                "onShow-prevented"?: (() => any) | undefined;
                onShown?: (() => any) | undefined;
                onClick?: ((event: MouseEvent) => any) | undefined;
                onToggle?: (() => any) | undefined;
            }, {
                hide: () => void;
                show: () => void;
                toggle: () => void;
            }, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {
                hidden: () => void;
                click: (event: MouseEvent) => void;
                toggle: () => void;
                show: (value: import('bootstrap-vue-next').BvTriggerableEvent) => void;
                hide: (value: import('bootstrap-vue-next').BvTriggerableEvent) => void;
                "hide-prevented": () => void;
                "show-prevented": () => void;
                shown: () => void;
            }, import('vue').PublicProps, {
                split: boolean;
                end: boolean;
                center: boolean;
                offset: string | number | Readonly<{
                    mainAxis?: number | undefined;
                    crossAxis?: number | undefined;
                    alignmentAxis?: number | null | undefined;
                }>;
                id: string;
                ariaLabel: string;
                role: string;
                disabled: boolean;
                text: string;
                size: keyof import('bootstrap-vue-next').BaseSize;
                variant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
                lazy: boolean;
                boundary: Element | "document" | "clippingAncestors" | Element[] | import('@floating-ui/utils').Rect | "viewport";
                boundaryPadding: import('@floating-ui/utils').Padding;
                floatingMiddleware: {
                    name: string;
                    options?: any;
                    fn: (state: {
                        x: number;
                        y: number;
                        placement: import('@floating-ui/utils').Placement;
                        strategy: import('@floating-ui/utils').Strategy;
                        initialPlacement: import('@floating-ui/utils').Placement;
                        middlewareData: any;
                        rects: import('@floating-ui/utils').ElementRects;
                        platform: any;
                        elements: import('@floating-ui/dom').Elements;
                    }) => any | Promise<any>;
                }[];
                noFlip: boolean;
                noShift: boolean;
                noSize: boolean;
                strategy: import('@floating-ui/utils').Strategy;
                teleportDisabled: boolean;
                teleportTo: string | import('vue').RendererElement | null;
                isNav: boolean;
                autoClose: boolean | "inside" | "outside";
                dropend: boolean;
                dropstart: boolean;
                dropup: boolean;
                menuClass: any;
                noCaret: boolean;
                splitButtonType: import('bootstrap-vue-next').ButtonType;
                splitClass: any;
                splitDisabled: boolean;
                splitHref: string;
                splitTo: import('vue-router').RouteLocationRaw;
                splitVariant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
                toggleClass: any;
                toggleText: string;
            }, true, {}, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, {}, any, import('vue').ComponentProvideOptions, {
                P: {};
                B: {};
                D: {};
                C: {};
                M: {};
                Defaults: {};
            }, Readonly<import('vue').ExtractPropTypes<{
                modelValue: import('vue').PropType<boolean>;
                split: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                end: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                center: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                offset: {
                    type: import('vue').PropType<string | number | Readonly<{
                        mainAxis?: number | undefined;
                        crossAxis?: number | undefined;
                        alignmentAxis?: number | null | undefined;
                    }>>;
                    default: number;
                };
                id: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                ariaLabel: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                role: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                disabled: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                text: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                size: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseSize>;
                    default: string;
                };
                variant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseButtonVariant | null>;
                    default: string;
                };
                lazy: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                boundary: {
                    type: import('vue').PropType<Element | "document" | "clippingAncestors" | Element[] | import('@floating-ui/utils').Rect | "viewport">;
                    default: string;
                };
                boundaryPadding: {
                    type: import('vue').PropType<import('@floating-ui/utils').Padding>;
                    default: undefined;
                };
                floatingMiddleware: {
                    type: import('vue').PropType<{
                        name: string;
                        options?: any;
                        fn: (state: {
                            x: number;
                            y: number;
                            placement: import('@floating-ui/utils').Placement;
                            strategy: import('@floating-ui/utils').Strategy;
                            initialPlacement: import('@floating-ui/utils').Placement;
                            middlewareData: any;
                            rects: import('@floating-ui/utils').ElementRects;
                            platform: any;
                            elements: import('@floating-ui/dom').Elements;
                        }) => any | Promise<any>;
                    }[]>;
                    default: undefined;
                };
                noFlip: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                noShift: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                noSize: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                strategy: {
                    type: import('vue').PropType<import('@floating-ui/utils').Strategy>;
                    default: string;
                };
                teleportDisabled: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                teleportTo: {
                    type: import('vue').PropType<string | import('vue').RendererElement | null>;
                    default: undefined;
                };
                isNav: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                autoClose: {
                    type: import('vue').PropType<boolean | "inside" | "outside">;
                    default: boolean;
                };
                dropend: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                dropstart: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                dropup: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                menuClass: {
                    type: import('vue').PropType<any>;
                    default: undefined;
                };
                noCaret: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                splitButtonType: {
                    type: import('vue').PropType<import('bootstrap-vue-next').ButtonType>;
                    default: string;
                };
                splitClass: {
                    type: import('vue').PropType<any>;
                    default: undefined;
                };
                splitDisabled: {
                    type: import('vue').PropType<boolean>;
                    default: undefined;
                };
                splitHref: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                splitTo: {
                    type: import('vue').PropType<import('vue-router').RouteLocationRaw>;
                    default: undefined;
                };
                splitVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseButtonVariant | null>;
                    default: undefined;
                };
                toggleClass: {
                    type: import('vue').PropType<any>;
                    default: undefined;
                };
                toggleText: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
            }>> & {
                onHidden?: (() => any) | undefined;
                onShow?: ((value: import('bootstrap-vue-next').BvTriggerableEvent) => any) | undefined;
                onHide?: ((value: import('bootstrap-vue-next').BvTriggerableEvent) => any) | undefined;
                "onHide-prevented"?: (() => any) | undefined;
                "onShow-prevented"?: (() => any) | undefined;
                onShown?: (() => any) | undefined;
                onClick?: ((event: MouseEvent) => any) | undefined;
                onToggle?: (() => any) | undefined;
            }, {
                hide: () => void;
                show: () => void;
                toggle: () => void;
            }, {}, {}, {}, {
                split: boolean;
                end: boolean;
                center: boolean;
                offset: string | number | Readonly<{
                    mainAxis?: number | undefined;
                    crossAxis?: number | undefined;
                    alignmentAxis?: number | null | undefined;
                }>;
                id: string;
                ariaLabel: string;
                role: string;
                disabled: boolean;
                text: string;
                size: keyof import('bootstrap-vue-next').BaseSize;
                variant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
                lazy: boolean;
                boundary: Element | "document" | "clippingAncestors" | Element[] | import('@floating-ui/utils').Rect | "viewport";
                boundaryPadding: import('@floating-ui/utils').Padding;
                floatingMiddleware: {
                    name: string;
                    options?: any;
                    fn: (state: {
                        x: number;
                        y: number;
                        placement: import('@floating-ui/utils').Placement;
                        strategy: import('@floating-ui/utils').Strategy;
                        initialPlacement: import('@floating-ui/utils').Placement;
                        middlewareData: any;
                        rects: import('@floating-ui/utils').ElementRects;
                        platform: any;
                        elements: import('@floating-ui/dom').Elements;
                    }) => any | Promise<any>;
                }[];
                noFlip: boolean;
                noShift: boolean;
                noSize: boolean;
                strategy: import('@floating-ui/utils').Strategy;
                teleportDisabled: boolean;
                teleportTo: string | import('vue').RendererElement | null;
                isNav: boolean;
                autoClose: boolean | "inside" | "outside";
                dropend: boolean;
                dropstart: boolean;
                dropup: boolean;
                menuClass: any;
                noCaret: boolean;
                splitButtonType: import('bootstrap-vue-next').ButtonType;
                splitClass: any;
                splitDisabled: boolean;
                splitHref: string;
                splitTo: import('vue-router').RouteLocationRaw;
                splitVariant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
                toggleClass: any;
                toggleText: string;
            }>;
            __isFragment?: never;
            __isTeleport?: never;
            __isSuspense?: never;
        } & import('vue').ComponentOptionsBase<Readonly<import('vue').ExtractPropTypes<{
            modelValue: import('vue').PropType<boolean>;
            split: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            end: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            center: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            offset: {
                type: import('vue').PropType<string | number | Readonly<{
                    mainAxis?: number | undefined;
                    crossAxis?: number | undefined;
                    alignmentAxis?: number | null | undefined;
                }>>;
                default: number;
            };
            id: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            ariaLabel: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            role: {
                type: import('vue').PropType<string>;
                default: string;
            };
            disabled: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            text: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            size: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseSize>;
                default: string;
            };
            variant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseButtonVariant | null>;
                default: string;
            };
            lazy: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            boundary: {
                type: import('vue').PropType<Element | "document" | "clippingAncestors" | Element[] | import('@floating-ui/utils').Rect | "viewport">;
                default: string;
            };
            boundaryPadding: {
                type: import('vue').PropType<import('@floating-ui/utils').Padding>;
                default: undefined;
            };
            floatingMiddleware: {
                type: import('vue').PropType<{
                    name: string;
                    options?: any;
                    fn: (state: {
                        x: number;
                        y: number;
                        placement: import('@floating-ui/utils').Placement;
                        strategy: import('@floating-ui/utils').Strategy;
                        initialPlacement: import('@floating-ui/utils').Placement;
                        middlewareData: any;
                        rects: import('@floating-ui/utils').ElementRects;
                        platform: any;
                        elements: import('@floating-ui/dom').Elements;
                    }) => any | Promise<any>;
                }[]>;
                default: undefined;
            };
            noFlip: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            noShift: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            noSize: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            strategy: {
                type: import('vue').PropType<import('@floating-ui/utils').Strategy>;
                default: string;
            };
            teleportDisabled: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            teleportTo: {
                type: import('vue').PropType<string | import('vue').RendererElement | null>;
                default: undefined;
            };
            isNav: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            autoClose: {
                type: import('vue').PropType<boolean | "inside" | "outside">;
                default: boolean;
            };
            dropend: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            dropstart: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            dropup: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            menuClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            noCaret: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            splitButtonType: {
                type: import('vue').PropType<import('bootstrap-vue-next').ButtonType>;
                default: string;
            };
            splitClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            splitDisabled: {
                type: import('vue').PropType<boolean>;
                default: undefined;
            };
            splitHref: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            splitTo: {
                type: import('vue').PropType<import('vue-router').RouteLocationRaw>;
                default: undefined;
            };
            splitVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseButtonVariant | null>;
                default: undefined;
            };
            toggleClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            toggleText: {
                type: import('vue').PropType<string>;
                default: string;
            };
        }>> & {
            onHidden?: (() => any) | undefined;
            onShow?: ((value: import('bootstrap-vue-next').BvTriggerableEvent) => any) | undefined;
            onHide?: ((value: import('bootstrap-vue-next').BvTriggerableEvent) => any) | undefined;
            "onHide-prevented"?: (() => any) | undefined;
            "onShow-prevented"?: (() => any) | undefined;
            onShown?: (() => any) | undefined;
            onClick?: ((event: MouseEvent) => any) | undefined;
            onToggle?: (() => any) | undefined;
        }, {
            hide: () => void;
            show: () => void;
            toggle: () => void;
        }, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {
            hidden: () => void;
            click: (event: MouseEvent) => void;
            toggle: () => void;
            show: (value: import('bootstrap-vue-next').BvTriggerableEvent) => void;
            hide: (value: import('bootstrap-vue-next').BvTriggerableEvent) => void;
            "hide-prevented": () => void;
            "show-prevented": () => void;
            shown: () => void;
        }, string, {
            split: boolean;
            end: boolean;
            center: boolean;
            offset: string | number | Readonly<{
                mainAxis?: number | undefined;
                crossAxis?: number | undefined;
                alignmentAxis?: number | null | undefined;
            }>;
            id: string;
            ariaLabel: string;
            role: string;
            disabled: boolean;
            text: string;
            size: keyof import('bootstrap-vue-next').BaseSize;
            variant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
            lazy: boolean;
            boundary: Element | "document" | "clippingAncestors" | Element[] | import('@floating-ui/utils').Rect | "viewport";
            boundaryPadding: import('@floating-ui/utils').Padding;
            floatingMiddleware: {
                name: string;
                options?: any;
                fn: (state: {
                    x: number;
                    y: number;
                    placement: import('@floating-ui/utils').Placement;
                    strategy: import('@floating-ui/utils').Strategy;
                    initialPlacement: import('@floating-ui/utils').Placement;
                    middlewareData: any;
                    rects: import('@floating-ui/utils').ElementRects;
                    platform: any;
                    elements: import('@floating-ui/dom').Elements;
                }) => any | Promise<any>;
            }[];
            noFlip: boolean;
            noShift: boolean;
            noSize: boolean;
            strategy: import('@floating-ui/utils').Strategy;
            teleportDisabled: boolean;
            teleportTo: string | import('vue').RendererElement | null;
            isNav: boolean;
            autoClose: boolean | "inside" | "outside";
            dropend: boolean;
            dropstart: boolean;
            dropup: boolean;
            menuClass: any;
            noCaret: boolean;
            splitButtonType: import('bootstrap-vue-next').ButtonType;
            splitClass: any;
            splitDisabled: boolean;
            splitHref: string;
            splitTo: import('vue-router').RouteLocationRaw;
            splitVariant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
            toggleClass: any;
            toggleText: string;
        }, {}, string, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, import('vue').ComponentProvideOptions> & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps & (new () => {
            $slots: Readonly<{
                'button-content'?: ((props: Record<string, never>) => any) | undefined;
                default?: ((props: {
                    hide: () => void;
                    show: () => void;
                }) => any) | undefined;
                'toggle-text'?: ((props: Record<string, never>) => any) | undefined;
            }> & {
                'button-content'?: ((props: Record<string, never>) => any) | undefined;
                default?: ((props: {
                    hide: () => void;
                    show: () => void;
                }) => any) | undefined;
                'toggle-text'?: ((props: Record<string, never>) => any) | undefined;
            };
        });
    }, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
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
export default _default;
