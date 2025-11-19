declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * The source URL
     */
    src: {
        type: StringConstructor;
        required: true;
    };
    /**
     * Provide low res image to be initially loaded
     */
    lowResImage: {
        type: StringConstructor;
        default: any;
    };
    /**
     * The alt text for accessibility
     */
    alt: {
        type: StringConstructor;
        default: string;
    };
    /**
     * If true makes the image responsive, scaling with the parent
     * up to a max of the native width of the image itself
     */
    fluid: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * If true makes the image responsive, scaling with the parent
     * beyond the native image width if necessary
     */
    fluidGrow: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * If true a generic LQIP is used for lazyloading, rather than the
     * provided xxs path to the image scaler. Used when the image scaler
     * is not functioning as desired for certain images.
     */
    useGenericLqip: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * If true switches on lazy loading for the image
    */
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
    /**
     * The source URL
     */
    src: {
        type: StringConstructor;
        required: true;
    };
    /**
     * Provide low res image to be initially loaded
     */
    lowResImage: {
        type: StringConstructor;
        default: any;
    };
    /**
     * The alt text for accessibility
     */
    alt: {
        type: StringConstructor;
        default: string;
    };
    /**
     * If true makes the image responsive, scaling with the parent
     * up to a max of the native width of the image itself
     */
    fluid: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * If true makes the image responsive, scaling with the parent
     * beyond the native image width if necessary
     */
    fluidGrow: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * If true a generic LQIP is used for lazyloading, rather than the
     * provided xxs path to the image scaler. Used when the image scaler
     * is not functioning as desired for certain images.
     */
    useGenericLqip: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * If true switches on lazy loading for the image
    */
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
export default _default;
