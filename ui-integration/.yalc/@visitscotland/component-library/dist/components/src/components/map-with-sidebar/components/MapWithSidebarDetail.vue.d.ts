declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * Data for component content
     */
    contentData: {
        type: ObjectConstructor;
        required: true;
    };
    /**
     * Heading level - to allow sequential heading
     */
    headingLevel: {
        type: StringConstructor;
        default: string;
    };
}>, {}, {}, {
    showTitle(): boolean;
}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * Data for component content
     */
    contentData: {
        type: ObjectConstructor;
        required: true;
    };
    /**
     * Heading level - to allow sequential heading
     */
    headingLevel: {
        type: StringConstructor;
        default: string;
    };
}>> & Readonly<{}>, {
    headingLevel: string;
}, {}, {
    VsImg: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
        src: {
            type: StringConstructor;
            required: true;
        };
        lowResImage: {
            type: StringConstructor;
            default: any;
        };
        alt: {
            type: StringConstructor;
            default: string;
        };
        fluid: {
            type: BooleanConstructor;
            default: boolean;
        };
        fluidGrow: {
            type: BooleanConstructor;
            default: boolean;
        };
        useGenericLqip: {
            type: BooleanConstructor;
            default: boolean;
        };
        useLazyLoading: {
            type: BooleanConstructor;
            default: boolean;
        };
    }>, {}, {}, {
        imgStyle(): {
            backgroundImage: string;
        };
        isSvg(): boolean;
        computedSrcSet(): unknown;
    }, {}, {
        computed: {
            fullSrcSet(): string;
        };
        methods: {
            specificImgSize(size: any): string;
        };
    } | {
        inject: {
            cols: {
                default: number;
            };
            sm: {
                default: number;
            };
            md: {
                default: number;
            };
            lg: {
                default: number;
            };
            xl: {
                default: number;
            };
        };
        computed: {
            computedSizes(): string;
        };
    }, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
        src: {
            type: StringConstructor;
            required: true;
        };
        lowResImage: {
            type: StringConstructor;
            default: any;
        };
        alt: {
            type: StringConstructor;
            default: string;
        };
        fluid: {
            type: BooleanConstructor;
            default: boolean;
        };
        fluidGrow: {
            type: BooleanConstructor;
            default: boolean;
        };
        useGenericLqip: {
            type: BooleanConstructor;
            default: boolean;
        };
        useLazyLoading: {
            type: BooleanConstructor;
            default: boolean;
        };
    }>> & Readonly<{}>, {
        fluid: boolean;
        fluidGrow: boolean;
        lowResImage: string;
        alt: string;
        useGenericLqip: boolean;
        useLazyLoading: boolean;
    }, {}, {
        BImg: import('vue').DefineComponent<{
            rounded: {
                type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                default: boolean;
            };
            start: {
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
            fluid: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            src: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            blank: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            blankColor: {
                type: import('vue').PropType<string>;
                default: string;
            };
            block: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            fluidGrow: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            height: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
            };
            lazy: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            sizes: {
                type: import('vue').PropType<string | readonly string[]>;
                default: undefined;
            };
            srcset: {
                type: import('vue').PropType<string | readonly string[]>;
                default: undefined;
            };
            thumbnail: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            width: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
            };
            roundedTop: {
                type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                default: undefined;
            };
            roundedBottom: {
                type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                default: undefined;
            };
            roundedStart: {
                type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                default: undefined;
            };
            roundedEnd: {
                type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                default: undefined;
            };
        }, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
            rounded: {
                type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                default: boolean;
            };
            start: {
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
            fluid: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            src: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            blank: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            blankColor: {
                type: import('vue').PropType<string>;
                default: string;
            };
            block: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            fluidGrow: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            height: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
            };
            lazy: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            sizes: {
                type: import('vue').PropType<string | readonly string[]>;
                default: undefined;
            };
            srcset: {
                type: import('vue').PropType<string | readonly string[]>;
                default: undefined;
            };
            thumbnail: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            width: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
            };
            roundedTop: {
                type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                default: undefined;
            };
            roundedBottom: {
                type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                default: undefined;
            };
            roundedStart: {
                type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                default: undefined;
            };
            roundedEnd: {
                type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                default: undefined;
            };
        }>>, {
            fluid: boolean;
            start: boolean;
            end: boolean;
            center: boolean;
            src: string;
            srcset: string | readonly string[];
            width: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            height: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            lazy: boolean;
            block: boolean;
            rounded: boolean | import('bootstrap-vue-next').RadiusElement;
            roundedTop: boolean | import('bootstrap-vue-next').RadiusElement;
            roundedBottom: boolean | import('bootstrap-vue-next').RadiusElement;
            roundedStart: boolean | import('bootstrap-vue-next').RadiusElement;
            roundedEnd: boolean | import('bootstrap-vue-next').RadiusElement;
            blank: boolean;
            blankColor: string;
            fluidGrow: boolean;
            sizes: string | readonly string[];
            thumbnail: boolean;
        }, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
    }, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
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
export default _default;
