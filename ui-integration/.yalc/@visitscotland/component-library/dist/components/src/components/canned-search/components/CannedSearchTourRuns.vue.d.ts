declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
    * A string to precede the time period
    */
    label: {
        type: StringConstructor;
        default: string;
    };
    /**
    * The first time in the period, if an endDay isn't provided
    * this will be shown on its own (as in the case `all year round`)
    */
    startDay: {
        type: StringConstructor;
        default: string;
    };
    /**
    * The last time in the period, if set this appears - separated from
    * the start day
    */
    endDay: {
        type: StringConstructor;
        default: string;
    };
}>, {}, {}, {
    /**
     * Returns a formatted string for the date range
     */
    formattedPeriod(): string;
}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
    * A string to precede the time period
    */
    label: {
        type: StringConstructor;
        default: string;
    };
    /**
    * The first time in the period, if an endDay isn't provided
    * this will be shown on its own (as in the case `all year round`)
    */
    startDay: {
        type: StringConstructor;
        default: string;
    };
    /**
    * The last time in the period, if set this appears - separated from
    * the start day
    */
    endDay: {
        type: StringConstructor;
        default: string;
    };
}>> & Readonly<{}>, {
    label: string;
    startDay: string;
    endDay: string;
}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
