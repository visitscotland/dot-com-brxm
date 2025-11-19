declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * The table caption for screen readers
     */
    tableCaption: {
        type: StringConstructor;
        required: true;
    };
    /**
     * The type of table to display
     * `responsive|stacked`
     */
    tableType: {
        type: StringConstructor;
        default: string;
        validator: (value: unknown) => any;
    };
}>, {}, {}, {
    responsive(): boolean;
    stacked(): boolean;
}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * The table caption for screen readers
     */
    tableCaption: {
        type: StringConstructor;
        required: true;
    };
    /**
     * The type of table to display
     * `responsive|stacked`
     */
    tableType: {
        type: StringConstructor;
        default: string;
        validator: (value: unknown) => any;
    };
}>> & Readonly<{}>, {
    tableType: string;
}, {}, {
    BTableSimple: {
        new (...args: any[]): import('vue').CreateComponentPublicInstanceWithMixins<Readonly<import('vue').ExtractPropTypes<{
            fixed: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            small: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            variant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: null;
            };
            id: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            dark: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            borderVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: null;
            };
            stacked: {
                type: import('vue').PropType<boolean | import('bootstrap-vue-next').Breakpoint>;
                default: boolean;
            };
            striped: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            bordered: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            borderless: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            captionTop: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            hover: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            noBorderCollapse: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            outlined: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            responsive: {
                type: import('vue').PropType<boolean | import('bootstrap-vue-next').Breakpoint>;
                default: boolean;
            };
            stickyHeader: {
                type: import('vue').PropType<boolean | import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: boolean;
            };
            stripedColumns: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            tableClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
        }>>, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, import('vue').PublicProps, {
            fixed: boolean;
            small: boolean;
            id: string;
            variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            dark: boolean;
            borderVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            stacked: boolean | import('bootstrap-vue-next').Breakpoint;
            bordered: boolean;
            borderless: boolean;
            captionTop: boolean;
            hover: boolean;
            noBorderCollapse: boolean;
            outlined: boolean;
            responsive: boolean | import('bootstrap-vue-next').Breakpoint;
            stickyHeader: boolean | import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            striped: boolean;
            stripedColumns: boolean;
            tableClass: any;
        }, true, {}, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, {}, any, import('vue').ComponentProvideOptions, {
            P: {};
            B: {};
            D: {};
            C: {};
            M: {};
            Defaults: {};
        }, Readonly<import('vue').ExtractPropTypes<{
            fixed: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            small: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            variant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: null;
            };
            id: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            dark: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            borderVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: null;
            };
            stacked: {
                type: import('vue').PropType<boolean | import('bootstrap-vue-next').Breakpoint>;
                default: boolean;
            };
            striped: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            bordered: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            borderless: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            captionTop: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            hover: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            noBorderCollapse: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            outlined: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            responsive: {
                type: import('vue').PropType<boolean | import('bootstrap-vue-next').Breakpoint>;
                default: boolean;
            };
            stickyHeader: {
                type: import('vue').PropType<boolean | import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: boolean;
            };
            stripedColumns: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            tableClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
        }>>, {}, {}, {}, {}, {
            fixed: boolean;
            small: boolean;
            id: string;
            variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            dark: boolean;
            borderVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            stacked: boolean | import('bootstrap-vue-next').Breakpoint;
            bordered: boolean;
            borderless: boolean;
            captionTop: boolean;
            hover: boolean;
            noBorderCollapse: boolean;
            outlined: boolean;
            responsive: boolean | import('bootstrap-vue-next').Breakpoint;
            stickyHeader: boolean | import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            striped: boolean;
            stripedColumns: boolean;
            tableClass: any;
        }>;
        __isFragment?: never;
        __isTeleport?: never;
        __isSuspense?: never;
    } & import('vue').ComponentOptionsBase<Readonly<import('vue').ExtractPropTypes<{
        fixed: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        small: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        variant: {
            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
            default: null;
        };
        id: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        dark: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        borderVariant: {
            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
            default: null;
        };
        stacked: {
            type: import('vue').PropType<boolean | import('bootstrap-vue-next').Breakpoint>;
            default: boolean;
        };
        striped: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        bordered: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        borderless: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        captionTop: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        hover: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        noBorderCollapse: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        outlined: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        responsive: {
            type: import('vue').PropType<boolean | import('bootstrap-vue-next').Breakpoint>;
            default: boolean;
        };
        stickyHeader: {
            type: import('vue').PropType<boolean | import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
            default: boolean;
        };
        stripedColumns: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        tableClass: {
            type: import('vue').PropType<any>;
            default: undefined;
        };
    }>>, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, {
        fixed: boolean;
        small: boolean;
        id: string;
        variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
        dark: boolean;
        borderVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
        stacked: boolean | import('bootstrap-vue-next').Breakpoint;
        bordered: boolean;
        borderless: boolean;
        captionTop: boolean;
        hover: boolean;
        noBorderCollapse: boolean;
        outlined: boolean;
        responsive: boolean | import('bootstrap-vue-next').Breakpoint;
        stickyHeader: boolean | import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
        striped: boolean;
        stripedColumns: boolean;
        tableClass: any;
    }, {}, string, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, import('vue').ComponentProvideOptions> & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps & (new () => {
        $slots: Readonly<{
            default?: ((props: Record<string, never>) => any) | undefined;
        }> & {
            default?: ((props: Record<string, never>) => any) | undefined;
        };
    });
}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
