declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
    * Variant of the badge
    * @values default, subtle, information, highlight
    */
    variant: {
        type: StringConstructor;
        default: string;
        validator: (value: unknown) => any;
    };
}>, {}, {}, {
    badgeClasses(): string[];
}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
    * Variant of the badge
    * @values default, subtle, information, highlight
    */
    variant: {
        type: StringConstructor;
        default: string;
        validator: (value: unknown) => any;
    };
}>> & Readonly<{}>, {
    variant: string;
}, {}, {
    BBadge: {
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
                default: string;
            };
            tag: {
                type: import('vue').PropType<string>;
                default: string;
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
                default: undefined;
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
            pill: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            exactActiveClass: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            bgVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: null;
            };
            textVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                default: null;
            };
            dotIndicator: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            textIndicator: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
        }>>, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, import('vue').PublicProps, {
            tag: string;
            replace: boolean;
            target: import('bootstrap-vue-next').LinkTarget;
            to: import('vue-router').RouteLocationRaw;
            append: boolean;
            disabled: boolean;
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
            pill: boolean;
            icon: boolean;
            exactActiveClass: string;
            bgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            textVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
            dotIndicator: boolean;
            textIndicator: boolean;
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
                default: string;
            };
            tag: {
                type: import('vue').PropType<string>;
                default: string;
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
                default: undefined;
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
            pill: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            exactActiveClass: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            bgVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: null;
            };
            textVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                default: null;
            };
            dotIndicator: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            textIndicator: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
        }>>, {}, {}, {}, {}, {
            tag: string;
            replace: boolean;
            target: import('bootstrap-vue-next').LinkTarget;
            to: import('vue-router').RouteLocationRaw;
            append: boolean;
            disabled: boolean;
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
            pill: boolean;
            icon: boolean;
            exactActiveClass: string;
            bgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            textVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
            dotIndicator: boolean;
            textIndicator: boolean;
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
            default: string;
        };
        tag: {
            type: import('vue').PropType<string>;
            default: string;
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
            default: undefined;
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
        pill: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        exactActiveClass: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        bgVariant: {
            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
            default: null;
        };
        textVariant: {
            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
            default: null;
        };
        dotIndicator: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        textIndicator: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
    }>>, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, {
        tag: string;
        replace: boolean;
        target: import('bootstrap-vue-next').LinkTarget;
        to: import('vue-router').RouteLocationRaw;
        append: boolean;
        disabled: boolean;
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
        pill: boolean;
        icon: boolean;
        exactActiveClass: string;
        bgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
        textVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
        dotIndicator: boolean;
        textIndicator: boolean;
    }, {}, string, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, import('vue').ComponentProvideOptions> & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps & (new () => {
        $slots: Readonly<{
            default?: ((props: Record<string, never>) => any) | undefined;
        }> & {
            default?: ((props: Record<string, never>) => any) | undefined;
        };
    });
}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
