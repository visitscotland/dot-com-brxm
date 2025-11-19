declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * This sets the alignment of the sidebar left or right of the section
     */
    sidebarAlign: {
        type: StringConstructor;
        default: string;
        validator: (value: unknown) => any;
    };
    businessSupport: {
        type: BooleanConstructor;
        default: boolean;
    };
}>, {}, {}, {
    sidebarAlignClass(): "vs-article-section--sidebar-right" | "vs-article-section--sidebar-left";
    hasSidebarSlot(): boolean;
}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * This sets the alignment of the sidebar left or right of the section
     */
    sidebarAlign: {
        type: StringConstructor;
        default: string;
        validator: (value: unknown) => any;
    };
    businessSupport: {
        type: BooleanConstructor;
        default: boolean;
    };
}>> & Readonly<{}>, {
    businessSupport: boolean;
    sidebarAlign: string;
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
    VsRow: import('vue').DefineComponent<{}, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<{}> & Readonly<{}>, {}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
    VsBody: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
        variant: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
        noMargins: {
            type: BooleanConstructor;
            default: boolean;
        };
    }>, {}, {}, {
        bodyClasses(): string[];
    }, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
        variant: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
        noMargins: {
            type: BooleanConstructor;
            default: boolean;
        };
    }>> & Readonly<{}>, {
        variant: string;
        noMargins: boolean;
    }, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
