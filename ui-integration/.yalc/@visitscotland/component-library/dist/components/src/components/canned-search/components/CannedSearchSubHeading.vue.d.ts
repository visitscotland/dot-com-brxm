declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
    * The string to display
    */
    subHeading: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The number of lines of text to limit the subHeading to, if
     * this is un-set it will not limit
     *
     * 1, 2
     */
    lineLimit: {
        type: NumberConstructor;
        default: any;
        validator: (value: unknown) => boolean;
    };
}>, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
    * The string to display
    */
    subHeading: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The number of lines of text to limit the subHeading to, if
     * this is un-set it will not limit
     *
     * 1, 2
     */
    lineLimit: {
        type: NumberConstructor;
        default: any;
        validator: (value: unknown) => boolean;
    };
}>> & Readonly<{}>, {
    subHeading: string;
    lineLimit: number;
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
}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
