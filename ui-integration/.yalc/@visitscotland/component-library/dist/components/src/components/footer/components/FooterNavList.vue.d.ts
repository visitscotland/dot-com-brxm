declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * If this is provided, the accordion expands above
     * the specified viewport `xs,sm, md, lg, xl, xxl`
     */
    breakPoint: {
        type: StringConstructor;
        default: any;
        validator: (value: unknown) => any;
    };
}>, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * If this is provided, the accordion expands above
     * the specified viewport `xs,sm, md, lg, xl, xxl`
     */
    breakPoint: {
        type: StringConstructor;
        default: any;
        validator: (value: unknown) => any;
    };
}>> & Readonly<{}>, {
    breakPoint: string;
}, {}, {
    VsAccordion: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
        breakPoint: {
            type: StringConstructor;
            default: any;
            validator: (value: unknown) => any;
        };
    }>, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
        breakPoint: {
            type: StringConstructor;
            default: any;
            validator: (value: unknown) => any;
        };
    }>> & Readonly<{}>, {
        breakPoint: string;
    }, {}, {}, {}, string, () => {
        breakPoint: string;
    }, true, {}, any>;
    VsRow: import('vue').DefineComponent<{}, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<{}> & Readonly<{}>, {}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
    VsContainer: import('vue').DefineComponent<{}, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<{}> & Readonly<{}>, {}, {}, {
        BContainer: {
            new (...args: any[]): import('vue').CreateComponentPublicInstanceWithMixins<Readonly<import('vue').ExtractPropTypes<{
                tag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                fluid: {
                    type: import('vue').PropType<boolean | import('bootstrap-vue-next').Breakpoint>;
                    default: boolean;
                };
                gutterX: {
                    type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                    default: undefined;
                };
                gutterY: {
                    type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                    default: undefined;
                };
            }>>, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, import('vue').PublicProps, {
                fluid: boolean | import('bootstrap-vue-next').Breakpoint;
                gutterX: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
                gutterY: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
                tag: string;
            }, true, {}, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, {}, any, import('vue').ComponentProvideOptions, {
                P: {};
                B: {};
                D: {};
                C: {};
                M: {};
                Defaults: {};
            }, Readonly<import('vue').ExtractPropTypes<{
                tag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                fluid: {
                    type: import('vue').PropType<boolean | import('bootstrap-vue-next').Breakpoint>;
                    default: boolean;
                };
                gutterX: {
                    type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                    default: undefined;
                };
                gutterY: {
                    type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                    default: undefined;
                };
            }>>, {}, {}, {}, {}, {
                fluid: boolean | import('bootstrap-vue-next').Breakpoint;
                gutterX: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
                gutterY: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
                tag: string;
            }>;
            __isFragment?: never;
            __isTeleport?: never;
            __isSuspense?: never;
        } & import('vue').ComponentOptionsBase<Readonly<import('vue').ExtractPropTypes<{
            tag: {
                type: import('vue').PropType<string>;
                default: string;
            };
            fluid: {
                type: import('vue').PropType<boolean | import('bootstrap-vue-next').Breakpoint>;
                default: boolean;
            };
            gutterX: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
            };
            gutterY: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
            };
        }>>, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, {
            fluid: boolean | import('bootstrap-vue-next').Breakpoint;
            gutterX: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            gutterY: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            tag: string;
        }, {}, string, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, import('vue').ComponentProvideOptions> & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps & (new () => {
            $slots: {
                default?(_: {}): any;
            };
        });
    }, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
    LazyHydrationWrapper: import('vue').DefineComponent<{
        whenIdle: {
            default: boolean;
            type: (BooleanConstructor | NumberConstructor)[];
        };
        whenVisible: {
            default: boolean;
            type: (BooleanConstructor | ObjectConstructor)[];
        };
        onInteraction: {
            default: boolean;
            type: (BooleanConstructor | StringConstructor | ArrayConstructor)[];
        };
        whenTriggered: {
            default: undefined;
            type: (BooleanConstructor | ObjectConstructor)[];
        };
    }, () => import('vue').VNode<import('vue').RendererNode, import('vue').RendererElement, {
        [key: string]: any;
    }> | import('vue').VNode<import('vue').RendererNode, import('vue').RendererElement, {
        [key: string]: any;
    }>[], unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, "hydrated"[], "hydrated", import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps, Readonly<import('vue').ExtractPropTypes<{
        whenIdle: {
            default: boolean;
            type: (BooleanConstructor | NumberConstructor)[];
        };
        whenVisible: {
            default: boolean;
            type: (BooleanConstructor | ObjectConstructor)[];
        };
        onInteraction: {
            default: boolean;
            type: (BooleanConstructor | StringConstructor | ArrayConstructor)[];
        };
        whenTriggered: {
            default: undefined;
            type: (BooleanConstructor | ObjectConstructor)[];
        };
    }>> & {
        onHydrated?: ((...args: any[]) => any) | undefined;
    }, {
        whenIdle: number | boolean;
        whenVisible: boolean | Record<string, any>;
        onInteraction: string | boolean | unknown[];
        whenTriggered: boolean | Record<string, any>;
    }>;
}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
