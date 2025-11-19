declare const _default: {
    VsQuote: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
        variant: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
        withBorder: {
            type: BooleanConstructor;
            default: boolean;
        };
    }>, {}, {}, {
        hasAuthorName(): boolean;
        hasAuthorTitle(): boolean;
        hasAuthorImage(): boolean;
        variantClass(): {
            'vs-quote--narrow': boolean;
            'vs-quote--wide': boolean;
            'vs-quote--with-border': boolean;
        }[];
    }, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
        variant: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
        withBorder: {
            type: BooleanConstructor;
            default: boolean;
        };
    }>> & Readonly<{}>, {
        variant: string;
        withBorder: boolean;
    }, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
};
export default _default;
