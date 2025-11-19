declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * Use this option to render this component with a title styling.
     */
    title: {
        type: BooleanConstructor;
        default: boolean;
    };
}>, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * Use this option to render this component with a title styling.
     */
    title: {
        type: BooleanConstructor;
        default: boolean;
    };
}>> & Readonly<{}>, {
    title: boolean;
}, {}, {
    VsCol: import('vue').DefineComponent<{}, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<{}> & Readonly<{}>, {}, {}, {
        BCol: import('vue').DefineComponent<{
            alignSelf: {
                type: import('vue').PropType<import('bootstrap-vue-next').AlignmentVertical | "auto">;
                default: null;
            };
            tag: {
                type: StringConstructor;
                default: string;
            };
            order: {
                type: (StringConstructor | NumberConstructor)[];
                default: null;
            };
            offset: {
                type: (StringConstructor | NumberConstructor)[];
                default: null;
            };
            col: {
                type: BooleanConstructor;
                default: boolean;
            };
            cols: {
                type: (StringConstructor | NumberConstructor)[];
                default: null;
            };
        }, {
            computedClasses: import('vue').ComputedRef<(string[] | {
                [x: string]: boolean;
                col: boolean;
            })[]>;
        }, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
            alignSelf: {
                type: import('vue').PropType<import('bootstrap-vue-next').AlignmentVertical | "auto">;
                default: null;
            };
            tag: {
                type: StringConstructor;
                default: string;
            };
            order: {
                type: (StringConstructor | NumberConstructor)[];
                default: null;
            };
            offset: {
                type: (StringConstructor | NumberConstructor)[];
                default: null;
            };
            col: {
                type: BooleanConstructor;
                default: boolean;
            };
            cols: {
                type: (StringConstructor | NumberConstructor)[];
                default: null;
            };
        }>>, {
            tag: string;
            cols: string | number;
            col: boolean;
            offset: string | number;
            order: string | number;
            alignSelf: import('bootstrap-vue-next').AlignmentVertical | "auto";
        }, import('vue').SlotsType<{
            default?: Record<string, never> | undefined;
        }>, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
    }, {}, string, () => {
        cols: unknown;
        sm: unknown;
        md: unknown;
        lg: unknown;
        xl: unknown;
    }, true, {}, any>;
}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
