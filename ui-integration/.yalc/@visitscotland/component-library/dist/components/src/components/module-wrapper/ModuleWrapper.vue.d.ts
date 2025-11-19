declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * AnchorId will be used to set an id on the heading,
     * allowing for anchor links (ToC)
     */
    anchorId: {
        type: StringConstructor;
        default: any;
    };
    /**
     * Set column size and left-align the heading and intro content
     * for the Business Support Hub site.
     */
    businessSupport: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
    * The correct heading level for page hierarchy, the
    * heading will be styled the same regardless of level provided
    * `1|2|3|4|5|6`
    */
    headingLevel: {
        type: NumberConstructor;
        default: number;
        validator: (value: unknown) => any;
    };
    /**
     * The heading style used for the heading.
     * `display-m|display-s|heading-xl|
     * heading-l|heading-m|heading-s|heading-xs|heading-xxs`
     */
    headingStyle: {
        type: StringConstructor;
        default: string;
        validator: (value: unknown) => any;
    };
    /**
    * Theme of module wrapper to use
    */
    theme: {
        type: StringConstructor;
        default: string;
        validator: (value: unknown) => any;
    };
}>, {}, {}, {
    sectionClasses(): string[];
    headingColumnSizes(): {
        cols: number;
        offset: number;
        md: number;
        'offset-md': number;
    };
    IntroColumnSizes(): {
        cols: number;
        sm: number;
        'offset-sm': number;
        md: number;
        'offset-md': number;
    };
}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * AnchorId will be used to set an id on the heading,
     * allowing for anchor links (ToC)
     */
    anchorId: {
        type: StringConstructor;
        default: any;
    };
    /**
     * Set column size and left-align the heading and intro content
     * for the Business Support Hub site.
     */
    businessSupport: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
    * The correct heading level for page hierarchy, the
    * heading will be styled the same regardless of level provided
    * `1|2|3|4|5|6`
    */
    headingLevel: {
        type: NumberConstructor;
        default: number;
        validator: (value: unknown) => any;
    };
    /**
     * The heading style used for the heading.
     * `display-m|display-s|heading-xl|
     * heading-l|heading-m|heading-s|heading-xs|heading-xxs`
     */
    headingStyle: {
        type: StringConstructor;
        default: string;
        validator: (value: unknown) => any;
    };
    /**
    * Theme of module wrapper to use
    */
    theme: {
        type: StringConstructor;
        default: string;
        validator: (value: unknown) => any;
    };
}>> & Readonly<{}>, {
    headingStyle: string;
    headingLevel: number;
    businessSupport: boolean;
    anchorId: string;
    theme: string;
}, {}, {
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
    VsRow: import('vue').DefineComponent<{}, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<{}> & Readonly<{}>, {}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
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
