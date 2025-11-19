declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
    * A list of categories that describe the product, should contain
    * objects, each of which has a `name` prop
    */
    categories: {
        type: ArrayConstructor;
        default: any;
    };
}>, {}, {}, {
    /**
    * Returns the first 3 provided categories as a pipe separated string for
    * display
    */
    transformedCategories(): string;
}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
    * A list of categories that describe the product, should contain
    * objects, each of which has a `name` prop
    */
    categories: {
        type: ArrayConstructor;
        default: any;
    };
}>> & Readonly<{}>, {
    categories: unknown[];
}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
