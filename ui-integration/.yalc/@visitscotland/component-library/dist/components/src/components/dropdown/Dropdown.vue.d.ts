declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    text: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Style variation to give additional meaning
     * `primary|secondary`.
     */
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
    /**
     * Style variation to give additional meaning
     * `primary|secondary`.
     */
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
export default _default;
