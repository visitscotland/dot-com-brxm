declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * The breadcrumb anchor link href.
     */
    href: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The active breadcrumb item (last item). By default, BootstrapVue renders as a span.
     */
    active: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * The breadcrumb aria-current attribute. Only visible when the item is `active`.
     */
    ariaCurrent: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The breadcrumb text.
     */
    text: {
        type: StringConstructor;
        default: string;
        required: true;
    };
}>, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * The breadcrumb anchor link href.
     */
    href: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The active breadcrumb item (last item). By default, BootstrapVue renders as a span.
     */
    active: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * The breadcrumb aria-current attribute. Only visible when the item is `active`.
     */
    ariaCurrent: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The breadcrumb text.
     */
    text: {
        type: StringConstructor;
        default: string;
        required: true;
    };
}>> & Readonly<{}>, {
    href: string;
    active: boolean;
    text: string;
    ariaCurrent: string;
}, {}, {
    BBreadcrumbItem: {
        new (...args: any[]): import('vue').CreateComponentPublicInstanceWithMixins<Readonly<import('vue').ExtractPropTypes<{
            replace: {
                type: import('vue').PropType<boolean>;
                default: undefined;
            };
            icon: {
                type: import('vue').PropType<boolean>;
                default: undefined;
            };
            variant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: undefined;
            };
            target: {
                type: import('vue').PropType<import('bootstrap-vue-next').LinkTarget>;
                default: undefined;
            };
            to: RouteLocationRaw;
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
            opacity: {
                type: import('vue').PropType<10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                default: undefined;
            };
            opacityHover: {
                type: import('vue').PropType<10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                default: undefined;
            };
            underlineVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
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
            exactActiveClass: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            text: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            ariaCurrent: {
                type: import('vue').PropType<string>;
                default: string;
            };
        }>> & {
            onClick?: ((value: MouseEvent) => any) | undefined;
        }, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {
            click: (value: MouseEvent) => void;
        }, import('vue').PublicProps, {
            replace: boolean;
            target: import('bootstrap-vue-next').LinkTarget;
            to: import('vue-router').RouteLocationRaw;
            ariaCurrent: string;
            append: boolean;
            disabled: boolean;
            text: string;
            href: string;
            rel: string;
            active: boolean;
            activeClass: string;
            routerComponentName: string;
            variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            opacity: 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
            opacityHover: 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
            underlineVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            underlineOffset: 1 | 2 | 3 | "1" | "2" | "3";
            underlineOffsetHover: 1 | 2 | 3 | "1" | "2" | "3";
            underlineOpacity: 0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
            underlineOpacityHover: 0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
            icon: boolean;
            exactActiveClass: string;
        }, true, {}, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, {}, any, import('vue').ComponentProvideOptions, {
            P: {};
            B: {};
            D: {};
            C: {};
            M: {};
            Defaults: {};
        }, Readonly<import('vue').ExtractPropTypes<{
            replace: {
                type: import('vue').PropType<boolean>;
                default: undefined;
            };
            icon: {
                type: import('vue').PropType<boolean>;
                default: undefined;
            };
            variant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: undefined;
            };
            target: {
                type: import('vue').PropType<import('bootstrap-vue-next').LinkTarget>;
                default: undefined;
            };
            to: RouteLocationRaw;
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
            opacity: {
                type: import('vue').PropType<10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                default: undefined;
            };
            opacityHover: {
                type: import('vue').PropType<10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                default: undefined;
            };
            underlineVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
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
            exactActiveClass: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            text: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            ariaCurrent: {
                type: import('vue').PropType<string>;
                default: string;
            };
        }>> & {
            onClick?: ((value: MouseEvent) => any) | undefined;
        }, {}, {}, {}, {}, {
            replace: boolean;
            target: import('bootstrap-vue-next').LinkTarget;
            to: import('vue-router').RouteLocationRaw;
            ariaCurrent: string;
            append: boolean;
            disabled: boolean;
            text: string;
            href: string;
            rel: string;
            active: boolean;
            activeClass: string;
            routerComponentName: string;
            variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            opacity: 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
            opacityHover: 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
            underlineVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            underlineOffset: 1 | 2 | 3 | "1" | "2" | "3";
            underlineOffsetHover: 1 | 2 | 3 | "1" | "2" | "3";
            underlineOpacity: 0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
            underlineOpacityHover: 0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
            icon: boolean;
            exactActiveClass: string;
        }>;
        __isFragment?: never;
        __isTeleport?: never;
        __isSuspense?: never;
    } & import('vue').ComponentOptionsBase<Readonly<import('vue').ExtractPropTypes<{
        replace: {
            type: import('vue').PropType<boolean>;
            default: undefined;
        };
        icon: {
            type: import('vue').PropType<boolean>;
            default: undefined;
        };
        variant: {
            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
            default: undefined;
        };
        target: {
            type: import('vue').PropType<import('bootstrap-vue-next').LinkTarget>;
            default: undefined;
        };
        to: RouteLocationRaw;
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
        opacity: {
            type: import('vue').PropType<10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
            default: undefined;
        };
        opacityHover: {
            type: import('vue').PropType<10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
            default: undefined;
        };
        underlineVariant: {
            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
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
        exactActiveClass: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        text: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        ariaCurrent: {
            type: import('vue').PropType<string>;
            default: string;
        };
    }>> & {
        onClick?: ((value: MouseEvent) => any) | undefined;
    }, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {
        click: (value: MouseEvent) => void;
    }, string, {
        replace: boolean;
        target: import('bootstrap-vue-next').LinkTarget;
        to: import('vue-router').RouteLocationRaw;
        ariaCurrent: string;
        append: boolean;
        disabled: boolean;
        text: string;
        href: string;
        rel: string;
        active: boolean;
        activeClass: string;
        routerComponentName: string;
        variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
        opacity: 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
        opacityHover: 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
        underlineVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
        underlineOffset: 1 | 2 | 3 | "1" | "2" | "3";
        underlineOffsetHover: 1 | 2 | 3 | "1" | "2" | "3";
        underlineOpacity: 0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
        underlineOpacityHover: 0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
        icon: boolean;
        exactActiveClass: string;
    }, {}, string, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, import('vue').ComponentProvideOptions> & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps & (new () => {
        $slots: Readonly<{
            default?: ((props: Record<string, never>) => any) | undefined;
        }> & {
            default?: ((props: Record<string, never>) => any) | undefined;
        };
    });
}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
