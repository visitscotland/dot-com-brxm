declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * The font size used by the wrapper
     * `normal|lead`
     */
    variant: {
        type: StringConstructor;
        default: string;
        validator: (value: unknown) => any;
    };
    /**
     * Option to remove margins from body text.
     * Useful when body is used inside a component
     */
    noMargins: {
        type: BooleanConstructor;
        default: boolean;
    };
}>, {}, {}, {
    bodyClasses(): string[];
}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * The font size used by the wrapper
     * `normal|lead`
     */
    variant: {
        type: StringConstructor;
        default: string;
        validator: (value: unknown) => any;
    };
    /**
     * Option to remove margins from body text.
     * Useful when body is used inside a component
     */
    noMargins: {
        type: BooleanConstructor;
        default: boolean;
    };
}>> & Readonly<{}>, {
    variant: string;
    noMargins: boolean;
}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
