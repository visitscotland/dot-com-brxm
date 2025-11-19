declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
    * A string to precede the list of origins
    */
    label: {
        type: StringConstructor;
        default: any;
    };
    /**
    * A list of places that a tour departs from, should contain
    * objects, each of which has a `name` prop
    */
    origins: {
        type: ArrayConstructor;
        default: any;
    };
}>, {}, {}, {
    /**
    * Returns the first 3 provided categories as a pipe separated string for
    * display
    */
    transformedOrigins(): string;
}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
    * A string to precede the list of origins
    */
    label: {
        type: StringConstructor;
        default: any;
    };
    /**
    * A list of places that a tour departs from, should contain
    * objects, each of which has a `name` prop
    */
    origins: {
        type: ArrayConstructor;
        default: any;
    };
}>> & Readonly<{}>, {
    label: string;
    origins: unknown[];
}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
